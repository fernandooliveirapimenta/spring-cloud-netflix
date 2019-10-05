docker-compose
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

#Curso russo projetos
eureka-server
users-service
account-service
zuul-api-gatway

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
