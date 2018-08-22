/**
 * Copyright (c) 2018 BNDY-NET. All Rights Reserved.
 * http://bndy.net
 */
package net.bndy.sc.service.sso.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.bndy.sc.service.sso.service.AppUserDetailsService;
import net.bndy.sc.service.sso.service.OauthClientDetailsService;

/**
 * @author Bendy Zhang
 * @version 1.0
 */
@Controller
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
    
}
