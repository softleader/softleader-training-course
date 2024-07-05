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
