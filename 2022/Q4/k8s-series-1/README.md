# 給只知道Tomcat的人一步步開始了解Docker跟Container

錄影: 忘了錄影QQ

[sample專案](./war-sample.zip)

有用到的指令
```shell
docker cp "C:\develop\projects\tomcat-8.5\webapps\demo.war" "6f3fdbc3de32:/opt/tomcat/webapps/"


docker run -p 8081:8080 tomcat:8.5-jdk17
docker cp "C:\develop\projects\tomcat-8.5\webapps\ROOT\index.html" "8f2d4e5c1eb2:/usr/local/tomcat/webapps/ROOT/"
docker cp "C:\develop\projects\tomcat-8.5\webapps\demo.war" "8f2d4e5c1eb2:/usr/local/tomcat/webapps/"
docker exec -it <container_id> sh

docker run -p 8081:8080 -v "C:\develop\projects\tomcat-8.5\webapps:/usr/local/tomcat/webapps/" tomcat:8.5-jdk17


harbor.softleader.com.tw/library/zulu-openjdk-alpine:17-jre-taipei
```