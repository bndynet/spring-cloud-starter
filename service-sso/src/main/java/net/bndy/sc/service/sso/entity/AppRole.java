/**
 * Copyright (c) 2018 BNDY-NET. All Rights Reserved.
 * http://bndy.net
 */
package net.bndy.sc.service.sso.entity;

import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;

/**
 * @author Bendy Zhang
 * @version 1.0
 */
@Entity
public class AppRole extends BaseIdEntity {

    private static final long serialVersionUID = 1L;

    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "app_role_permission", joinColumns = {
        @JoinColumn(name = "role_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "permission_id", referencedColumnName = "id")})
    private List<AppPermission> permissions;

    public String getName() {
        return name;
    }

    public List<AppPermission> getPermissions() {
        return permissions;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPermissions(List<AppPermission> permissions) {
        this.permissions = permissions;
    }

}
