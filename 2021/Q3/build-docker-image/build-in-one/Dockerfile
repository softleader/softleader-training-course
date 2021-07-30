FROM maven:3.8.1-jdk-11-slim
WORKDIR /tmp/workspace
COPY web-demo /tmp/workspace
RUN mvn package -DskipTests
CMD java -jar /tmp/workspace/target/app.jar