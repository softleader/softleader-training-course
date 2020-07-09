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
> ### 需注意, 有MapperClass存在的專案, 無論其專案下引用的jar是否曾經包含過, 本身都必須親自引用 `mapstruct-processor`, 否則不會正常compile出實作, 將會發生 NoSuchMethod 或者是 ClassNotFound

## Softleader Guide

### 撰寫規範

要點:
1. Mapper 的 Class 宣告以其中一方作為標的(v26而言, 推薦以Entity作為標的宣告, 如 PosPolicyEntityMapper), 原則上一個Domain只有一個MapperClass
	> (舊) Mapper 以 `@org.mapstruct.Mapper public interface Mapper` 宣告於所屬 class 下  
	> 因考量到專案的 Entity Field 欄位數量較多, 因此此種方法閱讀不易
2. Mapper Instance 使用 `Mapper INSTANCE = Mappers.getMapper(Mapper.class);` 不註冊至 spring
3. Method 的宣告, 以以下 pattern 為主, 以下以PosPolicy 為例
	```java
	// from 系列可精簡宣告 
	PosPolicyEntity from(PosPolicyRequest)
	PosPolicyEntity from(PosPolicyDto)
	PosPolicyEntity from(QotPolicyEntity)
	// from 的對象如果是 list 則沒辦法精簡宣告
	List<PosPolicyEntity> fromRequests(List<PosPolicyRequest>)
	List<PosPolicyEntity> fromDtos(List<PosPolicyDto>)
	// to 系列一律進行完整宣告
	PosPolicyRequest toRequest(PosPolicyEntity)
	PosPolicyDto toDto(PosPolicyEntity)
	List<PosPolicyRequest> toRequests(List<PosPolicyEntity>)
	List<PosPolicyDto> toDtos(List<PosPolicyEntity>)
	// copy 系列可精簡宣告
	void copy(PosPolicyEntity, @MappingTarget PosPolicyEntity)
	void copy(PosPolicyRequest, @MappingTarget PosPolicyEntity)
	void copy(PosPolicyDto, @MappingTarget PosPolicyEntity)
	// 如果是是相關 Domain 但是跟標的 Class 無直接關係 改用 convert 關鍵字命名, 可精簡宣告
	PosPolicyRiskEntity convert(PosPolicyRiskRequest)
	PosPolicyRiskItemEntity convert(PosPolicyRiskItemRequest)
	```

`Mapper作為InnerClass此為舊版規範, 可無視`
由於公司的Entity, Vo數量眾多, 為了方便管理, 以及避免重複造輪等理由, 建議將Mapper以InnerClass的形式進行撰寫在 Entity or Vo class內  
例:

```java
@Getter
@Setter
@ToString(callSuper = true)
public class FinancePayInfoRequest extends FinancePayInfoDto {

  /** 財務給付通知書 */
  private List<FinanceNoticeRequest> notices = Lists.newArrayList();

  /** 財務給付記錄 */
  private List<FinancePaidLogRequest> paidLogs = Lists.newArrayList();

  /** 財務受款人資料 */
  private List<FinanceReceiverInfoRequest> receiverInfos = Lists.newArrayList();

  /** 賠付對象資料 */
  private List<ClmPaymentRequest> payments = Lists.newArrayList();

  @org.mapstruct.Mapper
  public interface Mapper {
    Mapper INSTANCE = Mappers.getMapper(Mapper.class);

    @Mapping(target = "notices", ignore = true)
    @Mapping(target = "paidLogs", ignore = true)
    @Mapping(target = "receiverInfos", ignore = true)
    FinancePayInfoRequest from(FinancePayInfoData bean);
  }

  @org.mapstruct.Mapper
  public interface NoIdMapper {
    NoIdMapper INSTANCE = Mappers.getMapper(NoIdMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdTime", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "modifiedTime", ignore = true)
    void update(FinancePayInfoData source, @MappingTarget FinancePayInfoRequest target);
  }

}
```
使用時
```java
FinancePayInfoRequest copyPropertiesToRequest(FinancePayInfoData data){
  return FinancePayInfoRequest.Mapper.INSTANCE.from(data);
}
```

### Samples

1. 型態變換 A to B
    ```java
    FooEntity from(FooVo source);
    ```

2. B 已存在的情況下, 將 A 的欄位複製 to B
    ```java
    void from(FooVo source, @MappingTarget FooEntity target);
    ```

3. 複製的過程中, 需要略過某些欄位 ignore `A.id`
    ```java
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdTime", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "modifiedTime", ignore = true)
    FooEntity from(FooVo source);
   
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdTime", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "modifiedTime", ignore = true)
    void update(FooVo source, @MappingTarget FooEntity target);
    ```

4. 要複製的欄位名稱不同 `A.localName` to `B.name`
    ```java
    @Mapping(source = "localName", target = "name")
    FooEntity from(FooVo source);
    ```

5. 需要略過的欄位在更下層的位置 ignore `A.B.id`
    ```java
    @Data
    class BooEntity {
      private Long id;
      private String name;
    }
   
    @Data
    class FooEntiy {
      private Long id;
      private List<BooEntity> boo;
   
      @org.mapstruct.Mapper
      public interface Mapper {
        Mapper INSTANCE = Mappers.getMapper(Mapper.class);
   
        FooEntiy from(FooVo source);
        List<BooEntity> from(List<BooVo> source);

        @Mapping(target = "id", ignore = true)
        BooEntity from(BooVo source);
      }
    }
    ```
    > 同一個Mapper下, 若欄位之間是有關連的, 會優先使用Mapper底下定義的複製方式
    > 但是前提條件是中間的每一層都必須寫出來, 且 List 跟 Bean 的複製要分開來各寫一次
    > FooEntity > List<BooEntity> > BooEntity

6. 由於DB雙向關聯的關係, 造成複製遞迴 `A.B.A.B.A.....`
    ```java
    
    @Data
    class BooEntity {
      private Long id;
      private FooEntity foo;
    }
   
    @Data
    class FooEntiy {
      private Long id;
      private List<BooEntity> boo;
   
      @org.mapstruct.Mapper
      public interface Mapper {
        Mapper INSTANCE = Mappers.getMapper(Mapper.class);

        FooEntiy from(FooVo source, @Context MapStructUtils.CycleAvoidingContext context);
      }
    }
    ```
    ```java
    public class MapStructUtils {
    
      /**
       * 避免遞迴的關係造成無限迴圈的Mapping
       * 物件內若有遞迴的關係發生時，可使用此class避免，使用細節參考下列網址範例
       * https://github.com/mapstruct/mapstruct-examples/tree/master/mapstruct-mapping-with-cycles
       */
      public static class CycleAvoidingContext {
        private Map<Object, Object> knownInstances = new IdentityHashMap<>();
    
        @BeforeMapping
        public <T> T getMappedInstance(Object source, @TargetType Class<T> targetType) {
          return (T) knownInstances.get( source );
        }
    
        @BeforeMapping
        public void storeMappedInstance(Object source, @MappingTarget Object target) {
          knownInstances.put( source, target );
        }
      }
    }
    ```
    > 這 class 在 jasmine-common 有
    
    使用:
    ```java
    FooEntity foo = FooEntity.Mapper.INSTANCE.from(fooVo, new MapStructUtils.CycleAvoidingContext());
    ```
    > 呼叫的時候, 將 `MapStructUtils.CycleAvoidingContext` new 出來
    > 需注意這個 instance 是不能共用的, 每當需要時就得 new 一個

7. 複製出的物件同時有兩個以上的來源時
    ```java
    // 欄位攤在裡面的情況
    // FooBarDto.fooCode = foo.code, FooBarDto.barCode = bar.code
    @Mapping(source = "foo.code", target = "fooCode")
    @Mapping(source = "bar.code", target = "barCode")
    FooBarEntity from(FooVo foo, BarVo bar);
    ```
    
    ```java
    // 來源本身就是其中一個field的情況
    // FooBarDto.foo = foo, FooBarDto.bar = bar
    @Mapping(source = "foo", target = "foo")
    @Mapping(source = "bar", target = "bar")
    FooBarDto from(FooVo foo, BarVo bar);
    ```

8. 物件下的某欄位其型別已有該型別的Mapper, 可以直接引用 `Foo.bar`, 不用再寫一次關於該型別的轉換
    ```java
    @org.mapstruct.Mapper(imports = BarMapper.class)
    public interface FooMapper {
      FooMapper INSTANCE = Mappers.getMapper(FooMapper.class);
      FooDto from(FooEntity source);
    }
    ```
    > 需注意這種情況, 兩個Mapper的ClassName不能相同(會與我們一開始定的規則牴觸, 所以遇到這種情況, 建議不要寫InnerClass, 直接拉出來獨立寫, 並且確實命名(例 FooDtoMapper, BarEntityMapper 等)

9. 物件裡的欄位有比較複雜的轉換邏輯時 `Foo.bars = new HashMap<>()`
    ```java
    @org.mapstruct.Mapper(uses = BarMapperDef.class)
    public interface FooMapper {
      FooMapper INSTANCE = Mappers.getMapper(FooMapper.class);
      FooDto from(FooEntity source);
    }
    @org.mapstruct.Mapper
    public interface BarMapper {
      BarMapper INSTANCE = Mappers.getMapper(BarMapper.class);
      BarDto from(BarEntity source);
    }
    
    public class BarMapperDef {
      Map<Integer, List<BarDto>> mappingBarMap(Map<Integer, List<BarEntity>> source) {
        Map<Integer, List<BarDto>> result = Maps.newHashMap();
        for (Entry<Integer, List<BarEntity>> entry : source.entrySet()) {
          result.put(entry.getKey(), BarMapper.INSTANCE.from(entry.getValue()));
        }
        return result;
      }
    }
    ```
    > 當 Mapper 發現 user 所定義的 class 裡, 有出現跟轉換型別一樣的 input 的 method 時, 會自動使用該 method 進行轉換

10. 物件內有欄位的型別轉換時 `Money to BigDecimal`
    ```java
    @org.mapstruct.Mapper
    interface Mapper {
      Mapper INSTANCE = Mappers.getMapper(Mapper.class);

      FooVo to(FooEntity source);

      default BigDecimal convert(Money money) {
        return Optional.ofNullable(money).map(Money::getNumberStripped).orElse(null);
      }
    }
    ```
    > 其實就是當作Mapper的對應邏輯之一, 只是因為不是值的1:1轉換, 因此直接寫成預設實作來處理
