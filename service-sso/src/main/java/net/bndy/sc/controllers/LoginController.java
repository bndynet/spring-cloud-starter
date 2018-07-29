/**
 * Copyright (c) 2018 BNDY-NET. All Rights Reserved.
 * http://bndy.net
 */
package net.bndy.sc.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final static String KEY_REDIRECT_URI = "redirect_uri";

    @Autowired
    private MessageSource messageSource;

//    @GetMapping("/login")
//    public String login(Model model, HttpSession session,
//        @RequestParam(name = KEY_REDIRECT_URI, required = true) String redirect) {
//
//        model.addAttribute("name", "Bendy");
//        session.setAttribute(KEY_REDIRECT_URI, redirect);
//
//        return "login";
//    }
//
//    @PostMapping("/login")
//    public String login(Model model, HttpSession session,
//        @RequestParam(name = "username", required = false) String username,
//        @RequestParam(name = "password", required = false) String password,
//        @RequestParam(name = "remember", required = false) Boolean remember) {
//
//        if (username.isEmpty() || username.trim().isEmpty() || password.isEmpty() || password.trim().isEmpty()) {
//            model.addAttribute("error", messageSource.getMessage("msgInvalidUsernameOrPassword", null, null, LocaleContextHolder.getLocale()));
//        } else {
//            // TODO: validate the user
//            return "redirect:" + session.getAttribute(KEY_REDIRECT_URI);
//        }
//
//        return "login";
//    }
}