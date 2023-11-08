FROM openjdk:11.0.16-jre-slim

ARG JAR_FILE=target/order-service-1.0-SNAPSHOT.jar

WORKDIR /ru-liga-food-service

COPY ${JAR_FILE} /ru-liga-food-service/order-service.jar

ENTRYPOINT ["java", "-jar", "order-service.jar"]
