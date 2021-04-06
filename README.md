# globalmoney-payment-service

This Service contains REST API to manage payments.

### Service Configuration

Service uses 'Spring Profiles' and have different properties file per environment

- [application.yaml]
- [application-dev.yaml]

use below arguments to activate profile on startup

-Dspring.profiles.active=dev

### Run as dockerized application
Run on the Terminal
1. ./gradlew bootBuildImage   --- Build the image and store it in docker local registry

2. docker run -p 9080:9080 -e "SPRING_PROFILES_ACTIVE=dev" --name payment-service payment-service:0.0.1-SNAPSHOT 

#### Docker commands:
1. docker ps -a  --- List the docker containers status

2. docker images --- List the docker images present in local registry

3. docker stop <\<container name>> --- Stop the docker running container

4. docker rm <\<container name>> --- Remove the docker running container

#### Prerequisite:
Docker needs to be installed.

### API Docs
 Local : http://localhost:9080/globalmoney-payments/swagger-ui.html#/
 
### Service Health and Info

Health : http://localhost:9080/globalmoney-payments/actuator/health

Info : http://localhost:9080/globalmoney-payments/actuator/info

