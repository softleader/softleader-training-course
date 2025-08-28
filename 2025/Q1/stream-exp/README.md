# 講講我們目前怎麼用Stream

[錄影檔](https://drive.google.com/file/d/1iP4WYI_NZ9VlAbiH4vaxFOTpQ862TNeH/view?pli=1)

## 特點
- 處理 Collection (轉型、篩選、分組)
- 一種語法糖 (For迴圈)
- 函數式編程 (Functional Programing)
- 通常會跟 lambda 一起使用
- JDK8

## 痛點舉例
- DTO -> Entity
- 使用 for 迴圈
```java
public List<SampleEntity> toEntity(List<SampleDto> dtos) {
  var entities = new ArrayList<>();
  for (var dto : dtos) {
    var entity = mapping(dto);
    entities.add(entity);
  }
  return entities;
}
```

- 使用stream
```java
public List<SampleEntity> toEntity(List<SampleDto> dtos) {
  return dtos.stream()
      .map(this::mapping)
      .toList();
}
```


## Lambda
- f(x) = x -> x + 1
- 某輸出 = ( `某輸入 -> 某件事` )
```
1. x -> x + 1
2. (int x) -> x + 1
3. (int x) -> {
      return x + 1
   }
4. this::treatX
int treatX(int x) {
   return x + 1;
}

```