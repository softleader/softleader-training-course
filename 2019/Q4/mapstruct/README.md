## MapStruct - Java bean mappings [WIP]

我們在專案中時常會需要複製 Java Bean, 也許是為了 Call Api 需要跟 DTO 互轉, 或是要轉成 VO 丟給某些頁面來使用, 簡單點我們會使用 `BeanUtils.copyProperties(target, source)` 來做, 但這類型的 `copyProperties` 幾乎都是透過 reflection 來存取 Object, 但用 reflection 的效能真的很慢, 這邊我們推薦使用 [MapStruct](https://mapstruct.org/) 來取代 `copyProperties`

MapStruct 基於 [JSR 269](https://www.jcp.org/en/jsr/detail?id=269) 來幫你產生對應的程式, 官方提到的優點包含了:

- 在 compile 的階段就可以知道程式撰寫錯誤, 不用等到 runtime
- 超級好的效能
- 沒有額外的 runtime lib 依賴需求

延伸閱讀: 

- [MapStruct Reference Guide](https://mapstruct.org/documentation/stable/reference/html/)
- [Performance of Java Mapping Frameworks](https://www.baeldung.com/java-performance-mapping-frameworks)
- [Java-JSR-269-插入式註解處理器](https://liuyehcf.github.io/2018/02/02/Java-JSR-269-%E6%8F%92%E5%85%A5%E5%BC%8F%E6%B3%A8%E8%A7%A3%E5%A4%84%E7%90%86%E5%99%A8/)


## Setup w/ Lombok

公司的專案大多都有使用 [Project Lombok](https://projectlombok.org/), MapStruct 亦可整合一起使用, 只要在 `pom.xml` 中加入依賴即可:

```xml
...
<properties>
    <mapstruct.version>1.3.1.Final</mapstruct.version>
    <lombok.version>1.18.0</lombok.version>
	<m2e.apt.activation>jdt_apt</m2e.apt.activation>
</properties>
...
<dependencies>
    <dependency>
        <groupId>org.mapstruct</groupId>
        <artifactId>mapstruct</artifactId>
        <version>${mapstruct.version}</version>
    </dependency>
    <dependency>
      <groupId>org.mapstruct</groupId>
      <artifactId>mapstruct-processor</artifactId>
      <version>${mapstruct.version}</version>
      <optional>true</optional>
    </dependency>
    <dependency>
      <groupId>org.projectlombok</groupId>
      <artifactId>lombok</artifactId>
      <version>${lombok.version}</version>
      <optional>true</optional>
    </dependency>
</dependencies>
...
```

> 請確保使用版本不可低於: MapStruct 1.2.0.Beta1 及 Lombok 1.16.14


## QuickStart



