/**
 * Copyright (c) 2018 BNDY-NET. All Rights Reserved.
 * http://bndy.net
 */
package net.bndy.sc.service.sso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.bndy.sc.service.sso.entity.AppRole;

/**
 * @author Bendy Zhang
 * @version 1.0
 */
public interface AppRoleRepository extends JpaRepository<AppRole, Long>{
    
    AppRole findByName(String name);
}
