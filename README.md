docker-compose
id
Eureka-server: service discovery
Zool: api gateway
Ribbon: front loadbalancer

Dependencias:
Java 8
Maven 3.6 +
Docker
Docker Compose


#Docker compose commands
docker-compose config: valida o yaml
docker-compose up: no diretorio do arquivo .yaml vai startar
docker-compose -f docker-compose.yaml -f docker-compose-dev.yaml config
docker-compose -f docker-compose.yaml -f docker-compose-dev.yaml up -d
docker-compose -f docker-compose.yaml -f docker-compose-dev.yaml logs : atach logs containers
docker-compose -f docker-compose.yaml -f docker-compose-dev.yaml ps
docker-compose -f docker-compose.yaml -f docker-compose-dev.yaml stop
docker-compose -f docker-compose.yaml -f docker-compose-dev.yaml down

#Eureka server
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
		</dependency>
Anotar clesse main com @EnableEurekaServer
//usar essas configs
server.port=8016
spring.application.name=eurekadiscovery
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false
eureka.client.service-url.defaultZone: http://localhost:8010/eureka

#Eureka client
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-netflix-eureka-client</artifactId>
		</dependency>
Anotar a classe main @EnableEurekaClient
Configs
server.port=0
#automatic assigne port
spring.application.name=users-ws
eureka.client.service-url.defaultZone=http://localhost:8010/eureka

#Zuul
Deve se registrar no eureka
chamada url base localhost:8011/users-ws/users/status/check

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
public class ZuulApiGatwayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulApiGatwayApplication.class, args);
	}

}

#Curso russo projetos
account-service
eureka-discovery
users-service
zuul-api-gatway

#Curso paktpublishing
blog
bookmarks
configserver
reverseproxy
serviceregistry


#startando spring boot mvn
mvn spring-boot:run -Dspring-boot.run.arguments=--spring.application.instance_id=fernando
mvn spring-boot:run -Dspring-boot.run.arguments=--spring.application.instance_id=fernando,--server.port=833333

#add h2 in memory
		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<version>1.4.193</version>
			<scope>runtime</scope>
		</dependency>
spring.h2.console.enabled=true
spring.h2.console.settings.web-allow-others=true
localhost:8080/h2-console
jdbc:h2:mem:testdb
user: sa
pass: 

#rabbitmq docker
docker container run -d -p 15672:15672 -p 5672:5672 -p 25676:25676 rabbitmq:3-management

#mysql docker

docker-compose -f docker/mysql-docker-compose.yaml up -d
docker-compose -f docker/mysql-docker-compose.yaml down

#JCE java cryptograph extension 
Unlimitedjce/*

keytool -genkeypair -alias apiEncryptionKey -keyalg RSA -dname "CN=Fernando,OU=Api,O=fernando.com,L=Brasil,S=ON,C=CA" -keypass fefe@123456 -keystore apiEncryptionKey.jks -storepass fefe@123456

keytool -importkeystore -srckeystore apiEncryptionKey.jks -destkeystore apiEncryptionKey.jks -deststoretype pkcs12
