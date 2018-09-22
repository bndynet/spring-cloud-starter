/**
 * Copyright (c) 2018 BNDY-NET. All Rights Reserved.
 * http://bndy.net
 */
package net.bndy.sc.service.sso.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import net.bndy.sc.service.sso.ApplicationUtil;
import net.bndy.sc.service.sso.service.OauthClientDetailsService;

/**
 * @author Bendy Zhang
 * @version 1.0
 */
@Controller
// SessionAttribute is required, otherwise this type can not be passed to /oauth/confirm_access
// Because the default Endpoint is /oauth/authorize and then forward to /oauth/confirm_access
@SessionAttributes(types = {AuthorizationRequest.class})
// Implements ErrorController is for changing default error handling
public class HomeController implements ErrorController {
    
    @Autowired
    private OauthClientDetailsService oauthClientDetailsService;
    
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
    
    @RequestMapping(value = {"/error", "/oauth/error"})
    public String handleError(Model mView, HttpServletRequest request) {
        Object error = request.getAttribute("error");
        if (error instanceof OAuth2Exception) {
            OAuth2Exception exception = (OAuth2Exception)error;
            mView.addAttribute("error", ApplicationUtil.getOauthErrorLang(exception.getOAuth2ErrorCode()));
            mView.addAttribute("message", exception.getMessage());
        }
        return "error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
