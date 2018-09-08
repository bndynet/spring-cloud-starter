/**
 * Copyright (c) 2018 BNDY-NET. All Rights Reserved.
 * http://bndy.net
 */
package net.bndy.sc.service.sso.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.bndy.sc.service.sso.entity.AppUser;
import net.bndy.sc.service.sso.exception.ApplicationException;
import net.bndy.sc.service.sso.exception.ErrorCode;
import net.bndy.sc.service.sso.repository.AppUserRepository;

/**
 * @author Bendy Zhang
 * @version 1.0
 */
@Service
@Transactional
public class AppUserDetailsService implements UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public final static String PERMISSION_READ = "USER:R";
	public final static String PERMISSION_WRITE = "USER:W";
	
	public final static String ROLE_ADMIN = "ROLE_ADMIN";
	public final static String ROLE_READONLY_USER = "ROLE_READONLY";
	
	@Autowired
	private AppUserRepository appUserRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private MessageSource messageSource;
	
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
		
		this.logger.info("The user " + input + " try to log in.");
		
		return user;
	}
	
	public AppUser saveUser(AppUser user) {
	    // check whether user name exists
	    AppUser dbUser = this.appUserRepository.findByUsername(user.getUsername());
	    if (dbUser != null && !dbUser.getId().equals(user.getId())) {
	        throw new ApplicationException(this.messageSource, ErrorCode.USER_EXISTED_USERNAME);
	    }
		if (user.getId() != null) {
			AppUser originUser = this.findById(user.getId());
			if (originUser != null && user.getPassword() != null && !user.getPassword().trim().isEmpty()) {
				originUser.setPassword(passwordEncoder.encode(user.getPassword()));
			}
			originUser.setEmail(user.getEmail());
			originUser.setEnabled(user.isEnabled());
			originUser.setUsername(user.getUsername());
			user = originUser;
		} else {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		}
		user = this.appUserRepository.save(user);
		return user;
	}
	
	public List<AppUser> getAllUsers() {
		return this.appUserRepository.findAll();
	}
	
	public AppUser findById(long id) {
		Optional<AppUser> optional = this.appUserRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	public List<AppUser> search(String keywords){
	    return this.appUserRepository.findByUsernameOrEmailContaining(keywords);
	}
	
	public void removeAppUser(long id) {
		this.appUserRepository.deleteById(id);
	}
	
	public long countUser() {
	    return this.appUserRepository.count();
	}
}
