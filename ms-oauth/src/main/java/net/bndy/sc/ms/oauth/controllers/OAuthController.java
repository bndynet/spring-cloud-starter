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
import net.bndy.sc.ms.oauth.OAuthParams;

@Controller
public class OAuthController {

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
	public RedirectView authorize(HttpServletRequest request, RedirectAttributes attributes, @RequestParam(name = "redirect_uri" ) String redirectUri) {
		request.getSession().setAttribute("redirectUri", redirectUri);
		attributes.addAttribute("client_id", this.githubConfig.getClient_id());
		attributes.addAttribute("redirect_uri", this.getCallbackUrl());
		attributes.addAttribute("state", "1");
		return new RedirectView(this.githubConfig.getUrl().getAuthorize());
	}
	
	@RequestMapping("/callback")
	public RedirectView callback(HttpServletRequest request, RedirectAttributes attributes, @RequestParam(name = "code") String code) {
		ResponseEntity<OAuthParams> responseEntity = this.restTemplate.postForEntity(
				this.githubConfig.getUrl().getAccess_token(), new OAuthParams(
					this.githubConfig.getClient_id(),
					this.githubConfig.getClient_secret(),
					this.getCallbackUrl(),
					code), OAuthParams.class);
		attributes.addAttribute("access_token", responseEntity.getBody().getAccess_token());
		String redirectUri = request.getSession().getAttribute("redirectUri").toString(); // "https://api.github.com/user";
		return new RedirectView(redirectUri);
	}
}
