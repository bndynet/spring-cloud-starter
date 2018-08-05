/**
 * Copyright (c) 2018 BNDY-NET. All Rights Reserved.
 * http://bndy.net
 */
package net.bndy.sc.service.sso;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;

import net.bndy.sc.lib.ApplicationBase;

/**
 * @author Bendy Zhang
 * @version 1.0
 */
@MapperScan("net.bndy.sc.service.sso.dao")
public class Application extends ApplicationBase {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}

