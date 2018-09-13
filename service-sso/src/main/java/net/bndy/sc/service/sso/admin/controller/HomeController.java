/**
 * Copyright (c) 2018 BNDY-NET. All Rights Reserved.
 * http://bndy.net
 */
package net.bndy.sc.service.sso.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import net.bndy.sc.service.sso.service.AppUserDetailsService;
import net.bndy.sc.service.sso.service.OauthClientDetailsService;

/**
 * @author Bendy Zhang
 * @version 1.0
 */
@Controller
// Below is required, otherwise this type can not be passed to /oauth/confirm_access
// Because the default Endpoint is /oauth/authorize and then forward to /oauth/confirm_access
@SessionAttributes(types = {AuthorizationRequest.class})
public class HomeController {
    
    @Autowired
    private AppUserDetailsService appUserDetailsService;
    @Autowired
    private OauthClientDetailsService oauthClientDetailsService;
    
    @RequestMapping(value = "/admin")
    public String index(Model model) {
        model.addAttribute("userCount", this.appUserDetailsService.countUser());
        model.addAttribute("clientCount", this.oauthClientDetailsService.countClient());
        return "admin/index";
    }
    
    @RequestMapping("/oauth/confirm_access")
    public String oauthConfirmAccess(
        Model viewModel,
        @ModelAttribute AuthorizationRequest authRequest
    ) {
        ClientDetails clientDetails = this.oauthClientDetailsService.loadClientByClientId(authRequest.getClientId());
        viewModel.addAttribute("authRequest", authRequest);
        viewModel.addAttribute("client", clientDetails);
        viewModel.addAttribute("scopes", String.join(",", authRequest.getScope()));
        return "oauth/confirm_access";
    }
    
    @RequestMapping("/oauth/error")
    public String oauthError() {
        return "oauth_error";
    }
}
