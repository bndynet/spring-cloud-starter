package net.bndy.sc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class ServiceSsoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceSsoApplication.class, args);
	}
	
    @Value("${server.port}")
    String port;

    @RequestMapping("/hi")
    public String hi(@RequestParam(name = "name", required = false) String name) {
        if (name == null || name.isEmpty()) {
            return "Hi SSO, I am from port:" + port;
        } else {
            return "Hi SSO, " + name + ", i am from port:" + port;
        }
    }
}
