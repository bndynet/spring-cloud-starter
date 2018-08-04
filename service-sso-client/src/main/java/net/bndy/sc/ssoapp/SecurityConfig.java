package net.bndy.sc.ssoapp;

import javax.servlet.Filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests() //
		.anyRequest().authenticated()
		.and() //
		.oauth2Login() //
		.userInfoEndpoint()
//		.customUserType(UserInfo.class, "github")
//		.customUserType(UserInfo.class, "home")
		;
	}
}
