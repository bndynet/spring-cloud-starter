/**
 * Copyright (c) 2018 BNDY-NET. All Rights Reserved.
 * http://bndy.net
 */
package net.bndy.sc.service.sso.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Bendy Zhang
 * @version 1.0
 */
@Entity
public class OauthClientToken {
    @Id
    private String tokenId;
    @Column(columnDefinition = "BLOB")
    private byte[] token;
    @SuppressWarnings("unused")
    private String authenticationId;
    @SuppressWarnings("unused")
    private String userName;
    @SuppressWarnings("unused")
    private String clientId;
}
