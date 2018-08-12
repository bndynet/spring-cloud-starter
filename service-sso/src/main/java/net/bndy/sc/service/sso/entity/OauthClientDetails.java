/**
 * Copyright (c) 2018 BNDY-NET. All Rights Reserved.
 * http://bndy.net
 */
package net.bndy.sc.service.sso.entity;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;

/**
 * @author Bendy Zhang
 * @version 1.0
 */
@Entity
public class OauthClientDetails implements ClientDetails {
	private static final long serialVersionUID = 1L;
	@Autowired
	@Transient
	private PasswordEncoder passwordEncoder;
	
	@Id
	private String clientId;
    private String clientName;
    private String clientSecret;
    private String clientSecretRaw;
    public String getClientSecretRaw() {
		return clientSecretRaw;
	}
	public void setClientSecretRaw(String clientSecretRaw) {
		this.clientSecretRaw = clientSecretRaw;
		this.clientSecret = this.passwordEncoder.encode(clientSecretRaw);
	}
	private String resourceIds;
    private String scope;
    private String authorizedGrantTypes;
    private String redirectUri;

    public String getRedirectUri() {
		return redirectUri;
	}
	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}
	private String authorities;
    private Integer accessTokenValidity;
    private Integer refreshTokenValidity;
    private String additionalInformation;
    private Boolean autoapprove;
    
	public Boolean getAutoapprove() {
		return autoapprove;
	}
	public void setAutoapprove(Boolean autoapprove) {
		this.autoapprove = autoapprove;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public void setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds;
	}
	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}
	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}
	public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
		this.authorizedGrantTypes = authorizedGrantTypes;
	}
    
	@Override
	public String getClientId() {
		return clientId;
	}
	@Override
	public Set<String> getResourceIds() {
		if (resourceIds != null && !resourceIds.trim().isEmpty()) {
			return Arrays.asList(resourceIds.split(";")).stream().collect(Collectors.toSet());
		}
		return Collections.emptySet();
	}
	@Override
	public boolean isSecretRequired() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public String getClientSecret() {
		return clientSecret;
	}
	@Override
	public boolean isScoped() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Set<String> getScope() {
		if (scope != null && !scope.isEmpty()) {
			return Arrays.asList(scope.split(";")).stream().collect(Collectors.toSet());
		}
		return Collections.emptySet();
	}
	@Override
	public Set<String> getAuthorizedGrantTypes() {
		if (authorizedGrantTypes != null && !authorizedGrantTypes.isEmpty()) {
			return Arrays.asList(authorizedGrantTypes.split(";")).stream().collect(Collectors.toSet());
		}
		return Collections.emptySet();
	}
	@Override
	public Set<String> getRegisteredRedirectUri() {
		if (redirectUri!= null && !redirectUri.isEmpty()) {
			return Arrays.asList(redirectUri.split(";")).stream().collect(Collectors.toSet());
		}
		return Collections.emptySet();
	}
	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> result = new HashSet<GrantedAuthority>();
		if (authorities!=null && !authorities.isEmpty()) {
			Arrays.asList(authorities.split(";")).stream().forEach(a -> {
				result.add(new SimpleGrantedAuthority(a));
			});
		}

		return result;
	}
	@Override
	public Integer getAccessTokenValiditySeconds() {
		return accessTokenValidity;
	}
	@Override
	public Integer getRefreshTokenValiditySeconds() {
		return refreshTokenValidity;
	}
	@Override
	public boolean isAutoApprove(String scope) {
		return autoapprove;
	}
	@Override
	public Map<String, Object> getAdditionalInformation() {
		HashMap<String, Object> map = new HashMap<>();
		// TODO: convert JSON string to Object
		Object jsonObject = additionalInformation;
		return null;
	}
}
