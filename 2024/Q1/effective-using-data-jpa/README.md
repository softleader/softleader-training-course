# 如何高效的使用Spring Data JPA

錄影檔1: https://drive.google.com/file/d/1b_sSzgRn-Xv6cuUe5Mffw52wB8U74os4/view?usp=drive_link
錄影檔2: https://drive.google.com/file/d/1-j3v2zasNX_L1LMQNhQe1ma8W5LjMsfC/view?usp=drive_link

## 查詢的時機
### 不要在迴圈內去做查詢 -> 使用Map
- 不好的做法
```
policyNos.forEach(policyNo -> {
    Optional<Policy> policy = policyDao.findByPolicyNo(policyNo); // <--這裡會有很差的效能, 每次都要花 70ms
    // do something with policy
})
```
- 好的做法
```
Map<String, Policy> policyByPolicyNo = policyDao.findByPolicyNoIn(policyNos).stream()
    .collect(Collectors.toMap(Policy::getPolicyNo, Function.identity()));

policyNos.forEach(policyNo -> {
    Optional<Policy> policy = Optional.ofNullable(policyByPolicyNo.get(policyNo)); // <-- 使用 2934ms
    // do something with policy
 })
```
> 不只可用在Policy這類持續成長的table, 也可以用在設定檔(例如說code value, 或者是ProductCode), 以8G的記憶而言, 一次可以處理的資料筆數在40萬筆以下(超過可能會OOM)

## 查詢的語句
- 盡量去利用index
  - 資料量 > 10000筆 盡量去建index
    - 有做Where,GroupBy,OrderBy的都去做index
    - 有做Join的都去做index(FK)
  - 資料量 <= 10000筆 全表查詢會比index查詢還快
- 盡量避免like語句, 若無法避免, 盡量與index的欄位一起做查詢
- 盡量避免反向查詢條件, 例如 not, !=, <>
- 完全避免再where條件中使用函式, 例如說 `where upper(code) = ?1`

## 傳遞資料量
- 盡量做Projection
  - interface base
  ```properties
  select
    p.policyNo as policyNo
  from PolciyEntity
  ```
  - class base
  ```properties
  select new tw.com.soflteader.PolicyVo(p.policyNo, ...)
  from PolciyEntity 
  ```

- 盡量使用分頁
  - 其實做分頁有時候反而會降低效能, 如果有效能需求, 應避免使用Page, 改回用List就好(例如瀑布流)
  > 主要發生在資料量很大的Table, 主因是為了做分頁統計, 會需要進行幾乎全表的count(*)