/**
 * Copyright (c) 2018 BNDY-NET. All Rights Reserved.
 * Created at 2018/12/21 3:37 PM
 * http://bndy.net
 */
package net.bndy.sc.ms.oauth.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Bendy Zhang
 * @version 1.0
 */
@Component
@ConfigurationProperties(prefix = "app")
public class AppConfiguration {

    public String getCallbackUri() {
        return callbackUri;
    }

    public void setCallbackUri(String callbackUri) {
        this.callbackUri = callbackUri;
    }

    public String[] getAllowedOrigins() {
        return allowedOrigins;
    }

    public void setAllowedOrigins(String[] allowedOrigins) {
        this.allowedOrigins = allowedOrigins;
    }

    public Map<String, ClientConfiguration> getClients() {
        return clients;
    }

    public void setClients(Map<String, ClientConfiguration> clients) {
        this.clients = clients;
    }

    private String callbackUri;
    private String[] allowedOrigins;
    private Map<String, ClientConfiguration> clients;
}
