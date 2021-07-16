# Spring Boot 2.4 Externalized Configuration

Spring Boot 2.4 改進了處理 `application.properties` 等設定檔的讀取的模式, 調整後的邏輯多支援了配置外部的載入方式, 此篇提供了一個簡單的 workshop 來介紹怎麼應用到雲平台 (Cloud Platform) 的部署上

> 如果是用 Spring Boot 2.4+ 但想要使用舊的邏輯, 可設定 `spring.config.use-legacy-processing=true`

## Config Data File

Spring Boot 在啟動時會參考許多設定來源 (Configuration Sources), Spring 也提供了[許多方式來配置](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-external-config), 這些源包含了以下常見的 (順序是重要的):

- Config data file(如 `application.properties`)
- OS environment variables
- Java System properties (`System.getProperties()`).
- Command-line arguments
- Testing config
- ... etc

其中在讀取 Config data file 的階段順序如下：

1. Application properties packaged inside your jar (`application.properties` and YAML variants).
1. Profile-specific application properties packaged inside your jar (`application-{profile}.properties` and YAML variants).
1. Application properties outside of your packaged jar (`application.properties` and YAML variants).
1. Profile-specific application properties outside of your packaged jar (`application-{profile}.properties` and YAML variants).


### External Application Properties

Spring Boot 預設的載入目錄順序為:

- `optional:classpath:/;optional:classpath:/config/`
- `optional:file:./;optional:file:./config/;optional:file:./config/*/`

你可以使用 `spring.config.location` 覆蓋預設載入行為:

```properties
# 覆蓋預設載入行為, 若為資料夾要以 '/' 結尾
spring.config.location=optional:classpath:/custom-config/
```

或是使用 `spring.config.additional-location` 在預設載入行為之後增加額外的讀取路徑:

```properties
spring.config.additional-location=optional:classpath:/custom-config/
```

以上設定 Spring 在載入的時機點非常的早, 通常是在 OS environment variable, system property 或 command-line argument 就必須先決定, 因此在[配置上有許多限制, 且檔案格式的支援上也有所限制](https://spring.io/blog/2020/08/14/config-file-processing-in-spring-boot-2-4#importing-additional-configuration)。

在雲平台 (Cloud Platform) 的部署上, 通常我們會在啟動 Container 時, 才將額外的設定以 Volume Mount 方式綁定到 Container 中, 因此在 Spring Boot 2.4 之後多了 `spring.config.import` , 可以在讀取到 properties 時才會再去載入所 import 的設定檔:

```properties
spring.application.name=myapp
spring.config.import=optional:file:./dev.properties
```

`spring.config.import` 除了 properties 或 YAML 格式以外, 還[支援了更多其他的設定方式](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#features.external-config.files.configtree)

### Profile Specific Files

Spring Boot 2.4 之後在讀取 `application` properties 外, 也跟之前一樣會試著讀取 `application-{profile}` 檔案, 假設我們啟動了 `prod,live` profile , 則載入的順序如下 (檔案沒找到不會報錯):

1. `application.properties`
2. `application-prod.properties`
3. `application-live.properties`

當 `spring.config.location` 指定多個目錄/檔案時, 要注意:

-  `,` - 使用逗號分隔多個目錄/檔案，且**有載入順序**的概念
-  `;` - 使用分號分隔多個目錄/檔案, 是沒有順序的建議，所有目錄/檔案都會被視為同一等級。

> 點擊參考[官方範例說明](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#features.external-config.files.profile-specific)

當設定檔被 Spring 載入後, 會被放入 `Map` 結構的物件中, 若同樣的 key 重複載入時, 其 value 將會被更新, 即後蓋前。換句話說, **載入順序越後面的設定將會最終被採用**

## Prepare Application

首先我們要準備一個 Spring Boot 2.4+ 的 Application, 請到 [Spring Initializr](https://start.spring.io/) 下載一個全新的專, Spring Boot 版本選擇 **2.4.3**, Dependencies 選擇 **Spring Web**, 或是執行以下指令取得:

```bash
$ curl https://start.spring.io/starter.zip \
		-d bootVersion=2.4.3 -d dependencies=web \
		-o demo.zip
$ unzip demo.zip
```

在 main class 的 package 中, 加上以下 Java class

```java
package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppNameController {

  @GetMapping
  public String name(@Value("${spring.application.name}") String appName) {
    return "Hello " + appName;
  }
}
```

接著開啟專案中的 `application.properties`, 加上:

```yaml
 spring.application.name=MyApp
```

完成以上步驟後執行 main class 或透過 `mvn spring-boot:run`啟動 App, 接著輸入以下指令確認:

```bash
$ curl localhost:8080 
Hello MyApp
```

## Import Additional Config

通常在雲平台中, 都有提供 volume mounted 功能將設定檔 mount 至 Container 中, 因此現在我們需要配置一個目錄給之後 mount config 用, 開啟 `application.properties` 加上

```
spring.config.import=optional:file:/tmp/config/
```

`spring.config.import` 可以指定檔案或目錄, 是用來告訴 Spring 在啟動時還要載入哪些 config

建議可以使用目錄 (斜線 `/` 結尾) 的方式來配置, 讓 Spring 還是依照他自己的載入順序來讀取目錄下的檔案, 這樣可以保留多一點配置的彈性給未來各種雲平台的部署檔

避免在 Container 中遭遇檔案讀取權限的問題, 建議可以將目錄配置在 `/tmp` 下

### Test Locally

在你的電腦建立 `/tmp/config/application.yaml`, 內容為:

```yaml
spring:
  application:
    name: MyLocalApp
```

接著重新啟動 App, 再透過原本的指令我們會發現輸出變了

```bash
$ curl localhost:8080
Hello MyLocalApp
```

## Build Image

我們透過以下指令建置 Image, 用來準備部署到雲平台使用! 

```bash
mvn spring-boot:build-image
```

預設的 Image 名稱是 maven 的 `{project.artifactId}:{project.version}`, 也就是 `demo:0.0.1-SNAPSHOT`

若覺得不適用可以透過參數 `spring-boot.build-image.imageName` 指定名稱, 你可以在 `pom.xml` 的 `<properties/>` 中加上或透過 command line 傳入, 如:

```bash
mvn spring-boot:build-image -Dspring-boot.build-image.imageName=demo:1.0.0
```

## Deploy Cloud Platform

在章節中我們會以兩個常見雲平台作為範例: **Docker Swarm** 及 **Kubernetes**

### Docker Swarm

準備以下檔案並放在同樣的目錄下:

```bash
.
├── application.yaml
└── docker-compose.yml
```

```yaml
version: "3.8"
services:
  demo:
    image: demo:1.0.0
    ports:
      - 8080:8080
    configs:
      - source: demo-config
        target: /tmp/config/application.yaml
configs:
  demo-config:
    file: ./application.yaml
```

```yaml
spring:
  application:
    name: MySwarmApp
```

接著透過以下指令部署

```yaml
docker stack deploy demo -c docker-compose.yml
# docker stack remove demo
```

服務啟動後, 再透過原本的指令我們會發現輸出又變了

```bash
$ curl localhost:8080
Hello MySwarmApp
```

### Kubernetes

準備以下檔案並放在同樣的目錄下:

```yaml
.
├── configmap.yaml
└── deployment.yaml
```

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: demo
  labels:
    app: demo
spec:
  replicas: 1
  selector:
    matchLabels:
      app: demo
  template:
    metadata:
      labels:
        app: demo
    spec:
      containers:
      - name: demo
        image: demo:1.0.0
        ports:
        - containerPort: 8080
        volumeMounts:
        - name: config-volume
          mountPath: /tmp/config
      volumes:
      - name: config-volume
        configMap:
          name: demo-config
```

```yaml
apiVersion: v1
kind: ConfigMap
metadata:
  name: demo-config
data:
  application.yaml: |-
    spring:
      application:
        name: MyKubernetesApp
```

接著透過以下指令部署

```yaml
kubectl apply -f .
# kubectl delete -f .
```

服務啟動後, 透過 port-forward 設定 port mapping:

```bash
kubectl port-forward deploy/demo 8080
```

再透過原本的指令我們會發現輸出又變了

```bash
$ curl localhost:8080
Hello MyKubernetesApp
```

## Good Old  Days

之前, 我們會針對可能會依照環境不同有變動的設定, 提前挖洞給 configmap 來填, 例如:

```yaml
spring:
  application:
    name: ${APP_NAME:MyApp}
```

然後我們會在 configmap 配置這些挖好的洞, 在部署前先給客戶填寫:

```yaml
apiVersion: v1
kind: ConfigMap
metadata:
  name: demo-config
data:
  APP_NAME: MyKubernetesApp
```

對開發人員來說, 需要挖洞的確比較麻煩, 像是:

- 要挖新的洞總是要重新打包 Image
- 洞挖多了維護 configmap 上也變複雜許多了
- 需要想挖洞的 Key 名稱啊, 取名總是最花時間的!

然而對於我們的客戶來說, 維運的窗口 (如過版人員) 挖洞其實有保障的, 如他們:

- 不需要去了解 Spring Boot 要怎麼配置
- 不用擔心配置結構錯了, 或 key 寫錯就輕易的影響 Runtime 行為, 甚至導致整個 App 開不起來

想更遠一些, 如果要抽象化實作跟配置方式, 挖洞的方式甚至還更適合一些, 以維運團隊的角度來看, 在配置部署上本來就不需要去知道實作的, 甚至就算在未來換掉 Spring Boot 也是開發團隊的事情罷了!

本篇介紹了新的 Spring 配置模式, 大家可以從中思考怎麼選擇或混用這些配置方式, 來建議客戶最佳的解決方案!
