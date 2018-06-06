package net.bndy.sc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer
@RestController
public class ScConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScConfigApplication.class, args);
	}

    @Value("${spring.application.name}")
    String name;

    @RequestMapping("/hi")
    public String hi() {
    		return "Hi " + name;
    }
}
