# Spring-Web-102 講講Security

錄影: https://drive.google.com/file/d/1B1egV1uABZikfDuRciVPvS5r1pwLOLKo/view?usp=share_link

[sample專案](demo-2023-03-10.zip)

# 登入順序

1. 啟動 App, 連入 `http://localhost:8080/hello1` 發現401
2. 至 `http://adp-auth-tglife.118.163.91.247.nip.io/swagger-ui/index.html#/Token/grantAccessToken` 登入取得token
3. 使用 postman, 呼叫 `http://localhost:8080/hello1`
   - header Authorization = Bearer {token}
   - query string = name=XXX