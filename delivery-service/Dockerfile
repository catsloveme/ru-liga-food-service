FROM openjdk:11.0.16-jre-slim

ARG JAR_FILE=target/delivery-service-1.0-SNAPSHOT.jar

WORKDIR /ru-liga-food-service

COPY ${JAR_FILE} /ru-liga-food-service/delivery-service.jar

ENTRYPOINT ["java", "-jar", "delivery-service.jar"]
