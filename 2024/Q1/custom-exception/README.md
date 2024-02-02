# 自定義Excption 啥時有用

錄影檔: https://drive.google.com/file/d/1k6YIEtA9-iKtK1JF2Tb_M_EFtn6SqkrA/view?usp=drive_link


## 運行相關的Exception
- IllegalArgumentException
   > 輸入參數有誤
- IllegalStateException
   > 系統狀態(資料)不正確

## 安控相關的Exception
- CredentialException
   > 認證失敗
- IllegalAccessException
   > 授權不足

## 其他
- UnsupportedOperationException
   > 告知工程師, 這個方法還沒有實作

## 自訂義Exception
1. 為了使用獨立的欄位(field)記錄錯誤訊息的一些參數, 讓這個Exception可以被其他method利用(例如: 紀錄Log的service)
2. 為了區分出不同Exception需要做不同的額外處理(ControllerAdvice, Http Status, Multi-Catch)

