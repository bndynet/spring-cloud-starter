/**
 * Copyright (c) 2018 BNDY-NET. All Rights Reserved.
 * http://bndy.net
 */
package net.bndy.sc;

import org.springframework.boot.SpringApplication;

import net.bndy.sc.lib.ApplicationBase;

/**
 * @author Bendy Zhang
 * @version 1.0
 */
public class Application extends ApplicationBase {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}

