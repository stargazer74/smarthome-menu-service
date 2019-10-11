FROM openjdk:11-jdk

LABEL autor="Chris Wohlbrecht"

ENV TZ=Europe/Berlin

ARG JAR_FILE=target/menu-service.jar
ADD ${JAR_FILE} menu-service.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/menu-service.jar"]
