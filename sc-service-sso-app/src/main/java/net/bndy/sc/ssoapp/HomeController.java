package net.bndy.sc.ssoapp;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
    public String home(@AuthenticationPrincipal DefaultOAuth2User  user) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getPrincipal());
        return "/home";
    }

	
	@RequestMapping("/hi")
	public String callback(Authentication authentication, Principal principal, UserInfo user) {
		System.out.println("redirecting to home page");
		return "/index";
	}
}
