/**
 * Copyright (c) 2018 BNDY-NET. All Rights Reserved.
 * http://bndy.net
 */
package net.bndy.sc.service.sso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.bndy.sc.service.sso.service.OauthClientDetailsService;

/**
 * @author Bendy Zhang
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/client")
public class ClientController {
	
	@Autowired
	private OauthClientDetailsService oauthClientDetailsService;
	
	@RequestMapping(value = "/list")
	public String list(Model model) {
		model.addAttribute("clients", this.oauthClientDetailsService.getAllClients());
		return "client/list";
	}
}
