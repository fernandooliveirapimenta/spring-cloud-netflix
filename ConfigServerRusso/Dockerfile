FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY apiEncryptionKey.jks apiEncryptionKey.jks
COPY UnlimitedJCEPolicyJDK8/* /usr/lib/jvm/java-1.8-openjdk/jre/lib/security/
COPY target/config.jar config.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar", "config.jar"]