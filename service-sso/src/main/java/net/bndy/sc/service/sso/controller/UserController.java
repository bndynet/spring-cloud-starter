/**
 * Copyright (c) 2018 BNDY-NET. All Rights Reserved.
 * http://bndy.net
 */
package net.bndy.sc.service.sso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.bndy.sc.service.sso.entity.AppUser;
import net.bndy.sc.service.sso.service.AppUserDetailsService;
 
/**
 * @author Bendy Zhang
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	private AppUserDetailsService appUserDetailsService;
	
	@RequestMapping(value = "/list")
	public String list(Model model) {
		model.addAttribute("users", this.appUserDetailsService.getAllUsers());
		return "user/list";
	}
	
	@RequestMapping(value = "/edit")
	public String edit(Model model, @RequestParam(name = "id", required = false) Long id) {
		AppUser user;
		if (id != null) {
			user = this.appUserDetailsService.findById(id.longValue());
		} else {
			user = new AppUser();
			user.setEnabled(true);
		}
		model.addAttribute("user", user);
		return "user/edit";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute AppUser formModel, Model model) {
		this.appUserDetailsService.saveUser(formModel);
		return "redirect:/user/list";
	}
}