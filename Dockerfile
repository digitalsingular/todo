FROM openjdk:8-jdk-alpine

ENV APP_JAR app.jar
VOLUME /java
EXPOSE 8080
ENTRYPOINT java -jar java/${APP_JAR}