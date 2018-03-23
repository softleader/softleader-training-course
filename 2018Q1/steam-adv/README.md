## 2018Q1 Stream

### 重新回憶一下當年的 Stream 入門課
[Softleader Stream & Lambda介紹](https://github.com/softleader/softleader-training-course/blob/master/2016Q1/Stream%26Lambda.md)
1. 像是生產線一樣處理串流化的集合
2. 所有繼承自 `Collection` 的物件可直接呼叫
3. StreamAPI 中提供的方法主要區分為 `intermediate` 與 `terminal` 兩種
    - 需要呼叫 `terminal` 方法後，Stream 裡的處理邏輯才會被真正執行
4. 比起 for 迴圈，效能略差

### 點一下今天的重點
1. 單元化的處理邏輯
2. 自定義 Collector
