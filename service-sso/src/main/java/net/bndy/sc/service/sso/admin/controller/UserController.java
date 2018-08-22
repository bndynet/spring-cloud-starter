/**
 * Copyright (c) 2018 BNDY-NET. All Rights Reserved.
 * http://bndy.net
 */
package net.bndy.sc.service.sso.admin.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.bndy.lib.CollectionHelper;
import net.bndy.sc.service.sso.entity.AppUser;
import net.bndy.sc.service.sso.service.AppUserDetailsService;

/**
 * @author Bendy Zhang
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/admin/user")
public class UserController {

    @Autowired
    private AppUserDetailsService appUserDetailsService;

    @RequestMapping(value = "/list")
    public String list(Model model, @RequestParam(name = "k", required = false) String keywords) {
        List<AppUser> users = keywords != null && !keywords.trim().isEmpty() 
                ? this.appUserDetailsService.search(keywords) : this.appUserDetailsService.getAllUsers();
        List<Long> adminIds = users.stream().filter(u -> {
            return CollectionHelper.contains(u.getRoles(), r -> AppUserDetailsService.ROLE_ADMIN.equals(r.getName()));
        }).map(u -> u.getId()).collect(Collectors.toList());
        model.addAttribute("keywords", keywords);
        model.addAttribute("users", users);
        model.addAttribute("adminIds", adminIds);
        return "admin/user/list";
    }

    @RequestMapping(value = { "/edit", "/new" })
    public String edit(Model viewModel, @RequestParam(name = "id", required = false) Long id) {
        AppUser model;
        if (id != null) {
            model = this.appUserDetailsService.findById(id.longValue());
        } else {
            model = new AppUser();
            model.setEnabled(true);
        }
        viewModel.addAttribute("model", model);
        return "admin/user/edit";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute AppUser formModel, Model viewModel) {
        this.appUserDetailsService.saveUser(formModel);
        return "redirect:/admin/user/list";
    }

    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    public String remove(@RequestParam(name = "id") long id) {
        this.appUserDetailsService.removeAppUser(id);
        return "redirect:/admin/user/list";
    }
}