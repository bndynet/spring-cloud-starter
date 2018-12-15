/**
 * Copyright (c) 2018 BNDY-NET. All Rights Reserved.
 * http://bndy.net
 */
package net.bndy.sc.service.sso.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.bndy.sc.service.sso.Application;
import net.bndy.sc.service.sso.entity.OauthClientDetails;
import net.bndy.sc.service.sso.service.OauthClientDetailsService;

/**
 * @author Bendy Zhang
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/admin/client")
public class ClientController {

    @Autowired
    private OauthClientDetailsService oauthClientDetailsService;

    @PreAuthorize(value = "hasAuthority('" + OauthClientDetailsService.PERMISSION_READ + "')")
    @RequestMapping(value = "/list")
    public String list(Model model) {
        model.addAttribute("clients", this.oauthClientDetailsService.getAllClients());
        return "admin/client/list";
    }

    @PreAuthorize(value = "hasAuthority('" + OauthClientDetailsService.PERMISSION_WRITE + "')")
    @RequestMapping(value = { "/edit", "/new" })
    public String edit(Model viewModel, @RequestParam(name = "id", required = false) String id) {
        OauthClientDetails model;
        if (id != null) {
            model = this.oauthClientDetailsService.findByClientId(id);
        } else {
            model = new OauthClientDetails();
        }
        viewModel.addAttribute("model", model);
        return "admin/client/edit";
    }

    @PreAuthorize(value = "hasAuthority('" + OauthClientDetailsService.PERMISSION_WRITE + "')")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute OauthClientDetails formModel, Model viewModel) {
        // attach resource id so that this client can be get user info
        formModel.setResourceIds(Application.RESOURCE_ID);
        this.oauthClientDetailsService.save(formModel);
        return "redirect:/admin/client/list";
    }

    @PreAuthorize(value = "hasAuthority('" + OauthClientDetailsService.PERMISSION_WRITE + "')")
    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    public String remove(@RequestParam(name = "id") String id) {
        this.oauthClientDetailsService.remove(id);
        return "redirect:/admin/client/list";
    }
}
