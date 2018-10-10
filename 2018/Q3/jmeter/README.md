## Jmeter功能介紹


### JDBC測試
1. 先確認DB的驅動是否在jmeter的lib(libexec/lib)中
2. 新增Thread Group
3. 新增JDBC Connection Configuration(參數通常可參考專案中的datasource.properties)
4. 新增JDBC Request(下SQL的地方)
5. 新增View Results Tree

### 流程控制
1. for
2. if
3. while

### LDAP測試
1. 使用Docker建立openLdap
2. 自訂連線資訊
3. 測試query
4. 測試新增
5. 測試刪除
6. 新增LDAP Request


## 實際找一專案測試
### Response 解析
1. CSRF_TOKEN
2. SESSION

### 內建的好用函數
options/function help dialog


宣告/使用變數
宣告/使用函數
${__log())
${__time())
${__intSum(5, 3, result)}
${__intSum(${result}, 2)}
${__javaScript(邏輯, 變數名稱)}
"productTypeId":${__javaScript(${productTypeCodeIdMap}.filter(function(ele) ele.code == '${productTypeCode}').map(function(ele) ele.id)[0])},

### 測試案例 應用
使用Google Spreadsheet
1. 共同檢視
    - 透明的資料結構 測試與開發之間的溝通成本
2. 線上編輯 
    - 直覺、快速

https://docs.google.com/spreadsheets/d/1rYSHSoUfw6ZSGL4YyTK1K79gxxd16lxmFDMmKnT_ssc/edit?usp=sharing
CSV、JSON 轉換 
http://www.convertcsv.com/json-to-csv.htm


###用Command line執行你的Jmeter測試計畫

1. 步驟
    1. 建立一個全新的csv當作結果log  
    ```
    touch /Users/chenhaoxian/Downloads/jmeter_cmd.csv
    ```
    2. 建立一個簡單的測試計畫

    3. 打開command line 視窗下指令  
    ```
    chenhaoandeMBP2:bin chenhaoxian$ sh jmeter -n -t /Users/chenhaoxian/Downloads/AH.jmx -l /Users/chenhaoxian/Downloads/jmeter_cmd.csv
    ```  

2. 說明
    - sh: linux (windows不需要)
    - -n: none GUI mode
    - -t: location of jmeter script
    - -l: location of result file

3. 執行結果  
```
WARNING: package sun.awt.X11 not in java.desktop
Creating summariser <summary>
Created the tree successfully using /Users/chenhaoxian/Downloads/AH.jmx
Starting the test @ Thu Aug 23 23:17:34 CST 2018 (1535037454760)
Waiting for possible Shutdown/StopTestNow/Heapdump message on port 4445
summary +     26 in 00:00:25 =    1.0/s Avg:   971 Min:    16 Max:  7904 Err:     0 (0.00%) Active: 1 Started: 1 Finished: 0
summary +     19 in 00:00:28 =    0.7/s Avg:  1486 Min:    10 Max:  9316 Err:     0 (0.00%) Active: 0 Started: 1 Finished: 1
summary =     45 in 00:00:54 =    0.8/s Avg:  1188 Min:    10 Max:  9316 Err:     0 (0.00%)
Tidying up ...    @ Thu Aug 23 23:18:28 CST 2018 (1535037508858)
... end of run
```  
更多選項
chenhaoandeMBP2:bin chenhaoxian$ sh jmeter -h
指令查詢
chenhaoandeMBP2:bin chenhaoxian$ sh jmeter -?







### 下載jmeter plugins manager
1. 下載[plugins-manager.jar](https://jmeter-plugins.org/get/)
2. 將下載完的JAR丟到lib/ext(libexec/lib/ext)
3. 重開jemeter
4. 以後options裡面會有icon

  Available plugins
  step by step debug