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

import net.bndy.sc.service.sso.entity.OauthClientDetails;
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

    @RequestMapping(value = { "/edit", "/new" })
    public String edit(Model viewModel, @RequestParam(name = "id", required = false) String id) {
        OauthClientDetails model;
        if (id != null) {
            model = this.oauthClientDetailsService.findByClientId(id);
        } else {
            model = new OauthClientDetails();
        }
        viewModel.addAttribute("model", model);
        return "client/edit";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute OauthClientDetails formModel, Model viewModel) {
        this.oauthClientDetailsService.save(formModel);
        return "redirect:/client/list";
    }

    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    public String remove(@RequestParam(name = "id") String id) {
        this.oauthClientDetailsService.remove(id);
        return "redirect:/client/list";
    }
}
