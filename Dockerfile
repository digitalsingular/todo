FROM openjdk:8-jdk-alpine

ENV APP_JAR app.jar
VOLUME /java
EXPOSE 8080 8000
ENTRYPOINT java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8000 -jar java/${APP_JAR}