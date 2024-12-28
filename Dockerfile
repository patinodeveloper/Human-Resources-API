FROM openjdk:21-jdk-slim

COPY target/RecursosHumanosApp-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]
