# 松凌科技專案環境
https://github.com/softleader/softleader-microservice-wiki/wiki

## Docker Swarm (SIT-SWARM)
   - deployer: http://192.168.1.60:5678/dashboard
   - swarm level
     1. node
     2. stack(Project)
     3. service
     4. container(APP)
     - build
       1. mvn clean compile
         > 編譯出class
       2. mvn clean package
         > 包出jar/war, 執行test
       3. mvn clean package -U -DskipTests
         > 包出jar/war, 不執行test, force dependency update
   - cicd-sit: https://jenkins.softleader.com.tw/
   - cicd-rd: https://softleader.com.tw:48080/
   - package.yml: https://github.com/softleader/softleader-package

## Monitor
   - http://softleader.com.tw:23000/
## Logging
   - http://softleader.com.tw:15601/app/kibana#/home?_g=()
     - 看logstash*的index
## Redis
## LDAP
## SLKE (SIT-K8S)
   - https://github.com/softleader/slke
   - 部屬: https://github.com/softleader/softleader-charts