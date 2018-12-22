/**
 * Copyright (c) 2018 BNDY-NET. All Rights Reserved.
 * http://bndy.net
 */
package net.bndy.sc.ms.oauth.controllers;

import javax.servlet.http.HttpServletRequest;

import net.bndy.sc.ms.oauth.*;
import net.bndy.sc.ms.oauth.properties.AppConfiguration;
import net.bndy.sc.ms.oauth.properties.ClientConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import net.bndy.lib.CollectionHelper;
import net.bndy.lib.StringHelper;

/**
 * @author Bendy Zhang
 * @version 1.0
 */
@Controller
public class OAuthController {

    private final String KEY_ERROR = "error";
    private final String KEY_REDIRECT_URI = "redirect_uri";
    private final String KEY_CLIENT_ID = "client_id";
    private final String KEY_STATE = "state";
    private final String KEY_ACCESS_TOKEN = "access_token";
    private final String KEY_CONFIG = "oauth_config";
    private final String KEY_RESPONSE_TYPE = "response_type";

    @Autowired
    private AppConfiguration appConfiguration;

    @Autowired
    private HttpServletRequest request;

    private String getCallbackUrl() {
        if (this.appConfiguration.getCallbackUri() == null || this.appConfiguration.getCallbackUri().isEmpty()) {
            return request.getRequestURL().substring(0, request.getRequestURL().lastIndexOf("/")) + "/callback";
        } else {
            return this.appConfiguration.getCallbackUri();
        }
    }

    @RequestMapping("/authorize")
    public RedirectView authorize(
        HttpServletRequest request, RedirectAttributes attributes,
        @RequestParam(name = "target", required = false) String target,
        @RequestParam(name = "redirect_uri") String redirectUri) {

        // validate whether the redirect URI is allowed
        if (!CollectionHelper.contains(
            CollectionHelper.array2List(this.appConfiguration.getAllowedOrigins()),
                item -> redirectUri.toLowerCase().contains(item.toLowerCase()))) {

            attributes.addAttribute(KEY_ERROR, "The redirect uri is not allowed");
            return new RedirectView(redirectUri);
        }

        ClientConfiguration client = this.appConfiguration.getClients().get(target);

        if (client == null) {
            attributes.addAttribute(KEY_ERROR, "No client found. Are you missing 'target' in your url?");
            return new RedirectView(redirectUri);
        }

        String state = StringHelper.generateRandomCode(10);

        request.getSession().setAttribute(KEY_REDIRECT_URI, redirectUri);
        request.getSession().setAttribute(KEY_STATE, state);
        request.getSession().setAttribute(KEY_CONFIG, client);

        attributes.addAttribute(KEY_CLIENT_ID, client.getClientId());
        attributes.addAttribute(KEY_REDIRECT_URI, this.getCallbackUrl());
        attributes.addAttribute(KEY_STATE, state);
        attributes.addAttribute(KEY_RESPONSE_TYPE, "code");
        return new RedirectView(client.getUserAuthorizationUri());
    }

    @RequestMapping("/callback")
    public RedirectView callback(
        HttpServletRequest request, RedirectAttributes attributes,
        @RequestParam(name = "code") String code,
        @RequestParam(name = "state") String state) throws Exception {

        String validState = request.getSession().getAttribute(KEY_STATE).toString();
        if (!state.equals(validState)) {
            throw new Exception("Invalid State: " + state);
        }
        ClientConfiguration client = (ClientConfiguration) request.getSession().getAttribute(KEY_CONFIG);

        try {
            OAuthRestTemplate oAuthRestTemplate = new OAuthRestTemplate(client.getClientId(),
                client.getClientSecret());
            TokenEntity tokenEntity = oAuthRestTemplate.getToken(client.getAccessTokenUri(),
                code, this.getCallbackUrl());
            attributes.addAttribute(KEY_ACCESS_TOKEN, tokenEntity.getAccess_token());
        } catch (HttpClientErrorException err) {
            err.printStackTrace();
            attributes.addAttribute(KEY_ERROR, err.getRawStatusCode() + " " + err.getStatusCode().name());
        }
//		String redirectUri = "https://api.github.com/user";		// Just for testing
        String redirectUri = request.getSession().getAttribute(KEY_REDIRECT_URI).toString();
        return new RedirectView(redirectUri);
    }
}
