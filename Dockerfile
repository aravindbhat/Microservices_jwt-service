FROM eclipse-temurin:21-jdk-jammy
ADD target/jwt-service-0.0.1-SNAPSHOT.jar jwt-service.jar

ENTRYPOINT ["java","-jar","jwt-service.jar"]