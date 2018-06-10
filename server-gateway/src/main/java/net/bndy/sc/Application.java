package net.bndy.sc;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

import net.bndy.sc.lib.ApplicationBase;

/**
 * @author Bendy Zhang
 * @version 1.0
 */
@EnableZuulProxy
public class Application extends ApplicationBase {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
