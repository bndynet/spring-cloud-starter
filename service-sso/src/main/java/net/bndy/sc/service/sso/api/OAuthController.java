/**
 * Copyright (c) 2018 BNDY-NET. All Rights Reserved.
 * http://bndy.net
 */
package net.bndy.sc.service.sso.api;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import net.bndy.sc.service.sso.entity.AppUser;
 
/**
 * @author Bendy Zhang
 * @version 1.0
 */
@RestController
public class OAuthController {

    @GetMapping("/oauth/me")
    public AppUser user(Authentication principal) {
        return (AppUser)principal.getPrincipal();
    }
}