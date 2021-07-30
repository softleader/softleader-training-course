FROM openjdk:11.0.12-jre-slim
WORKDIR /tmp/worksapce
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
CMD java -jar app.jar