FROM openjdk:8-jdk-alpine

VOLUME /java
EXPOSE 8080 8000
ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8000","-jar","java/app.jar"]