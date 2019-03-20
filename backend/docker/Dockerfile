FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY /target/allert.jar allert.jar
ENTRYPOINT ["java", "-jar", "/allert.jar"]