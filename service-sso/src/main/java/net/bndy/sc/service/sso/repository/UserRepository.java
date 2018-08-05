/**
 * Copyright (c) 2018 BNDY-NET. All Rights Reserved.
 * http://bndy.net
 */
package net.bndy.sc.service.sso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.bndy.sc.service.sso.entity.AppUser;

/**
 * @author Bendy Zhang
 * @version 1.0
 */
public interface UserRepository extends JpaRepository<AppUser, String> {

	AppUser findByEmail(String email);
	AppUser findByUsername(String username);
}
