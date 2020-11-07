package net.bndy.res;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;


/**
 * @author Bendy Zhang
 * @version 1.0
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(final ApplicationContext ctx) {
        return new CommandLineRunner() {
            public void run(String... args) throws Exception {
                String[] beanNames = ctx.getBeanDefinitionNames();
                Arrays.sort(beanNames);
                System.out.println("Beans provided by Spring Boot:");
                for (String beanName : beanNames) {
                    System.out.println(beanName);
                }
            }
        };
    }
}
