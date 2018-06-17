/**
 * Copyright (c) 2018 BNDY-NET. All Rights Reserved.
 * http://bndy.net
 */
package net.bndy.sc.ms.oauth;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Bendy Zhang
 * @version 1.0
 */
@Component
@ConfigurationProperties(prefix="oauth")
public class OAuthConfig {
	private String callback_url;
	private String[] allowed_origins;

	public String getCallback_url() {
		return callback_url;
	}

	public void setCallback_url(String callback_url) {
		this.callback_url = callback_url;
	}

	public String[] getAllowed_origins() {
		return allowed_origins;
	}

	public void setAllowed_origins(String[] allowed_origins) {
		this.allowed_origins = allowed_origins;
	}
	
}
