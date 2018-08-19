/**
 * Copyright (c) 2018 BNDY-NET. All Rights Reserved.
 * http://bndy.net
 */
package net.bndy.sc.service.sso.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Bendy Zhang
 * @version 1.0
 */
@Entity
public class AppUser extends BaseIdEntity implements UserDetails {
	
	private static final long serialVersionUID = 1L;

	@Column(name = "username", nullable = false)
	private String username;

	@Column(name = "email", nullable = false)
	private String email;

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@JsonIgnore
	@Column(name = "enabled", nullable = false)
	private boolean enabled;

	@JsonIgnore
	@Column(name = "password", nullable = false)
	private String password;
	
	@JsonIgnore
	@Column(name = "account_locked", nullable = false)
	private boolean accountLocked;

	@JsonIgnore
	@Column(name = "account_expired", nullable = false)
	private boolean accountExpired;

	@JsonIgnore
	@Column(name = "credentials_expired", nullable = false)
	private boolean credentialsExpired;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "app_user_role", joinColumns = {
			@JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "role_id", referencedColumnName = "id") })
	private List<AppRole> roles;
	
	
	public List<AppRole> getRoles() {
        return this.roles;
    }
    public void setRoles(List<AppRole> roles) {
        this.roles = roles;
    }
    public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public void setAccountExpired(boolean accountExpired) {
		this.accountExpired = accountExpired;
	}
	public void setAccountLocked(boolean accountLocked) {
		this.accountLocked = accountLocked;
	}
	public void setCredentialsExpired(boolean credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();

		roles.forEach(r -> {
			authorities.add(new SimpleGrantedAuthority(r.getName()));
			r.getPermissions().forEach(p -> {
				authorities.add(new SimpleGrantedAuthority(p.getName()));
			});
		});

		return authorities;
	}
	@Override
	public String getPassword() {
		return password;
	}
	@Override
	public String getUsername() {
		return username;
	}
	@Override
	public boolean isAccountNonExpired() {
		return !accountExpired;
	}
	@Override
	public boolean isAccountNonLocked() {
		return !accountLocked;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return !credentialsExpired;
	}
	@Override
	public boolean isEnabled() {
		return enabled;
	}
}
