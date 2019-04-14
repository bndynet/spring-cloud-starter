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
public class OauthRefreshToken {
    @Id
    private String tokenId;
    @Column(columnDefinition = "BLOB")
    private byte[] token;
    @Column(columnDefinition = "BLOB")
    private byte[] authentication;
}
