FROM openjdk:8-jdk-alpine

COPY . .

ADD ./build/libs/backend-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]