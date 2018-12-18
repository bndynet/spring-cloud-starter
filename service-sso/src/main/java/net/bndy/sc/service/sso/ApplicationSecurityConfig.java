/**
 * Copyright (c) 2018 BNDY-NET. All Rights Reserved.
 * http://bndy.net
 */
package net.bndy.sc.service.sso;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
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
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.fasterxml.jackson.databind.deser.std.StringArrayDeserializer;

import net.bndy.sc.service.sso.entity.OauthClientDetails;
import net.bndy.sc.service.sso.service.AppUserDetailsService;
import net.bndy.sc.service.sso.service.OauthClientDetailsService;

/**
 * @author Bendy Zhang
 * @version 1.0
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)  // for enabling @PreAuthorize support
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AppUserDetailsService appUserDetailsService;
    @Autowired
    private OauthClientDetailsService oauthClientDetailsService;

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
        http.cors().and()
            .csrf().disable()
            .httpBasic().disable().authorizeRequests()
            .antMatchers("/", "/login*").permitAll()
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
    
    @Bean
    public FilterRegistrationBean<CorsFilter> initCorsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // setAllowCredentials(true) is important, otherwise:
        // The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
        config.setAllowCredentials(true);

        // setAllowedHeaders is important! Without it, OPTIONS preflight request
        // will fail with 403 Invalid CORS request
        config.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));

        config.addAllowedMethod("*");

        List<OauthClientDetails> allClients = this.oauthClientDetailsService.getAllClients();
        for(OauthClientDetails client: allClients) {
            for(String redirectUrl: client.getRegisteredRedirectUri()) {
                if (redirectUrl != null) {
                    try {
                        URL url =new URL(redirectUrl);
                        config.addAllowedOrigin(url.getProtocol() + "://" + url.getHost() + (url.getPort() == 80 ? "" : ":" + url.getPort()));
                    } catch (MalformedURLException e) {
                        // TODO Auto-generated catch block
                        System.out.print(redirectUrl + " is not valid URL.");
                        e.printStackTrace();
                    }
                }
            }
        }
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
    
    @Configuration
    @EnableResourceServer
    protected class ResourceServer extends ResourceServerConfigurerAdapter {

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
            resources.tokenStore(tokenStore()).resourceId(Application.RESOURCE_ID);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.requestMatchers().antMatchers("/oauth/me").and().authorizeRequests().antMatchers("/oauth/me")
                    .authenticated()
//                    .access("#oauth2.hasScope('user_info')")
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

        @Bean
        protected ApprovalStore approvalStore() {
            return new JdbcApprovalStore(dataSource);
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
//                    .checkTokenAccess("isAuthenticated()");
                ;
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients.withClientDetails(oauthClientDetailsService);
        }
        
        
    }

}
