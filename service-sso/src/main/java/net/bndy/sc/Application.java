package net.bndy.sc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
    @Value("${spring.application.name}")
    String name;

    @Value("${name}")
    String username;

    @RequestMapping("/hi")
    public String hi() {
    		return "Hi " + name + " --> i'm " + username;
    }
}
