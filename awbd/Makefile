build:
	mvn clean package spring-boot:repackage

image:
	docker build -t veronica01/awbd:awbdapp-0.0.3-SNAPSHOT .

push:
	docker push  veronica01/awbd:awbdapp-0.0.3-SNAPSHOT

# start:
# docker run -p 9411:9411 veronica01/awbd:awbdapp-0.0.3-SNAPSHOT --network awbd