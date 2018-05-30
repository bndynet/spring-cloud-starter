package net.bndy.sc.ms.oauth;

import net.bndy.sc.ms.oauth.Url;

public class OAuthConfig {
	private Url url;
	private String client_id;
	private String client_secret;
	
	public Url getUrl() {
		return url;
	}

	public void setUrl(Url url) {
		this.url = url;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public String getClient_secret() {
		return client_secret;
	}

	public void setClient_secret(String client_secret) {
		this.client_secret = client_secret;
	}
}
