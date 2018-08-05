package net.bndy.sc.service.sso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import net.bndy.sc.service.sso.dao.OauthClientDetailsMapper;
import net.bndy.sc.service.sso.model.OauthClientDetails;
 
@RestController
public class UserController {
	@Autowired
	private OauthClientDetailsMapper oauthClientDetailsMapper;
	
    @GetMapping("/user/me")
    public Object user(Authentication principal) {
    	    OauthClientDetails oauthClientDetails = new OauthClientDetails();
    	    oauthClientDetails.setAccess_token_validity(1);
    	    oauthClientDetails.setClient_id("aewfawfw");
    		this.oauthClientDetailsMapper.insert(oauthClientDetails);
    	
        return principal.getPrincipal();
    }
}