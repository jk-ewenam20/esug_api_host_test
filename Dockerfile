FROM openjdk:17-jdk
LABEL authors="jonathanewenam, joshnuku, samuelantwi, fareedmt"
ARG JAR_FILE=target/*.jar
COPY ./target/sesElearningPlatform-0.0.1-SNAPSHOT.jar esug.jar
EXPOSE 20201
ENTRYPOINT ["java","-jar","/esug.jar"]