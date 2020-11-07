package net.bndy.res.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

/**
 * @author Bendy Zhang
 * @version 1.0
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    String jwkSetUri;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable()
                .authorizeRequests()

                // enable knife4j access
                .antMatchers(
                        "/doc.html",
                        "/doc.html/**",
                        "/webjars/**",
                        "/swagger-resources",
                        "/v2/api-docs",
                        "/api/hi"
                ).permitAll()

                // APIs access
                .antMatchers(HttpMethod.GET, "/api/hi/who")
                .hasAuthority("SCOPE_profile")
                .antMatchers(HttpMethod.GET, "/api/hi/data")
                .hasAuthority("SCOPE_data")

                .anyRequest()
                .authenticated()
                .and()
                .oauth2ResourceServer().jwt();
    }

    @Bean
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withJwkSetUri(this.jwkSetUri).build();
    }
}
