/**
 * Copyright (c) 2018 BNDY-NET. All Rights Reserved.
 * http://bndy.net
 */
package net.bndy.sc.service.sso.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.bndy.sc.service.sso.entity.AppRole;
import net.bndy.sc.service.sso.entity.AppUser;
import net.bndy.sc.service.sso.repository.AppRoleRepository;
import net.bndy.sc.service.sso.repository.AppUserRepository;

/**
 * @author Bendy Zhang
 * @version 1.0
 */
@Service
@Transactional
public class AppUserDetailsService implements UserDetailsService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private final static String DEFAULT_ADMIN_USER = "admin";
	private final static String DEFAULT_ADMIN_PASS = "1";
	private final static String DEFAULT_ADMIN_EMAIL = "zb@bndy.net";
	public final static String ROLE_ADMIN = "ROLE_ADMIN";
	
	@Autowired
	private AppUserRepository appUserRepository;
	@Autowired
	private AppRoleRepository appRoleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
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
		
		this.logger.info("The user " + input + " has been logged in.");
		
		return user;
	}
	
	public AppUser saveUser(AppUser user) {
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
			user.setAccountExpired(false);
			user.setAccountLocked(false);
			user.setCredentialsExpired(false);
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
	
	public AppUser initAdmin() {
	    if (this.appUserRepository.count() == 0) {
            AppUser admin = new AppUser();
            admin.setUsername(DEFAULT_ADMIN_USER);
            admin.setPassword(this.passwordEncoder.encode(DEFAULT_ADMIN_PASS));
            admin.setEmail(DEFAULT_ADMIN_EMAIL);
            admin.setAccountExpired(false);
            admin.setAccountLocked(false);
            admin.setCredentialsExpired(false);
            admin.setEnabled(true);
            
            AppRole adminRole = this.appRoleRepository.findByName(ROLE_ADMIN);
            if (adminRole == null) {
                adminRole = new AppRole();
                adminRole.setName(ROLE_ADMIN);
                adminRole = this.appRoleRepository.save(adminRole);
            }
            
            admin.setRoles(Arrays.asList(adminRole));
            admin = this.appUserRepository.save(admin);
            
            return admin;
	    }
	    return null;
	}
	
	public long countUser() {
	    return this.appUserRepository.count();
	}
}
