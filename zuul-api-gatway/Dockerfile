FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/zuul.jar zuul.jar
ENTRYPOINT ["java", "-jar", "zuul.jar"]