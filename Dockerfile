FROM openjdk:8-jdk-alpine

VOLUME /tmp

EXPOSE 8082

ARG JAR_FILE=target/map-service-1.0.jar
ADD ${JAR_FILE} map-service.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","map-service.jar"]