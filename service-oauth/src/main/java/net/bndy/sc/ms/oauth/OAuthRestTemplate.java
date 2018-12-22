/**
 * Copyright (c) 2018 BNDY-NET. All Rights Reserved.
 * Created at 2018/12/22 1:49 PM
 * http://bndy.net
 */
package net.bndy.sc.ms.oauth;

import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Base64;
import java.util.Map;

/**
 * @author Bendy Zhang
 * @version 1.0
 */
public class OAuthRestTemplate extends RestTemplate {

    private String clientId;
    private String clientSecret;

    public OAuthRestTemplate(String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    private String getBase64Credentials() {
        String credentialsString = this.clientId + ":" + this.clientSecret;
        return Base64.getEncoder().encodeToString(credentialsString.getBytes());
    }

    public TokenEntity getToken(String tokenUrl, String code, String redirectUrl) {
        return getToken(tokenUrl, code, redirectUrl, null);
    }

    public TokenEntity getToken(String tokenUrl, String code, String redirectUrl, Map<String, Object> additionalUrlParameters) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Basic " + getBase64Credentials());
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        final StringBuffer accessTokenUrl = new StringBuffer();
        accessTokenUrl.append(tokenUrl + "?code=" + code
            + "&grant_type=authorization_code"
            + "&redirect_uri=" + redirectUrl);
        if (additionalUrlParameters != null) {
            additionalUrlParameters.forEach((k, v) -> accessTokenUrl.append("&" + k + "=" + v));
        }
        ResponseEntity<TokenEntity> response = this.exchange(accessTokenUrl.toString(), HttpMethod.POST, requestEntity, TokenEntity.class);
        return response.getBody();
    }
}
