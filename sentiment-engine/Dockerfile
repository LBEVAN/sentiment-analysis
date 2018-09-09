# production stage
FROM openjdk:8-jdk-alpine as build-stage
VOLUME /tmp
ARG JAR_FILE=target/sentiment-engine-0.1.jar
COPY ${JAR_FILE} sentiment-engine.jar
EXPOSE 9300
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/sentiment-engine.jar"]