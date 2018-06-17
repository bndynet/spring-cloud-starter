/**
 * Copyright (c) 2018 BNDY-NET. All Rights Reserved.
 * http://bndy.net
 */
package net.bndy.sc.ms.oauth.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import net.bndy.sc.ms.oauth.github.GitHubConfig;
import net.bndy.lib.CollectionHelper;
import net.bndy.lib.StringHelper;
import net.bndy.sc.ms.oauth.OAuthConfig;
import net.bndy.sc.ms.oauth.OAuthItemConfigBase;
import net.bndy.sc.ms.oauth.OAuthParams;

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
	
    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private GitHubConfig githubConfig;
    
    @Autowired
    private OAuthConfig oauthConfig;
    
    @Autowired
    private HttpServletRequest request;
    
    private String getCallbackUrl() {
    		if (this.oauthConfig.getCallback_url() == null || this.oauthConfig.getCallback_url().isEmpty()) {
			return request.getRequestURL().substring(0, request.getRequestURL().lastIndexOf("/")) + "/callback";
    		} else {
    			return this.oauthConfig.getCallback_url();
    		}
    }

	@RequestMapping("/authorize")
	public RedirectView authorize(
			HttpServletRequest request, RedirectAttributes attributes, 
			@RequestParam(name = "target", required = false) String target,
			@RequestParam(name = "redirect_uri" ) String redirectUri) {
		// validate whether the redirect URI is allowed
		if (!CollectionHelper.contains(
				CollectionHelper.array2List(this.oauthConfig.getAllowed_origins()), item -> item.toLowerCase().equals(redirectUri.toLowerCase()))) {
			
			attributes.addAttribute(KEY_ERROR, "The redirect uri is not allowed");
			return new RedirectView(redirectUri);
		}

		String state = StringHelper.generateRandomCode(10);
		request.getSession().setAttribute(KEY_REDIRECT_URI, redirectUri);
		request.getSession().setAttribute(KEY_STATE, state);
		
		OAuthItemConfigBase itemConfig = null;
		if ("github".equals(target)) {
			itemConfig = this.githubConfig;
		}
		request.getSession().setAttribute(KEY_CONFIG, itemConfig);

		attributes.addAttribute(KEY_CLIENT_ID, itemConfig.getClient_id());
		attributes.addAttribute(KEY_REDIRECT_URI, this.getCallbackUrl());
		attributes.addAttribute(KEY_STATE, state);
		return new RedirectView(itemConfig.getUrl().getAuthorize());
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
		OAuthItemConfigBase itemConfig = (OAuthItemConfigBase)request.getSession().getAttribute(KEY_CONFIG);
		ResponseEntity<OAuthParams> responseEntity = this.restTemplate.postForEntity(
				itemConfig.getUrl().getAccess_token(), new OAuthParams(
					itemConfig.getClient_id(),
					itemConfig.getClient_secret(),
					this.getCallbackUrl(),
					code, state), OAuthParams.class);
		attributes.addAttribute(KEY_ACCESS_TOKEN, responseEntity.getBody().getAccess_token());
//		String redirectUri = "https://api.github.com/user";		// Just for testing
		String redirectUri = request.getSession().getAttribute(KEY_REDIRECT_URI).toString();
		return new RedirectView(redirectUri);
	}
}
