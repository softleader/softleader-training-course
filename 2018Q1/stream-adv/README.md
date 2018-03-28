## 2018Q1 Stream

### 重新回憶一下當年的 Stream 入門課
[Softleader Stream & Lambda介紹](https://github.com/softleader/softleader-training-course/blob/master/2016Q1/Stream%26Lambda.md)
1. 像是生產線一樣處理串流化的集合
2. 所有繼承自 `Collection` 的物件可直接呼叫
3. StreamAPI 中提供的方法主要區分為 `intermediate` 與 `terminal` 兩種
    - 需要呼叫 `terminal` 方法後，Stream 裡的處理邏輯才會被真正執行
4. 比起 for 迴圈，效能略差
---
### 大綱
#### CollectorSampleApp
1. 那些年我們都不認識的 Collectors
2. 用一個 grouping 做不到，你有沒有試過用兩個
3. 不寫註解，兩分鐘後連我自己都看不懂

#### MergeTxtsToTxtSampleApp
1. 單元化處理邏輯
2. 這是 peek 如果你還不認識，現在讓你看一看，很像 forEach 對吧
3. 有想過 reduce 的心情嗎？沒有！因為都只想到 collect

#### MergeTxtsToZipSampleApp
1. 只要用一行便能遍歷所有資料夾
2. 自己的 Collector 自己做
