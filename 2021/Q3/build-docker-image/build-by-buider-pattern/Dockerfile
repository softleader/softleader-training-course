FROM maven:3.8.1-jdk-11-slim As build-env
WORKDIR /tmp/workspace
# dependency layer
COPY web-demo/pom.xml ./
RUN mvn dependency:resolve
# build layer
COPY web-demo/src ./src
RUN mvn package -DskipTests

FROM openjdk:11.0.12-jre-slim
WORKDIR /tmp/workspace
COPY --from=build-env /tmp/workspace/target/app.jar app.jar
EXPOSE 8080
CMD java -jar /tmp/workspace/app.jar