# Spring Boot 入門 (Web + Jpa)

- [錄影檔-1](https://drive.google.com/file/d/1ldyKJ_fwswKCGwQtkhGtfGetGfeiQxtc/view?usp=drive_link)
- [錄影檔-2](https://drive.google.com/file/d/1oKN2a9cVqOjH6ymfNRECS8K3Y8mVfc8I/view?usp=drive_link)
- [錄影檔-3](https://drive.google.com/file/d/11Ze8NQNBPnVXOKLXfM2Ief2jtkuAjcr9/view?usp=drive_link)
- [錄影檔-4-spec-mapper](https://drive.google.com/file/d/1GmumcIfc5oa6l9FT0e95VRSgOi8AkOfM/view?usp=drive_link)

## `AI筆記`

### **快速啟動一個 Spring Boot 專案：從初始化到實踐**

本篇教學旨在引導開發者，特別是剛加入團隊的新成員，快速掌握如何從零開始建立一個簡單的 Spring Boot 專案，並介紹公司內部常用的框架與實務中常見的配置。

#### **1. 使用 Spring Initializr 建立專案基礎**

對於 Java 開發者而言，[Spring Initializr](https://start.spring.io/) 是一個不可或缺的工具。它提供了一個網頁介面，讓我們可以快速生成 Spring Boot 專案的基礎結構，並根據需求選擇所需的依賴函式庫 (Dependencies)。

**設定步驟：**

1.  **專案管理 (Project)**: 選擇 `Maven`。Maven 是 Java 世界中主流的專案管理與建構工具，我們公司內部專案也以此為標準。
2.  **語言 (Language)**: 選擇 `Java`。
3.  **Spring Boot 版本**: 選擇一個穩定版本，例如 `3.1.2` 或更新的版本。
4.  **專案元數據 (Project Metadata)**:
   *   **Group**: `com.example` (通常是公司或組織的反向網域名稱)
   *   **Artifact**: `demo` (專案的名稱)
   *   **Packaging**: `Jar`。Spring Boot 內嵌了 Tomcat 伺服器，使我們能將應用程式打包成一個可獨立執行的 Jar 檔，這也是雲原生 (Cloud Native) 應用程式的推薦作法。
   *   **Java**: 選擇 `17` 或更新的 LTS (長期支援) 版本。

5.  **加入核心依賴 (Dependencies)**: 點擊 "ADD DEPENDENCIES..." 按鈕，加入以下幾個在實務中幾乎都會用到的函式庫：
   *   **Spring Web**: 用於建構 RESTful API 和 Web 應用程式的核心模組。
   *   **Spring Data JPA**: 提供了一套簡潔的方式來操作關聯式資料庫，它基於 Java Persistence API (JPA) 標準，能大幅簡化資料存取層的程式碼。
   *   **Lombok**: 一個非常實用的工具，可以透過 Annotation (註解) 的方式，在編譯時期自動生成樣板程式碼 (Boilerplate Code)，例如 `getter`, `setter`, `constructor` 等，讓程式碼更簡潔。
   *   **H2 Database**: 一個輕量級的 Java SQL 資料庫，支援記憶體模式 (in-memory mode)。它非常適合在開發、測試階段使用，因為它啟動快速且不需要額外的安裝設定。
   *   **Spring Boot Actuator**: 提供了一系列用於監控和管理應用程式的 HTTP 端點 (Endpoints)。例如，它可以提供應用程式的健康狀態 (`/health`)、指標 (`/metrics`) 等資訊，這在 Kubernetes (K8s) 等容器化環境中對於實現健康檢查 (Health Check) 至關重要。

設定完成後，點擊 "GENERATE" 按鈕，將會下載一個名為 `demo.zip` 的壓縮檔，這就是我們專案的初始樣貌。

#### **2. 公司內部框架概覽**

在實務上，為了統一開發標準並整合常用功能，公司內部通常會基於開源框架再封裝一層自己的框架。目前主流使用的有兩個版本：

1.  **softleader-boot-starter-platform**: 一個基於較早版本 Spring Boot (如 1.5.x) 的框架，是許多現有專案的基礎。
2.  **Kapok**: 新一代的框架，基於 Spring Boot 3，並針對 Kubernetes 環境進行了優化，是目前新專案的首選。

這兩個框架都整合了公司開發所需的各種模組，但底層的 Spring Boot 版本和設計理念有所不同。本次教學將以純粹的 Spring Boot 3 為基礎，讓大家先掌握核心概念。

#### **3. 專案結構與實作**

解壓縮 `demo.zip` 後，使用 IntelliJ IDEA 開啟專案。Maven 會自動下載所需的依賴函式庫。

**核心檔案結構：**

```
demo
├── .mvn/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/demo/
│   │   │       └── DemoApplication.java  // Spring Boot 啟動類別
│   │   └── resources/
│   │       ├── static/
│   │       ├── templates/
│   │       └── application.properties    // 應用程式設定檔
│   └── test/
└── pom.xml  // Maven 專案設定檔
```

##### **3.1 設定 H2 資料庫 Console**

為了方便在開發時查看資料庫內容，我們可以啟用 H2 的網頁管理介面。在 `src/main/resources/application.properties` 中加入以下設定：

```properties
# 設定應用程式運行的 Port，避免預設的 8080 與其他服務衝突
server.port=8081

# 啟用 H2 Console
spring.h2.console.enabled=true

# 自訂 JDBC URL，給予一個固定的資料庫名稱 (例如 'test')
# 否則每次重啟，記憶體中的資料庫名稱都會是隨機的 UUID
spring.datasource.url=jdbc:h2:mem:test
```

##### **3.2 建立資料模型 (Entity)**

我們來建立一個簡單的 `SampleEntity` 作為範例。

1.  在 `com.example.demo` 套件下建立一個新的子套件 `jpa`。
2.  在 `jpa` 套件中新增一個名為 `SampleEntity.java` 的類別。

```java
package com.example.demo.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.Year;

@Entity
@Getter
@Setter
public class SampleEntity {

    @Id
    private Long id;

    @Column(length = 20)
    private String name;

    @Column(name = "amt", precision = 16, scale = 6)
    private BigDecimal amount;

    @Column(length = 5)
    @Enumerated(EnumType.STRING) // 推薦！將 Enum 以字串形式存入資料庫
    private SampleType type;

    private LocalDateTime time;
    private LocalDate date;
    private YearMonth yearMonth;
    private Year year;
}
```

**重點說明：**
*   `@Entity`: 標示這個類別是一個 JPA 實體，對應到資料庫中的一個資料表。
*   `@Id`: 標示 `id` 欄位是這個資料表的主鍵 (Primary Key)。
*   `@Getter` / `@Setter`: Lombok 的註解，自動生成所有欄位的 getter 和 setter 方法。
*   `@Column`: 用於定義欄位的細節。
   *   `length`: 對於字串類型的欄位，定義其長度。
   *   `name`: 自訂在資料庫中對應的欄位名稱。
   *   `precision` 和 `scale`: 用於定義 `BigDecimal` 的總位數和小數位數。
*   `@Enumerated(EnumType.STRING)`:
   *   **最佳實踐**：當欄位是 Enum 型別時，強烈建議使用 `EnumType.STRING`。這會將 Enum 的名稱 (如 "A", "B") 作為字串存入資料庫，可讀性高且不易出錯。
   *   **注意**：若使用預設的 `EnumType.ORDINAL`，存入的會是 Enum 的順序 (0, 1, 2...)。未來若在 Enum 中間插入新的值，會導致後續所有值的順序錯亂，引發嚴重的資料錯誤。

##### **3.3 建立資料存取介面 (DAO/Repository)**

在 `jpa` 套件中，新增一個名為 `SampleDao.java` 的介面 (Interface)。

```java
package com.example.demo.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SampleDao extends JpaRepository<SampleEntity, Long> {
    List<SampleEntity> findByName(String name);
}
```
*   `JpaRepository<SampleEntity, Long>`: 繼承此介面後，Spring Data JPA 會自動為 `SampleEntity` 提供一系列 CRUD (Create, Read, Update, Delete) 的標準方法。`Long` 指的是主鍵 `id` 的型別。
*   `findByName(String name)`: Spring Data JPA 的一大特色是「**約定優於配置 (Convention over Configuration)**」。只要遵循命名規則，它就能自動生成對應的 SQL 查詢。這個方法會被自動實作成一個根據 `name` 欄位查詢資料的功能。

##### **3.4 建立服務層 (Service)**

在 `com.example.demo` 下建立 `SampleService.java`。

```java
package com.example.demo;

import com.example.demo.jpa.SampleDao;
import com.example.demo.jpa.SampleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SampleService {

    private final SampleDao sampleDao;

    public List<SampleEntity> query(String name) {
        return sampleDao.findByName(name);
    }
}
```

*   `@Service`: 標示這是一個服務層的 Bean。
*   `@Transactional`: 宣告此類別下的所有公開方法都具備交易 (Transaction) 特性。若方法執行出錯，資料庫操作會自動回滾 (Rollback)。
*   `@RequiredArgsConstructor`: Lombok 的註解，會為所有 `final` 的欄位自動生成一個建構子 (Constructor)，這是實現**建構子注入 (Constructor Injection)** 的最佳方式。
*   **依賴注入 (Dependency Injection)**: 我們透過 `final SampleDao sampleDao;` 和建構子的方式，讓 Spring 容器自動將 `SampleDao` 的實例注入進來，而不是手動 `new` 一個物件。

##### **3.5 建立 API 端點 (Controller)**

在 `com.example.demo` 下建立 `http` 子套件，並新增 `SampleController.java`。

```java
package com.example.demo.http;

import com.example.demo.SampleService;
import com.example.demo.jpa.SampleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SampleController {

    private final SampleService sampleService;

    @GetMapping
    public String hello() {
        return "world";
    }

    @GetMapping("/query")
    public List<SampleEntity> query(String name) {
        return sampleService.query(name);
    }
}
```
*   `@RestController`: 這是一個組合註解，等同於 `@Controller` 和 `@ResponseBody`。它告訴 Spring 這是一個處理 RESTful API 的控制器，並且方法的回傳值會被自動序列化成 JSON 格式。
*   `@GetMapping`: 將 HTTP GET 請求映射到對應的方法。

#### **4. 執行與測試**

1.  **啟動應用程式**: 找到 `DemoApplication.java`，點擊 `main` 方法旁的綠色箭頭，選擇 "Run 'DemoApplication'"。
2.  **測試 API**:
   *   **Health Check**: 在瀏覽器中訪問 `http://localhost:8081/actuator/health`。您應該會看到 `{ "status": "UP" }` 的回應，代表應用程式已成功啟動並處於健康狀態。
   *   **Hello World**: 訪問 `http://localhost:8081/`，您會看到 "world" 的字串。
3.  **使用 H2 Console**:
   *   訪問 `http://localhost:8081/h2-console`。
   *   確保 **JDBC URL** 欄位填寫的是 `jdbc:h2:mem:test`。
   *   點擊 "Connect"。
   *   您會看到左側出現了 `SAMPLE_ENTITY` 資料表以及其所有欄位。您可以在右側的 SQL 編輯器中執行 SQL 語句來操作資料。

至此，您已經成功建立並啟動了一個具備基本功能的 Spring Boot 專案。這是一個堅實的起點，您可以基於此結構，繼續擴充更複雜的業務邏輯。