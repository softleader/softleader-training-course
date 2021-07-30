# build-docker-image

### links
- [docker-desktop](https://www.docker.com/products/docker-desktop)
- [docker-hub](https://www.docker.com/products/docker-hub)

```shell
# 觀看跑起來的containers
docker ps

# 強制關閉並移除某個container
docker rm -f [containerId]
```

### helloworld
> 利用docker印出一行文字在console上
```shell
# 包版
docker build -t train-helloworld .
# 執行
docker run --rm train-helloworld
```

### maven-build
> 利用docker協助包版java專案
```shell
# 包版
docker build -t train-maven-build .
# 執行
docker run --rm -v "project:/workspace" train-maven-build
```

### web-demo
> 將包好的jar放到image給docker執行
```shell
# 包版(先包jar, 再包image)
mvn clean package -DskipTests
docker build -t train-spring-web-demo-1 .
# 執行
docker run --rm -p 8080:8080 train-spring-web-demo-1
```

### build-in-one
> 在一個Dockerfile內同時做包版與執行
```shell
# 包版
docker build -t train-spring-web-demo-2 .
# 執行
docker run --rm -p 8080:8080 train-spring-web-demo-2
```

### build-by-builder-pattern
> 在一個Dockerfile內同時做包版與執行, 但是包版專用的檔案不要放在執行的image裡面來節省空間
```shell
# 包版
docker build -t train-spring-web-demo-3 .
# 執行
docker run --rm -p 8080:8080 train-spring-web-demo-3
```

### build-by-jib
> 透過google jib工具, 直接使用mvn指令包image
```shell
# 包版
mvn clean compile jib:dockerBuild -DskipTests -Dimage=train-spring-web-demo-4
# 執行
docker run --rm -p 8080:8080 train-spring-web-demo-4
```

### diff-in-cmd-entrypoint
> Dockerfile內CMD與ENTRYPOINT的差別
[CMD vs ENTRYPOINT](https://docs.docker.com/engine/reference/builder/#understand-how-cmd-and-entrypoint-interact)

### build-ui-by-builder-pattern
> UI包版
```shell
# 包版
docker build -t train-ui-demo .
# 執行
docker run --rm -p 3000:80 train-ui-demo
```