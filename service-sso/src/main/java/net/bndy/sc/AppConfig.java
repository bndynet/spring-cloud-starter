/**
 * Copyright (c) 2018 BNDY-NET. All Rights Reserved.
 * http://bndy.net
 */
package net.bndy.sc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
 
@Configuration
public class AppConfig extends WebMvcConfigurerAdapter {
 
   @Bean
   public LocaleResolver localeResolver() {
       CookieLocaleResolver resolver = new CookieLocaleResolver();
       resolver.setCookiePath("/");
       resolver.setCookieName("locale");
       int ageInSeconds = 30 * 24 * 60 * 60;
       resolver.setCookieMaxAge(ageInSeconds);
       return resolver;
   }
 
   @Bean
   public LocaleChangeInterceptor localeChangeInterceptor() {
       LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
       lci.setParamName("lang");
       return lci;
   }
 
   @Override
   public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(localeChangeInterceptor());
   }
}