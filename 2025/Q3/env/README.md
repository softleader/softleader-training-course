# 變數與注入的幾種方式整理

[錄影檔](https://drive.google.com/file/d/1hE2xxgRzwuvJCWv9_kqzdz0vBpYCXYXT/view?usp=drive_link)

1. 抽變數的幾種方式
  - static
  - @Value
  - Properties Object
2. 變數檔案安排
  - profile
  - 依照環境
  - 依照系統的能力
3. 注入變數的幾種方式
  - Spring 對 System Env name 的轉換 (ex: HELLO_NAME => hello.name)
  - 挖洞方式
  - config import

> [!TIP]
> 延伸閱讀: [Kapok Config Order](https://kapok.cloud.softleader.com.tw/docs/modules/core/config/#config-ordering), [Externalized Configuration](https://docs.spring.io/spring-boot/reference/features/external-config.html)
