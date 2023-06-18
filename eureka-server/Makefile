build:
	mvn clean package spring-boot:repackage

image:
	docker build -t veronica01/awbd:eureka-server-0.0.3-SNAPSHOT .

push:
	docker push  veronica01/awbd:eureka-server-0.0.3-SNAPSHOT

# start:
docker run -p 8971:8971 -e EUREKA_INSTANCE_HOST=host.docker.internal

docker run -p 8761:8761 -e EUREKA_INSTANCE_HOST=host.docker.internal veronica01/awbd:eureka-server-0.0.3-SNAPSHOT --network awbd