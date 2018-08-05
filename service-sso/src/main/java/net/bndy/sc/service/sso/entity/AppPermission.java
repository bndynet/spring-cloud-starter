/**
 * Copyright (c) 2018 BNDY-NET. All Rights Reserved.
 * http://bndy.net
 */
package net.bndy.sc.service.sso.entity;

import javax.persistence.Entity;

/**
 * @author Bendy Zhang
 * @version 1.0
 */
@Entity
public class AppPermission extends BaseIdEntity {

	private static final long serialVersionUID = 1L;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
