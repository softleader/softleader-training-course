FROM maven:3.5-jdk-8-alpine As build-env
WORKDIR /app
ADD . /app
RUN mvn clean package

FROM openjdk:8-jre-alpine
RUN apk update \
 && apk --no-cache add git tzdata \
 && ls /usr/share/zoneinfo \
 && cp /usr/share/zoneinfo/Asia/Taipei /etc/localtime  \
 && rm -rf /var/cache/apk/*

VOLUME /tmp

COPY --from=build-env /app/target/app.jar /app.jar

ENTRYPOINT ["/bin/sh", "-c", "java -Xmx128m -Xms128m -Djava.security.egd=file:/dev/./urandom -jar /app.jar"]
