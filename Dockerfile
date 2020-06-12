FROM openjdk:8-jdk-alpine
MAINTAINER exercise
VOLUME /tmp
EXPOSE 9012
ADD target/exercise-0.0.1-SNAPSHOT.jar exercise.jar
ENTRYPOINT  ["java", "-jar","/exercise.jar"]