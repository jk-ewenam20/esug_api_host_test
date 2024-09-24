FROM openjdk:17-jdk
LABEL authors="jonathanewenam, joshnuku, samuelantwi, fareedmt"
ARG JAR_FILE=target/*.jar
COPY ./target/sesElearningPlatform-0.0.1-SNAPSHOT.jar esug_api.jar

ENTRYPOINT ["java","-jar","/esug_api.jar"]