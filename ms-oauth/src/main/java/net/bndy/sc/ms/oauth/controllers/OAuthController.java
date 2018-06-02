/**
 * Copyright (c) 2018 BNDY-NET. All Rights Reserved.
 * http://bndy.net
 */
package net.bndy.sc.ms.oauth.controllers;

import java.util.prefs.BackingStoreException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.filter.FilterAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.classmate.util.ResolvedTypeCache.Key;
import com.netflix.infix.lang.infix.antlr.EventFilterParser.path_function_return;

import net.bndy.sc.ms.oauth.github.GitHubConfig;
import net.bndy.lib.StringHelper;
import net.bndy.sc.ms.oauth.OAuthParams;

/**
 * @author Bendy Zhang
 * @version 1.0
 */
@Controller
public class OAuthController {
	
	private final String KEY_REDIRECT_URI = "redirect_uri";
	private final String KEY_CLIENT_ID = "client_id";
	private final String KEY_STATE = "state";
	private final String KEY_ACCESS_TOKEN = "access_token";
	

    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private GitHubConfig githubConfig;
    
    @Autowired
    private HttpServletRequest request;
    
    private String getCallbackUrl() {
    		return request.getRequestURL().substring(0, request.getRequestURL().lastIndexOf("/")) + "/callback";
    }

	@RequestMapping("/authorize")
	public RedirectView authorize(
			HttpServletRequest request, RedirectAttributes attributes, 
			@RequestParam(name = "redirect_uri" ) String redirectUri) {
		String state = StringHelper.generateRandomCode(10);
		request.getSession().setAttribute(KEY_REDIRECT_URI, redirectUri);
		request.getSession().setAttribute(KEY_STATE, state);
		attributes.addAttribute(KEY_CLIENT_ID, this.githubConfig.getClient_id());
		attributes.addAttribute(KEY_REDIRECT_URI, this.getCallbackUrl());
		attributes.addAttribute(KEY_STATE, state);
		return new RedirectView(this.githubConfig.getUrl().getAuthorize());
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
		
		ResponseEntity<OAuthParams> responseEntity = this.restTemplate.postForEntity(
				this.githubConfig.getUrl().getAccess_token(), new OAuthParams(
					this.githubConfig.getClient_id(),
					this.githubConfig.getClient_secret(),
					this.getCallbackUrl(),
					code, state), OAuthParams.class);
		attributes.addAttribute(KEY_ACCESS_TOKEN, responseEntity.getBody().getAccess_token());
		String redirectUri = "https://api.github.com/user";
//		String redirectUri = request.getSession().getAttribute(KEY_REDIRECT_URI).toString(); // "https://api.github.com/user";
		return new RedirectView(redirectUri);
	}
}
