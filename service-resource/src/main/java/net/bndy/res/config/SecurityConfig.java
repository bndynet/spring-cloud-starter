package net.bndy.res.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()

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

                .antMatchers(HttpMethod.GET, "/api/**")
                .hasAnyAuthority("SCOPE_profile")
                .anyRequest()
                .authenticated()
                .and()
                .oauth2ResourceServer().jwt();
//            .and().authorizeRequests().antMatchers("/api/**").permitAll();
//        super.configure(http);
    }
}
