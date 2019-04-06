/**
 * Copyright (c) 2018 BNDY-NET. All Rights Reserved.
 * http://bndy.net
 */
package net.bndy.sc.service.sso.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import net.bndy.sc.service.sso.entity.AppUser;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Bendy Zhang
 * @version 1.0
 */
@RestController
public class OAuthController {

    @GetMapping("/oauth/me")
    public AppUser user(Authentication principal) {
        return (AppUser) principal.getPrincipal();
    }

    @Autowired
    ConsumerTokenServices tokenServices;

    @DeleteMapping("/oauth/logout")
    public void revokeToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.contains("Bearer")) {
            String tokenId = authorization.substring("Bearer".length() + 1);
            tokenServices.revokeToken(tokenId);
        }
    }
}