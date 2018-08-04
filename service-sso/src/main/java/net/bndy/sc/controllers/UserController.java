package net.bndy.sc.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
 
@RestController
public class UserController {
    @GetMapping("/user/me")
    public Object user(Authentication principal) {
        return principal.getPrincipal();
    }
}