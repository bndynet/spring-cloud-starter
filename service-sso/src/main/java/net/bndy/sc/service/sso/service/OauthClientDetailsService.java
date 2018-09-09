/**
 * Copyright (c) 2018 BNDY-NET. All Rights Reserved.
 * http://bndy.net
 */
package net.bndy.sc.service.sso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.bndy.lib.StringHelper;
import net.bndy.sc.service.sso.entity.OauthClientDetails;
import net.bndy.sc.service.sso.repository.OauthClientDetailsRepository;

/**
 * @author Bendy Zhang
 * @version 1.0
 */
@Service
@Transactional
public class OauthClientDetailsService implements ClientDetailsService {
    
    public final static String PERMISSION_READ = "CLIENT:R";
    public final static String PERMISSION_WRITE = "CLIENT:W";

    private final static int CLIENT_ID_LENGTH = 10;
    private final static int CLIENT_SECRET_LENGTH = 20;
    private final static String DEFAULT_AUTHORIZED_GRANT_TYPES = "authorization_code;refresh_token";

    @Autowired
    private OauthClientDetailsRepository oauthClientDetailsRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

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

    public OauthClientDetails save(OauthClientDetails clientDetails) {
        
        if (clientDetails.getRedirectUri() != null) {
            clientDetails.setRedirectUri(clientDetails.getRedirectUri().trim().replaceAll("[\r|\n]+", ";"));
        }

        if (clientDetails.getClientId() != null && !clientDetails.getClientId().trim().isEmpty()) {
            OauthClientDetails originClient = this.findByClientId(clientDetails.getClientId());
            originClient.setClientName(clientDetails.getClientName());
            originClient.setAutoapprove(clientDetails.getAutoapprove());
            originClient.setRedirectUri(clientDetails.getRedirectUri());
            originClient.setOwner(clientDetails.getOwner());
            clientDetails = originClient;

        } else {
            clientDetails.setClientId(StringHelper.generateRandomCode(CLIENT_ID_LENGTH));
            clientDetails.setClientSecretRaw(StringHelper.generateRandomCode(CLIENT_SECRET_LENGTH));
            clientDetails.setClientSecret(this.passwordEncoder.encode(clientDetails.getClientSecretRaw()));
            clientDetails.setResourceIds("*");
            clientDetails.setScope("all");
            clientDetails.setAuthorizedGrantTypes(DEFAULT_AUTHORIZED_GRANT_TYPES);
            clientDetails.setAccessTokenValidity(60);
            clientDetails.setRefreshTokenValidity(60);
        }
        clientDetails = this.oauthClientDetailsRepository.save(clientDetails);
        return clientDetails;
    }

    public OauthClientDetails findByClientId(String clientId) {
        return this.oauthClientDetailsRepository.findByClientId(clientId);
    }

    public void remove(String id) {
        this.oauthClientDetailsRepository.deleteById(id);
    }

	public long countClient() {
	    return this.oauthClientDetailsRepository.count();
	}
}
