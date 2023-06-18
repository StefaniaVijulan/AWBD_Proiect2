build:
	mvn clean package spring-boot:repackage

image:
	docker build -t veronica01/awbd:config-server-0.0.3-SNAPSHOT .

push:
	docker push  veronica01/awbd:config-server-0.0.3-SNAPSHOT

# start:
# docker run -p 8070:8070 veronica01/awbd:config-server-0.0.3-SNAPSHOT