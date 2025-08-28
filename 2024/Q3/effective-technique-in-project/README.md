# 我在處理大量資料的服務中使用到的一些技巧
[錄影檔](https://drive.google.com/file/d/1pmDtfW7U53GfcMlskvb46D3Z74RT0aiq/view?usp=drive_link)

1. 12萬筆資料(4.4MB)的Excel匯入
https://github.com/pjfanning/excel-streaming-reader
```java
// before
Workbook workbook = WorkbookFactory.create(caInputStream);
```
```java
// after
Workbook workbook = StreamingReader.builder()
    .rowCacheSize(5000)
    .bufferSize(5242880)
    .open(caInputStream);
```



2. 規則查表計算
```java
public static final List<Integer> POS_6 = preparePos(6);

public static List<Integer> preparePos(int posCount) {
return IntStream.rangeClosed(0, (int) (Math.pow(2, posCount) - 1))
      .boxed()
      .sorted(Comparator
         .comparing(Integer::bitCount) // 1的數量越少排越前面
         .thenComparing(Comparator.naturalOrder()))
      .collect(Collectors.toList());
}
```
```java
for (Integer pos : KeyUtils.POS_6) {
  // 這邊用到的技巧是二進制比較
  // 從000000跑到111111, 其中變成1的部分就置換成ALL
  var maxRates = ratesByKey.get(toMatchKey.with(
      (pos & 0b1) == 1 ? ALL : distributor,
      ((pos >> 1) & 0b1) == 1 ? ALL : channel,
      ((pos >> 2) & 0b1) == 1 ? ALL : partner,
      ((pos >> 3) & 0b1) == 1 ? ALL : fundId,
      ((pos >> 4) & 0b1) == 1 ? ALL : premiumTerm,
      ((pos >> 5) & 0b1) == 1 ? ALL : policyTerm));
  if (maxRates != null) {
    return maxRates;
  }
}
```

3. 將搜索從迴圈轉為HashMap的使用技巧
```java
// Set<String> policyNos = payments.stream().map(p -> p.getPolicyNo()).toSet();
List<String> policyNos = payments.stream().map(p -> p.getPolicyNo()).distinct().toList();

// 直接查可能會為引數過多, 撞到OracleDB的 attr>1000 限制
// List<Polciy> policies = policyDao.findByPolicyNoIn(policyNos);
// Google guava 把List切成小的分區
Map<String, Polciy> policyByNo = Lists.partition(policyNos, 900).stream().map(policyDao::findByPolicyNoIn)
  .flatMap(List::stream).toMap(Policy::getPolicyNo, Function::identitiy);


for (Payment payment : payments) { // 20萬
   // policyDao.findByPolicyNo(payment.getPolicyNo()); // very bad
   // policies.stream().filter(policy -> policy.getPolicyNo().equals(payment.getPolicyNo())).findFirst() // bad
   Policy policy = Optional.ofNullable(policyByNo.get(payment.getPolicyNo()))
    .orElseThrow(() -> new IllegalArgumentException());
}
```

---

# `AI筆記`

在企業級應用開發中，處理大量資料和執行複雜的業務規則是常見的效能瓶頸。本文件將以一個真實情境為基礎——處理包含數十萬筆資料的 Excel 檔案並進行多條件佣金計算——深入探討三項核心的效能優化技巧。

#### **前言：問題的起源**

開發者經常面臨以下挑戰：
1.  需要從一個大型 Excel 檔案（例如，4.4MB，包含 12 萬筆資料）匯入資料進行處理。
2.  業務邏輯需要根據一個複雜的「規則查找表」來計算結果。
3.  處理過程中，多層迴圈導致執行時間過長，系統反應遲緩甚至崩潰。

本文件將帶您一步步拆解問題，並提供高效、可擴展的解決方案。

---

#### **第一部分：高效處理大型 Excel 檔案**

**1. 問題：記憶體溢位 (OutOfMemoryError) 的陷阱**

最直觀的 Excel 讀取方式是使用 Apache POI 的 `WorkbookFactory.create(inputStream)`。然而，這種方法會將整個 Excel 檔案的內容一次性載入到 Java 虛擬機 (JVM) 的堆內存 (Heap) 中。

*   **致命缺點**：當檔案體積龐大時，這種方式會消耗大量記憶體，極易引發 `java.lang.OutOfMemoryError`，導致應用程式崩潰。

**2. 解決方案：採用串流讀取 (Streaming)**

串流處理的核心思想是「逐行讀取、逐行處理」，而不是一次性載入所有內容。這極大地降低了記憶體的使用。

*   **推薦函式庫**：`excel-streaming-reader` 是一個基於 Apache POI 的優秀開源函式庫，它以 SAX (Simple API for XML) 模式解析 XLSX 檔案，實現了真正的串流讀取。

*   **Maven 依賴：**
    ```xml
    <dependency>
        <groupId>com.github.pjfanning</groupId>
        <artifactId>excel-streaming-reader</artifactId>
        <version>4.3.1</version> <!-- 建議隨時檢查並使用最新的穩定版本 -->
    </dependency>
    ```

**3. 程式碼實踐**

**不推薦的作法 (DOM 模式):**
```java
// 可能導致 OutOfMemoryError
try (InputStream is = new FileInputStream(new File("large-file.xlsx"))) {
    Workbook workbook = WorkbookFactory.create(is);
    // ... 此處已將整個檔案載入記憶體 ...
}
```

**最佳實踐 (Streaming 模式):**
```java
import com.github.pjfanning.xlsx.StreamingReader;

// ...

try (
    InputStream is = new FileInputStream(new File("large-file.xlsx"));
    Workbook workbook = StreamingReader.builder()
        // 在記憶體中快取多少行。這有助於處理需要回溯少量行的情況，
        // 預設值為 10，可依需求調整。
        .rowCacheSize(5000) 
        // 讀取檔案時的緩衝區大小 (bytes)。
        // 較大的緩衝區可以減少 I/O 次數，提升讀取效率。
        .bufferSize(5242880) // 5MB
        .open(is)
) {
    for (Sheet sheet : workbook) {
        System.out.println("Sheet: " + sheet.getSheetName());
        for (Row r : sheet) {
            // 逐行處理，記憶體使用量極低
            Cell cell = r.getCell(0);
            System.out.println(cell.getStringCellValue());
        }
    }
}
```
**核心 takeaways:**
*   **預測資料量**：在設計檔案匯入功能時，務必預估未來可能的資料量。
*   **選擇串流**：只要檔案可能超過數萬行，就應優先考慮串流讀取。
*   **資源管理**：務必使用 `try-with-resources` 語法，確保檔案和工作簿資源在使用後能被正確關閉，避免資源洩漏。

---

#### **第二部分：從 O(n*m) 到 O(n)，用 HashMap 優化規則查找**

**1. 問題：巢狀迴圈的效能災難**

假設我們需要為 20 萬筆交易紀錄，從 1 萬條規則中找到對應的佣金率。最直觀的寫法是雙層迴圈。

**不推薦的作法 (低效率的巢狀迴圈):**
```java
List<Payment> payments = ... // 20萬筆交易
List<Rule> rules = ...       // 1萬條規則

for (Payment payment : payments) {
    for (Rule rule : rules) {
        if (rule.matches(payment)) { // matches() 內部有多個欄位比對
            payment.setCommissionRate(rule.getRate());
            break; 
        }
    }
}
// 預估執行次數: 200,000 * (平均 5,000) = 10 億次比對
```
這種 `O(n*m)` 的複雜度會導致執行時間長達數小時，是不可接受的。

**2. 解決方案：空間換時間，預處理規則至 HashMap**

我們可以透過一次性的預處理，將規則列表轉換成一個 `HashMap`，從而將每次查找的時間複雜度從 `O(m)` 降至 **平均 O(1)**。

**3. 程式碼實踐**

**步驟 1: 定義一個唯一的 `RuleKey`**

`HashMap` 的 `Key` 必須能夠唯一代表一條規則。我們需要建立一個包含所有判斷條件的類別，並**務必**正確實作 `equals()` 和 `hashCode()` 方法，否則 `HashMap` 將無法正常工作。

```java
import java.util.Objects;

// 假設規則由通路、渠道、合作夥伴等欄位決定
public class RuleKey {
    private final String distributor;
    private final String channel;
    private final String partner;
    // ... 其他欄位 ...

    // 建議使用 Lombok 的 @Value 或 @Data 註解自動生成
    // Constructor, getters, equals, hashCode...

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RuleKey ruleKey = (RuleKey) o;
        return Objects.equals(distributor, ruleKey.distributor) &&
               Objects.equals(channel, ruleKey.channel) &&
               Objects.equals(partner, ruleKey.partner); // && ...
    }

    @Override
    public int hashCode() {
        return Objects.hash(distributor, channel, partner, ...);
    }
}
```

**步驟 2: 預處理規則列表**

在主邏輯執行前，將 `List<Rule>` 轉換為 `Map<RuleKey, Rule>`。

```java
import java.util.function.Function;
import java.util.stream.Collectors;

// 將 List 轉換為 Map，以利於快速查找
Map<RuleKey, Rule> ruleMap = rules.stream()
    .collect(Collectors.toMap(
        RuleKey::new, // 使用 Rule 物件建構 RuleKey
        Function.identity(),      // Value 就是 rule 物件本身
        // 處理重複 Key 的策略：此處保留舊值，並可加上日誌警告
        (existing, replacement) -> {
            log.warn("Duplicate rule key detected for: {}", existing);
            return existing; 
        }
    ));
```

**步驟 3: 高效查找**

```java
// 總時間複雜度從 O(n*m) 降至 O(n)
for (Payment payment : payments) {
    RuleKey keyToFind = new RuleKey(payment); // 從交易紀錄建立 Key
    Rule matchedRule = ruleMap.get(keyToFind); // 平均 O(1) 查找
    if (matchedRule != null) {
        payment.setCommissionRate(matchedRule.getRate());
    }
}
```
---

#### **第三部分：處理帶有萬用字元 (Wildcard) 的複雜規則**

**1. 問題：當 `Key` 不再是精確匹配**

在真實世界中，規則通常包含萬用字元（例如 `ALL`），代表「此欄位適用於所有值」。這使得我們無法再使用單一、精確的 `RuleKey` 來進行 `get()` 操作。業務需求通常是**優先匹配最精確的規則**。

**2. 解決方案：利用位元運算建立有序的查找策略**

我們可以將這個問題模型化：N 個條件欄位，每個欄位有兩種狀態：「精確值」或「萬用字元 (ALL)」。這正好對應了 N-bit 的二進位數字，`0` 代表精確，`1` 代表萬用。

**實踐步驟：**

1.  **建立查找順序列表**：
   *   我們的目標是產生一個從最精確（`000000`）到最通用（`111111`）的查找順序。
   *   這可以透過對 `0` 到 `2^N - 1` 的所有數字，**根據其二進位表示中 `1` 的數量** 進行升序排序來實現。
   *   `Integer.bitCount(i)` 這個內建函式可以高效地計算出一個整數的二進位中有多少個 `1`。

    ```java
    // 預先產生一個靜態的、排好序的查找順序列表
    private static List<Integer> prepareSearchOrder(int fieldCount) {
        return IntStream.range(0, (int) Math.pow(2, fieldCount))
            .boxed()
            .sorted(Comparator.comparingInt(Integer::bitCount))
            .collect(Collectors.toList());
    }

    // 在某處初始化
    private static final List<Integer> SEARCH_ORDER_6_FIELDS = prepareSearchOrder(6);
    ```

2.  **動態生成查找 `Key`**：
   *   建立一個函式，它接收原始交易資料和一個二進位「遮罩 (mask)」，並產生對應的 `RuleKey`。

    ```java
    private RuleKey createKeyWithMask(Payment payment, int mask) {
        // mask 是一個類似 0b001010 的數字
        // 判斷第 i 位是否為 1，若是，則將對應欄位設為 "ALL"
        String distributor = ((mask >> 0) & 1) == 1 ? "ALL" : payment.getDistributor();
        String channel     = ((mask >> 1) & 1) == 1 ? "ALL" : payment.getChannel();
        String partner     = ((mask >> 2) & 1) == 1 ? "ALL" : payment.getPartner();
        // ... 以此類推
        return new RuleKey(distributor, channel, partner, ...);
    }
    ```

3.  **執行有序查找**：
   *   對於每一筆交易，遍歷排好序的 `SEARCH_ORDER` 列表，動態生成 `Key` 並查找。

    ```java
    // 在處理每筆交易的迴圈中
    for (Payment payment : payments) {
        for (Integer mask : SEARCH_ORDER_6_FIELDS) {
            RuleKey dynamicKey = createKeyWithMask(payment, mask);
            Rule matchedRule = ruleMap.get(dynamicKey);
            if (matchedRule != null) {
                // 找到了最精確的規則
                applyRule(payment, matchedRule);
                break; // 立即跳出內層迴圈
            }
        }
    }
    ```

**核心 takeaways:**
*   **位元運算**：是處理多重開關、狀態組合和權限檢查等問題的強大工具，效能極高。
*   **預先計算**：將不變的查找順序預先計算並儲存為靜態常數，避免在每次迴圈中重複計算。
*   **從抽象到具體**：將複雜的業務規則（「最精確優先」）轉化為可計算的數學模型（位元 `1` 的數量），是解決複雜問題的有效思路。

#### **額外提示：處理資料庫 `IN` 查詢的限制**

在將大量 `Key`（如保單號碼）傳遞給資料庫進行 `IN` 查詢時，許多資料庫（特別是 Oracle）對 `IN` 子句中的元素數量有限制（通常是 1000）。

**解決方案：使用 `Lists.partition` (from Google Guava)**

Google 的 Guava 函式庫提供了一個方便的工具 `Lists.partition`，可以將一個大的 `List` 安全地分割成多個小的 `List`。

```java
import com.google.common.collect.Lists;

List<String> allPolicyNos = ... // 可能有數萬個保單號
int partitionSize = 900; // 安全起見，小於 1000

List<List<String>> partitionedNos = Lists.partition(allPolicyNos, partitionSize);

List<Policy> resultPolicies = new ArrayList<>();
for (List<String> subList : partitionedNos) {
    resultPolicies.addAll(policyDao.findByPolicyNoIn(subList));
}
```

這個技巧確保了即使處理大量 ID，也不會觸發資料庫的限制。

---

#### **總結**

本文件涵蓋了從 I/O 最佳化到 CPU 密集型演算法優化的完整流程：
1.  **處理大檔案**：使用 `StreamingReader` 避免記憶體溢位。
2.  **優化查找**：用 `HashMap` 將 `O(n*m)` 迴圈降維至 `O(n)`。
3.  **處理複雜規則**：用位元運算和有序查找策略解決萬用字元匹配問題。

這些不僅是解決特定問題的技巧，更是軟體工程中關於**效能、可維護性和問題建模**的重要思想。希望這些實戰經驗能幫助您在未來的開發工作中寫出更健壯、更高效的程式碼。