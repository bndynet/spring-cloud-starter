# Spring Cloud Starter Project

## Components

- **server center** Port: 9000
- **server config** Port: 9010
- **server gateway** Port: 9020
- **service oauth** Port: 9100
- **service sso** Port: 9110

## Build (Docker and Docker Compose)

Docker Build

> mvn clean package docker:build

And run all instances.

> docker-componse up

Endpoints

```
- eureka server:        http://localhost:9000 
- spring boot admin:    http://localhost:9000/sba    
- service gateway:      http://localhost:9020/<service-name>/
```
