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
