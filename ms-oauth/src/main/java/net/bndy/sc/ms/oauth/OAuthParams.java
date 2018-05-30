package net.bndy.sc.ms.oauth;

import java.io.Serializable;

public class OAuthParams implements Serializable {
	
	private static final long serialVersionUID = 1L;

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

	public String getRedirect_uri() {
		return redirect_uri;
	}

	public void setRedirect_uri(String redirect_uri) {
		this.redirect_uri = redirect_uri;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAllow_signup() {
		return allow_signup;
	}

	public void setAllow_signup(String allow_signup) {
		this.allow_signup = allow_signup;
	}

	private String client_id;
	private String client_secret;
	private String redirect_uri;
	private String scope;
	private String state;
	private String code;
	private String allow_signup;
	private String access_token;
	private String token_type;

	public String getToken_type() {
		return token_type;
	}

	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	
	public OAuthParams() {
	}

	public OAuthParams(String client_id, String redirect_uri) {
		this.client_id = client_id;
		this.redirect_uri = redirect_uri;
	}
	
	public OAuthParams(String client_id, String client_secret, String redirect_uri, String code) {
		this.client_id = client_id;
		this.client_secret = client_secret;
		this.redirect_uri = redirect_uri;
		this.code = code;
	}
}
