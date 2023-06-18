build:
	mvn clean package spring-boot:repackage

image:
	docker build -t veronica01/awbd:loadbalance-0.0.3-SNAPSHOT .

push:
	docker push  veronica01/awbd:loadbalance-0.0.3-SNAPSHOT

# start:
# docker run -p 9090:9090  veronica01/awbd:loadbalance-0.0.3-SNAPSHOT --network awbd