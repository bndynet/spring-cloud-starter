package net.bndy.sc.lib;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@EnableEurekaClient
@RestController
public abstract class ApplicationBase {

    @Value("${spring.application.name}")
    String name;

    @RequestMapping("/hi")
    public String hi() {
    		return "Hi " + name;
    }
}
