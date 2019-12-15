FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/users.jar users.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","users.jar"]