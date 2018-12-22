/**
 * Copyright (c) 2018 BNDY-NET. All Rights Reserved.
 * Created at 2018/12/22 2:12 PM
 * http://bndy.net
 */
package net.bndy.sc.ms.oauth;

import java.io.Serializable;

/**
 * @author Bendy Zhang 
 * @version 1.0
 */

public class TokenEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String access_token;
    private String token_type;
    private String refresh_token;
    private String scope;
    private Integer expires_in;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Integer getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Integer expires_in) {
        this.expires_in = expires_in;
    }
}
