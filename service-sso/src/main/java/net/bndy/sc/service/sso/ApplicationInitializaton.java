/**
 * copyright (c) 2018 bndy-net. all rights reserved.
 * http://bndy.net
 */
package net.bndy.sc.service.sso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import net.bndy.sc.service.sso.service.AppUserDetailsService;

/**
 * @author Bendy Zhang
 * @version 1.0
 */
@Component
public class ApplicationInitializaton implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private AppUserDetailsService appUserDetailsService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;
        
        appUserDetailsService.initAdmin();

        alreadySetup = true;
    }
}
