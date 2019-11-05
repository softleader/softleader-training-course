## 2018Q1 Stream

### 重新回憶一下當年的 Stream 入門課
[Softleader Stream & Lambda介紹](https://github.com/softleader/softleader-training-course/blob/master/2016/Q1/stream-and-lambda/Stream%26Lambda.md)
1. 像是生產線一樣處理串流化的集合
2. 所有繼承自 `Collection` 的物件可直接呼叫
3. StreamAPI 中提供的方法主要區分為 `intermediate` 與 `terminal` 兩種
    - 需要呼叫 `terminal` 方法後，Stream 裡的處理邏輯才會被真正執行
4. 比起 for 迴圈，效能略差
---
### 大綱
#### CollectorSampleApp
1. 那些年我們都不認識的 Collectors
    - `Collectors.groupingBy`
    - `Collectors.summingXXX`
    - `Collectors.toMap`
2. 用一個 grouping 做不到，你有沒有試過用兩個
    - `Map<KEY1, Map<KEY2, VALUE>>`

#### MergeTxtsToTxtSampleApp
1. 單元化處理邏輯
    - 抽成Method
    - 包裝 `java.util.Function`
2. 這是 peek 如果你沒看過，現在讓你看一看，很像 forEach 對吧
    ```
    list.stream().peek(x -> doSomeThing...).xxx...;
    ```
3. 有想過 reduce 的心情嗎？沒有！因為都只想到 collect
    ```
    list.stream().reduce((x1, x2) -> doSomeThing...);
    ```
    ```
    // normal max
    list.stream().max(Comparator.comparing(x -> x.getId()));

    // faster max
    list.stream().reduce((x1, x2) -> x1.getId() > x2.getId() ? x1 : x2);

    // no good max
    list.stream().collect(Collectors.maxBy(Comparator.comparing(x -> x.getId())));
    ```

#### MergeTxtsToZipSampleApp
1. 只要用一行便能遍歷所有資料夾
    ```
    Files.walk(path)
    ```
2. 自己的 Collector 自己做
    - `java.util.stream.Collector<T, A, R>`
        - `T`: 來源型別
            > 像是 toList, toMap 等都不指定，由傳入的型別處理
            > 而 joining 則特別指定必須是 CharSequence
        - `A`: 中間型別
            > 處理過程中, T 會先被處理成 A
            > 理應只有 Collector 內部才看得到
        - `R`: 最終輸出型別
