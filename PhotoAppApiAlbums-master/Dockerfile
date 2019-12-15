FROM openjdk:8-jdk-alpine 
VOLUME /tmp 
COPY target/albums.jar albums.jar
ENTRYPOINT ["java","-jar","albums.jar"]