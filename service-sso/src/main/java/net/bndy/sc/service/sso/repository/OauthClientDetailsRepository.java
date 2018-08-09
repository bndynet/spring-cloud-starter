/**
 * Copyright (c) 2018 BNDY-NET. All Rights Reserved.
 * http://bndy.net
 */
package net.bndy.sc.service.sso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.bndy.sc.service.sso.entity.OauthClientDetails;

/**
 * @author Bendy Zhang
 * @version 1.0
 */
public interface OauthClientDetailsRepository extends JpaRepository<OauthClientDetails, String> {

	OauthClientDetails findByClientId(String clientId);
}
