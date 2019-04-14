/**
 * Copyright (c) 2018 BNDY-NET. All Rights Reserved.
 * http://bndy.net
 */
package net.bndy.sc.service.sso.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Bendy Zhang
 * @version 1.0
 */
@Entity
public class OauthApprovals implements Serializable {
    /**
     * Composed-id MUST be serializable
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "userid")
    private String userId;
    @Id
    @Column(name = "clientid")
    private String clientId;
    @SuppressWarnings("unused")
    private String scope;
    @SuppressWarnings("unused")
    private String status;
    @Column(name = "expiresat")
    private Timestamp expiresAt;
    @Column(name = "lastmodifiedat")
    private Timestamp lastModifiedAt;
}
