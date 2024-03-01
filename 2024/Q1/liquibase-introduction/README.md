# Liquibase 教育訓練

錄影檔:https://drive.google.com/file/d/1NOPonNRnX33elilZHjuHENbPOJ_L5lHp/view

## Data Migration

資料遷移（Data Migration）是將資料從一個系統轉移到另一個系統的過程，這個過程中可能涉及到不同的格式轉換、資料庫、儲存技術，或是應用程式。資料遷移通常在企業升級其系統、整合不同資料來源，或是轉移到雲端服務時進行。

### 從Spring Common Properties 看分類

[Data Migration Properties](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#appendix.application-properties.data-migration) 分別有`spring.flyway`, `spring.liquibase`, `spring.sql.init`三種，其中的flyway, liquibase是目前最常用的資料庫版本控制工具，提供完整的解決方案來管理資料庫變更和遷移。另一個`spring.sql.ini`則是用於配置 SQL 資料庫的初始化，當APP啟動時能夠自動執行 SQL 腳本，來初始化數據庫結構（例如建表）或數據。這對於開發和測試環境特別有用，可以確保每次應用啟動時都有一個已知的數據庫狀態。

### 使用資料庫版本控制工具的目的

### (Hibernate DDL-AUTO不好嗎)

Liquibase/Flyway提供了一套完整的解決方案來管理資料庫變更和遷移，從而幫助團隊提高生產效率，降低因手動操作導致的錯誤風險，並確保資料庫結構的一致性和穩定性。

**把~~資料庫~~→程式抽換一下**

Git提供了一套完整的解決方案來管理~~資料庫~~程式碼變更和遷移，從而幫助團隊提高生產效率，降低因手動操作導致的錯誤風險，並確保~~資料庫~~程式碼結構的一致性和穩定性。

## Liquibase基本介紹

1. **廣泛的資料庫支持**：Liquibase 支持多種資料庫系統，包括 MySQL、PostgreSQL、SQL Server、Oracle 等，讓開發團隊可以輕鬆地在不同的資料庫平台上使用。

2. **靈活的變更記錄格式**：Liquibase 允許開發者使用 XML、YAML、JSON 甚至 SQL 腳本來定義資料庫變更，增加了操作的靈活性。

   → 使用一套資料庫版控機制處理多個資料庫，有SQL專屬語言也可以直接下SQL處理。

3. **版本控制和Rollback機制**：提供了強大的版本控制功能，允許開發者追蹤每一次的資料庫變更，並且在需要時可以輕鬆地回滾至之前的版本。

   → 類似於Git發生merge conflict衝突時，提早處理，也有類似於branch, cherry-pick等\
   功能。

4. **社群和文檔支持**：擁有活躍的開發者社群和豐富的文檔資源，幫助使用者解決問題和學習如何有效使用工具。

   → JPA-Buddy有支援Liquibase ChangeLog生成功能。
## Before we start…

常用文件

   - Liquibase Document: <https://docs.liquibase.com/start/home.html>

   - Introduction to Liquibase：基本介紹

   - ChangeLog：ChangeLog格式介紹

   - TrackingTables：資料庫版本控制表

   - Commands-Update：Spring Liquibase支援Command

   - ChangeTypes: ChangeSet的SQL語法

- Spring Common Properties: <https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#appendix.application-properties.data-migration>

- Using Liquibase with Spring Boot: <https://contribute.liquibase.com/extensions-integrations/directory/integration-docs/springboot/springboot/>

## Hello Liquibase

### Prerequisite

- [Kapok Initializr](https://start-kapok.cloud.softleader.com.tw/index.html): 3.2.11

   - Liquibase

   - Data JPA 

   - h2

   - Web\*

### Start-with h2

- make compile

- add data source

- disable liquibase: `spring.liquibase.enabled=false`

- generate sql by hibernate

   ```bash
   spring.jpa.hibernate.ddl-auto=create
   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.format_sql=true
   ```

- Write some entities: Student, Course

- Check tables on h2-console: 2 tables (Student, Course)

### Prepare baseline changeset 

- enable liquibase: `spring.liquibase.enabled=true`

- write changeset: SQL version

- Update 產生Tracking Table

- Check tables on h2-console: 4 tables (Student, Course, CHANGELOGTABLE, CHANGELOGLOCKTABLE)

- modify changeset in yaml: using JPA buddy

## Ready to data migration

- 安裝Docker MySQL

   ```bash
   # 啟動 MySQL 容器
   docker run --name my-mysql \
       -e MYSQL_ROOT_PASSWORD=my-secret-pw \
       -e MYSQL_DATABASE=mydatabase \
       -e MYSQL_USER=myuser \
       -e MYSQL_PASSWORD=mypassword \
       -p {3307}:3306 \
       -d mysql:8.3.0
   
   # 輸出提示信息
   echo "MySQL 容器已啟動"
   ```

   ```yaml
   version: '3.8'
   services:
     my-mysql:
       image: mysql:8.3.0
       container_name: my-mysql
       environment:
         MYSQL_ROOT_PASSWORD: my-secret-pw
         MYSQL_DATABASE: mydatabase
         MYSQL_USER: myuser
         MYSQL_PASSWORD: mypassword
       ports:
         - "3306:3306"
       restart: unless-stopped
   ```

- 加入MySQL Dependency

    ```xml
        <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <scope>runtime</scope>
        </dependency>
    ```

- Spring Properties

   ```yaml
   spring:
     datasource:
       url: jdbc:mysql://localhost:3306/mydatabase
       username: myuser
       password: mypassword
   ```

- Run APP → Data migration’s successful

## Do some changes

- Modify table structure

    ```java
    // Course
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "student_id")
        private Student student;

    // Student
        @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
        private List<Course> courses;
    ```

- Use JPA Buddy to generate diff changeSet

- Run APP → Check table structure

## Kapok Liquibase Command 補充

* validate: someone changes your changeSet

* spool feature: record all changes in

* deploy: start-kapok init container migration

## References
* [Generate Liquibase Changelog](https://github.com/softleader/kapok/wiki/Generate-Liquibase-Changelog)

* [Spring Common Properties](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#appendix.application-properties.data-migration)

* [Using Liquibase with Spring Boot](https://contribute.liquibase.com/extensions-integrations/directory/integration-docs/springboot/springboot)
