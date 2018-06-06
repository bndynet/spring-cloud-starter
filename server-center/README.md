## Docker 

**Build**

> `mvn clean package docker:build`

**Run docker container with**

> `docker run --name sc-server1 -p 8080:9000 sc-server`

Or below is without log in screen.

> `docker run -d --name sc-server1 -p 8080:9000 sc-server:latest`

