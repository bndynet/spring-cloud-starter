/**
 * Copyright (c) 2018 BNDY-NET. All Rights Reserved.
 * http://bndy.net
 */
package net.bndy.sc.service.sso;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.endpoint.AuthorizationEndpoint;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import net.bndy.sc.service.sso.service.AppUserDetailsService;
import net.bndy.sc.service.sso.service.OauthClientDetailsService;

/**
 * @author Bendy Zhang
 * @version 1.0
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)  // for enabling @PreAuthorize support
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String SERVER_RESOURCE_ID = "sso-resource"; //$NON-NLS-1$

    @Autowired
    private DataSource dataSource;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AppUserDetailsService appUserDetailsService;

    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/hi", "/static/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().httpBasic().disable().authorizeRequests().antMatchers("/", "/login*").permitAll()
            .antMatchers("/admin", "/admin/**").hasAnyAuthority(AppUserDetailsService.ROLE_ADMIN, AppUserDetailsService.ROLE_READONLY_USER)
            .anyRequest().authenticated().and().formLogin().defaultSuccessUrl("/").loginPage("/login").permitAll().and()
            .rememberMe().rememberMeParameter("rememberMe").and().logout().permitAll()
//    				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))	// required if enable CSRF, because CSRF requires a Post for logging out with CSRF code like login
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(appUserDetailsService).passwordEncoder(this.passwordEncoder);
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
            http.requestMatchers().antMatchers("/oauth/me").and().authorizeRequests().antMatchers("/oauth/me")
                    .authenticated()
//            		.access("#oauth2.hasScope('user_info')")
            ;
        }
    }

    @Configuration
    @EnableAuthorizationServer
    protected class AuthConfig extends AuthorizationServerConfigurerAdapter {

        @Autowired
        private AuthenticationManager authenticationManager;
        @Autowired
        private AppUserDetailsService appUserDetailsService;
        @Autowired
        private OauthClientDetailsService oauthClientDetailsService;
        @Autowired
        private AuthorizationEndpoint authorizationEndpoint;

        @Bean
        protected ApprovalStore approvalStore() {
            return new JdbcApprovalStore(dataSource);
        }
        
        @PostConstruct
        public void init() {
            // custom your pages
            this.authorizationEndpoint.setErrorPage("forward:/error");  // default is forward:/oauth/error
            this.authorizationEndpoint.setUserApprovalPage("forward:/oauth/confirm_access");
        }

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints.authenticationManager(authenticationManager).userDetailsService(appUserDetailsService)
                    .tokenStore(tokenStore()).approvalStore(approvalStore())
                    
                    // Below will be used in org.springframework.security.oauth2.provider.endpoint.AuthorizationEndpoint L503,
                    // for redirecting to global exception handling via throwing exception.
                    // By default, the exception does not be throwing.
//                    .exceptionTranslator(exception -> {
//                        throw exception;
//                    })
                    
                    ;
        }

        @Override
        public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
            security.allowFormAuthenticationForClients().tokenKeyAccess("permitAll()")
                    .checkTokenAccess("isAuthenticated()");
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients.withClientDetails(oauthClientDetailsService);
        }
    }

}