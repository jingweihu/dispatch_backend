FROM openjdk:8-jdk-alpine
EXPOSE 8080
ARG JAR_FILE=target/dispatch-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} .
CMD [ "java", "-jar",  "/dispatch-0.0.1-SNAPSHOT.jar"]