FROM openjdk:8-jdk-alpine

COPY target/todo-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080 8000
ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8000","-jar","/app.jar"]