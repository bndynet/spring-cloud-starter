/**
 * Copyright (c) 2018 BNDY-NET. All Rights Reserved.
 * http://bndy.net
 */
package net.bndy.sc.service.sso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import net.bndy.sc.service.sso.entity.OauthClientDetails;
import net.bndy.sc.service.sso.repository.OauthClientDetailsRepository;

/**
 * @author Bendy Zhang
 * @version 1.0
 */
@Service
public class OauthClientDetailsService implements ClientDetailsService {
	
	@Autowired
	private OauthClientDetailsRepository oauthClientDetailsRepository;

	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		if (clientId == null || clientId.trim().isEmpty()) {
			throw new ClientRegistrationException("The client id can not be empty");
		}
		
		OauthClientDetails clientDetails = this.oauthClientDetailsRepository.findByClientId(clientId);
		if (clientDetails == null) {
			throw new ClientRegistrationException("The client id " + clientId + " can not be found");
		}
		
		return clientDetails;
	}
	
	public List<OauthClientDetails> getAllClients() {
		return this.oauthClientDetailsRepository.findAll();
	}
}
