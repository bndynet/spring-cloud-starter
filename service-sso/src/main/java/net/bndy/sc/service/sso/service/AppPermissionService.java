/**
 * Copyright (c) 2018 BNDY-NET. All Rights Reserved.
 * http://bndy.net
 */
package net.bndy.sc.service.sso.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.bndy.lib.data.JpaService;
import net.bndy.sc.service.sso.entity.AppPermission;
import net.bndy.sc.service.sso.repository.AppPermissionRepository;

/**
 * @author Bendy Zhang
 * @version 1.0
 */
@Service
@Transactional
public class AppPermissionService extends JpaService<AppPermission, Long> {
    
    @Autowired
    private AppPermissionRepository appPermissionRepository;
    
    public AppPermission createIfNotFound(String name) {
        AppPermission permission = this.appPermissionRepository.findByName(name);
        if (permission == null) {
            permission = new AppPermission();
            permission.setName(name);
            this.appPermissionRepository.save(permission);
        }
        return permission;
    }
}
