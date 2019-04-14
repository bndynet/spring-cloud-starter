/**
 * Copyright (c) 2018 BNDY-NET. All Rights Reserved.
 * http://bndy.net
 */
package net.bndy.sc.service.sso.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.bndy.sc.service.sso.entity.AppPermission;
import net.bndy.sc.service.sso.entity.AppRole;
import net.bndy.sc.service.sso.repository.AppRoleRepository;

/**
 * @author Bendy Zhang
 * @version 1.0
 */
@Service
@Transactional(rollbackOn = Exception.class)
public class AppRoleService {
    
    @Autowired
    private AppRoleRepository appRoleRepository;
    
    public AppRole createIfNotFound(String name, List<AppPermission> permissions) {
        AppRole role = this.appRoleRepository.findByName(name);
        if (role == null) {
            role = new AppRole();
            role.setName(name);
        }
        role.setPermissions(permissions);
        this.appRoleRepository.save(role);
        return role;
    }
    
    public AppRole findByName(String name) {
        return this.appRoleRepository.findByName(name);
    }
}
