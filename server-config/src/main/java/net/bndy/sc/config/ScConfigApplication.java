/**
 * Copyright (c) 2018 BNDY-NET. All Rights Reserved.
 * http://bndy.net
 */
package net.bndy.sc.config;

import org.springframework.boot.SpringApplication;

import net.bndy.sc.lib.ApplicationBase;

import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author Bendy Zhang
 * @version 1.0
 */
@EnableConfigServer
public class ScConfigApplication extends ApplicationBase {

	public static void main(String[] args) {
		SpringApplication.run(ScConfigApplication.class, args);
	}
}
