# Spring Cloud Starter Project

## Components

| SERVICE NAME | PORT | DESCRIPTION |
| --- | :---: | --- |
| **server center** | 9000 | Eureka Server and Spring Boot Admin |
| **server config** | 9010 | Configurations from GitHub Repo |
| **server gateway** | 9020 | Zuul for All Services |
| **service oauth** | 9100 | OAuth Service |
| **service sso** | 9110 | SSO Service |

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

Note:  Deploy to tomcat container, please use `mvn clean package -Pwar` to generate war packages.
