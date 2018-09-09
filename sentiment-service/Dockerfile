# production stage
FROM openjdk:8-jdk-alpine as build-stage
VOLUME /tmp
ARG JAR_FILE=target/sentiment-service-0.1.jar
COPY ${JAR_FILE} sentiment-service.jar
EXPOSE 9200
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/sentiment-service.jar"]




