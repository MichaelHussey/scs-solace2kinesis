# scs-solace2kinesis

Sample showing bridging between Solace and AWS Kinesis.

Listens to a Solace topic and stores data in AWS Kinesis.

To start:

```
	mvn spring-boot:run -Drun.arguments="--server.port=8081"
```
NB - all SpringBoot Web applications start a REST interface on 8080 so when running multiple components on a single machine 
it's most likely necessary to explicitly change the port as shown to avoid collisions.

This application by default will use the `application.yml` file at `src/main/resources` which connects to the `default` VPN on
a local VMR running on the default port (55555) using the username `default` and password `default`. If you need different 
Solace connectivity settings make a copy of the file, edit it and pass the new location in as follows

```
	cp src/main/resources/application.yml ./application_props.yml
	vi application_props.yml
	mvn spring-boot:run -Dspring.config.location=application_props.yml -Drun.arguments="--server.port=8081"
```
