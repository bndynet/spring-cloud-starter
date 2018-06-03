/**
 * Copyright (c) 2018 BNDY-NET. All Rights Reserved.
 * http://bndy.net
 */
package net.bndy.sc.ms.oauth.github;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import net.bndy.sc.ms.oauth.OAuthConfig;

/**
 * @author Bendy Zhang
 * @version 1.0
 */
@Component
@ConfigurationProperties(prefix="oauth.github")
public class GitHubConfig extends OAuthConfig {
	
	
}
