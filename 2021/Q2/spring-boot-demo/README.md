# spring-boot-demo

## 包版

### 包jar
`mvn clean package`
- 跑jar: `java -jar demo-0.0.1-SNAPSHOT.jar`

### 包image
`mvn clean spring-boot:build-image`
- 跑image: `docker run -p:18080:18080 -d harbor.softleader.com.tw/training/spring-boot-demo`