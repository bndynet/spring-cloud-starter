/**
 * Copyright (c) 2018 BNDY-NET. All Rights Reserved.
 * http://bndy.net
 */
package net.bndy.sc.service.sso;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import net.bndy.sc.lib.ApplicationBase;

/**
 * @author Bendy Zhang
 * @version 1.0
 */
@EnableRedisHttpSession
public class Application extends ApplicationBase {

    public static final String RESOURCE_ID = "sso-resource";

    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
