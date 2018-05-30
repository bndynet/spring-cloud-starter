package net.bndy.sc.ms.oauth.github;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import net.bndy.sc.ms.oauth.OAuthConfig;

@Component
@ConfigurationProperties(prefix="oauth.github")
public class GitHubConfig extends OAuthConfig {
	
	
}
