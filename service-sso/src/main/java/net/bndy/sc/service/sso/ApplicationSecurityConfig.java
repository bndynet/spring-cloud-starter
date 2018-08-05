/**
 * Copyright (c) 2018 BNDY-NET. All Rights Reserved.
 * http://bndy.net
 */
package net.bndy.sc.service.sso;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import net.bndy.sc.lib.SecurityConfig;
import net.bndy.sc.service.sso.service.AppUserDetailsService;

/**
 * @author Bendy Zhang
 * @version 1.0
 */
@Configuration
@Order(99)
public class ApplicationSecurityConfig extends SecurityConfig {
	private static final String SERVER_RESOURCE_ID = "oauth2-server";
	
	@Autowired
	private DataSource dataSource;
	@Autowired
	private AppUserDetailsService appUserDetailsService;
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); // PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
	
	@Bean
	public TokenStore tokenStore() {
		return new JdbcTokenStore(dataSource);
	}
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    		http
    			.csrf().disable()
    		    .httpBasic().disable()
    			.authorizeRequests()
    			.antMatchers("/", "/static/**").permitAll()
    			.anyRequest().authenticated()
    			.and().formLogin().loginPage("/login").permitAll()
    				.and().rememberMe().rememberMeParameter("rememberMe")
    			.and().logout().permitAll()
//    				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))	// required if enable CSRF, because CSRF requires a Post for logging out with CSRF code like login
    			;
    }
     
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(appUserDetailsService)
			.passwordEncoder(passwordEncoder());
    }
    
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
			return super.authenticationManager();
    }
    
    @Configuration
    @EnableResourceServer
    protected class ResourceServer extends ResourceServerConfigurerAdapter {

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
            resources.tokenStore(tokenStore()).resourceId(SERVER_RESOURCE_ID);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.requestMatchers().antMatchers("/user/me").and().authorizeRequests().antMatchers("/user/me").access("#oauth2.hasScope('user_info')");
        }
    }
    
    @Configuration
    @EnableAuthorizationServer
    protected class AuthConfig extends AuthorizationServerConfigurerAdapter {

        @Autowired
        private AuthenticationManager authenticationManager;
		@Autowired
		private AppUserDetailsService appUserDetailsService;
        

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints.authenticationManager(authenticationManager)
            	.userDetailsService(appUserDetailsService)
            	.tokenStore(tokenStore())
            	.approvalStoreDisabled();
        }
        
        @Override
        public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
				security.allowFormAuthenticationForClients()
					.tokenKeyAccess("permitAll()")
					.checkTokenAccess("isAuthenticated()");
        }
        
        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients.inMemory()
				.withClient("foo")
				.secret("bar")
				.authorizedGrantTypes("authorization_code", "refresh_token")
				.scopes("user_info")
				.redirectUris("http://127.0.0.1:9111/login/oauth2/code/home", "https://www.getpostman.com/oauth2/callback")
				.resourceIds(SERVER_RESOURCE_ID)
//				.autoApprove(true)
				;
        }
    }

}