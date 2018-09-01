/**
 * Copyright (c) 2018 BNDY-NET. All Rights Reserved.
 * http://bndy.net
 */
package net.bndy.sc.service.sso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import net.bndy.sc.service.sso.entity.AppPermission;
import net.bndy.sc.service.sso.entity.AppRole;
import net.bndy.sc.service.sso.entity.AppUser;
import net.bndy.sc.service.sso.service.AppPermissionService;
import net.bndy.sc.service.sso.service.AppRoleService;
import net.bndy.sc.service.sso.service.AppUserDetailsService;
import net.bndy.sc.service.sso.service.OauthClientDetailsService;

/**
 * @author Bendy Zhang
 * @version 1.0
 */
@Component
public class ApplicationInitializaton implements ApplicationListener<ContextRefreshedEvent> {

    private final static String DEFAULT_ADMIN_USER = "admin";
    private final static String DEFAULT_ADMIN_PASS = "pass";
    private final static String DEFAULT_ADMIN_EMAIL = "zb@bndy.net";

    private final static String DEFAULT_READONLY_USER = "test";
    private final static String DEFAULT_READONLY_PASS = "pass";
    private final static String DEFAULT_READONLY_EMAIL = "zb@bndy.net";

    private final static String[] ADMIN_PERMISSIONS = { 
            AppUserDetailsService.PERMISSION_READ,
            AppUserDetailsService.PERMISSION_WRITE, 
            OauthClientDetailsService.PERMISSION_READ,
            OauthClientDetailsService.PERMISSION_WRITE,
            };
    private final static String[] READONLY_USER_PERMISSIONS = { 
            AppUserDetailsService.PERMISSION_READ, 
            OauthClientDetailsService.PERMISSION_READ,
            };

    boolean alreadySetup = false;

    @Autowired
    private AppUserDetailsService appUserDetailsService;
    @Autowired
    private AppRoleService appRoleService;
    @Autowired
    private AppPermissionService appPermissionService;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;

        List<AppPermission> adminPermissions = new ArrayList<AppPermission>();
        for (String permission : ADMIN_PERMISSIONS) {
            adminPermissions.add(this.appPermissionService.createIfNotFound(permission));
        }
        List<AppPermission> readonlyUserPermissions = new ArrayList<AppPermission>();
        for (String permission : READONLY_USER_PERMISSIONS) {
            readonlyUserPermissions.add(this.appPermissionService.createIfNotFound(permission));
        }
        
        AppRole roleAdmin = this.appRoleService.createIfNotFound(AppUserDetailsService.ROLE_ADMIN, adminPermissions);
        AppRole roleReadonlyUser = this.appRoleService.createIfNotFound(AppUserDetailsService.ROLE_READONLY_USER, readonlyUserPermissions);

        if (this.appUserDetailsService.countUser() == 0) {
            AppUser admin = new AppUser();
            admin.setUsername(DEFAULT_ADMIN_USER);
            admin.setPassword(DEFAULT_ADMIN_PASS);
            admin.setEmail(DEFAULT_ADMIN_EMAIL);
            admin.setAccountExpired(false);
            admin.setAccountLocked(false);
            admin.setCredentialsExpired(false);
            admin.setEnabled(true);
            admin.setRoles(Arrays.asList(roleAdmin));
            admin = this.appUserDetailsService.saveUser(admin);

            AppUser readonlyUser = new AppUser();
            readonlyUser.setUsername(DEFAULT_READONLY_USER);
            readonlyUser.setPassword(DEFAULT_READONLY_PASS);
            readonlyUser.setEmail(DEFAULT_READONLY_EMAIL);
            readonlyUser.setAccountExpired(false);
            readonlyUser.setAccountLocked(false);
            readonlyUser.setCredentialsExpired(false);
            readonlyUser.setEnabled(true);
            readonlyUser.setRoles(Arrays.asList(roleReadonlyUser));
            readonlyUser = this.appUserDetailsService.saveUser(readonlyUser);
        }

        alreadySetup = true;
    }
}
