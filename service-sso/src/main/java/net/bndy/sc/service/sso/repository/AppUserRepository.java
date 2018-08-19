/**
 * Copyright (c) 2018 BNDY-NET. All Rights Reserved.
 * http://bndy.net
 */
package net.bndy.sc.service.sso.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.bndy.sc.service.sso.entity.AppUser;

/**
 * @author Bendy Zhang
 * @version 1.0
 */
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

	AppUser findByEmail(String email);
	AppUser findByUsername(String username);
	
	@Query("select e from #{#entityName} e where e.username like %:keywords% or e.email like %:keywords%")
	List<AppUser> findByUsernameOrEmailContaining(String keywords);
}
