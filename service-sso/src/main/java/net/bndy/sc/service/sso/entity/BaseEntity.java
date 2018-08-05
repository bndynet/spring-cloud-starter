/**
 * Copyright (c) 2018 BNDY-NET. All Rights Reserved.
 * http://bndy.net
 */
package net.bndy.sc.service.sso.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * @author Bendy Zhang
 * @version 1.0
 */
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Version
	protected Long version;

	@CreationTimestamp
	protected LocalDateTime createdOn;

	@UpdateTimestamp
	protected LocalDateTime updatedOn;
}
