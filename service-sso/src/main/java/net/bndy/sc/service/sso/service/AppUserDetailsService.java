/**
 * Copyright (c) 2018 BNDY-NET. All Rights Reserved.
 * http://bndy.net
 */
package net.bndy.sc.service.sso.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import net.bndy.sc.service.sso.entity.AppUser;
import net.bndy.sc.service.sso.repository.AppUserRepository;

/**
 * @author Bendy Zhang
 * @version 1.0
 */
@Service
public class AppUserDetailsService implements UserDetailsService {
	
	@Autowired
	private AppUserRepository appUserRepository;
	
	@Override
	public UserDetails loadUserByUsername(String input) throws UsernameNotFoundException {
		AppUser user;
		if (input.contains("@")) {
			user = this.appUserRepository.findByEmail(input);
		} else {
			user = this.appUserRepository.findByUsername(input);
		}
		
		if (user == null) {
			throw new UsernameNotFoundException("User " + input + " can not be found");
		}
		
		return user;
	}
}
