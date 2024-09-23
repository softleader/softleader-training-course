# Java 專案起手式 (Kyle)

# 快速應對 Java 應用程式中的常見漏洞 (Sarah)

# 因為種種原因變成Rhys講
[錄影檔](https://drive.google.com/file/d/15ZG6OmuAFQ3kn0CUOP8yjYe7Sj_EEUWL/view?usp=drive_link)

- 小筆記
```
Java 專案起手式
   Spring boot 3
   JDK 21
   公司既有的FW: Kapok
   甲方專案需求, 建立自己的FW: e.g. SpringBoot2+JDK17
   預算比較低, 有全部的 source code 轉移需求: e.g. Mimosa(精簡) <- ROSE(完整)(第2代for MircoService FW SpringBoot2+JDK11)
   沒有特別討論: 原則上能新就新
   
   架構
   UI+APP+DB (三分離)(x client-server)
   UI+APP+CacheDB(Redis)+DB
   UI+APP+CacheDB(Redis)+DBs(ReadDB+WriteDB+CDC)
   Kubernetes
      Replica要幾份: 通常取決於甲方口袋多深(因為K8S平台的大小跟費用成正比)
      OCP: 訂閱費用的計算模式 = vCPU x 單價 x 時間
      不適合於PROD環境使用: ICP, Docker-Swarm -> Docker-Compose(微型系統可用)
   Web container
      Jboss EAP, WAS-CE
      根據甲方口袋深度>甲方想要的管理模式
   
   前端
   Vue(比較好學..的感覺), React, Angular
   Vue3+vite+nuxt
   根據是否能方便專案開發>安全性>甲方需求
   (根據情形, 甲方需求為第一位的情況也是會發生的)
   
   CICD
   Git+Jenkins+K8s+EFK
   DevOps
   Git+Jenkins+K8s+GrafanaPrometheus+EFK
   
   專案所需最低硬體數量
   以前
   硬體x1:Tomcat, 日誌寫在那台機器上
   現在
   硬體x6:K8Sx5(3m2w), 日誌監控x1
   硬體x1:docker-compose


快速應對 Java 應用程式中的常見漏洞
   SQL 注入 (發生組字串的方式來組SQL "select * from xxx where " + whereCondition)
   "select count(*) from user where user_id = " + uid + " and password = " + pwd;
   uid = 1
   pwd = xxx or 1 = 1
   select count(*) from user where user_id = 1 and password = xxx or 1 = 1;
   PrepareStatement
   "select count(*) from user where user_id = ?1 and password = ?2;"
   
   XSS（跨站腳本攻擊）
   老牌討論區常用做法:以自定義語法(如BBCode)取代Javascript (<b></b> -> [B][/B])
   以 Html encode 來取代特殊字元 < > / -> &lt; &gt; 
      replace時間點: java servlet response or java servlet request
      ex: servlet filter
   
   反序列化漏洞
   以我們的例子: JSON字串 -> Java Object, 透過使用成熟的Lib:Jackson來避免此問題
   
   缺乏適當的身份驗證和授權
   SSO or Zero Trust(零信任: 身分驗證+設備驗證+多重驗證(TOTP, 生物驗證))
```