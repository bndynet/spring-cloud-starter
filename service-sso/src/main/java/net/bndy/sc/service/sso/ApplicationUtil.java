/**
 * Copyright (c) 2018 BNDY-NET. All Rights Reserved.
 * http://bndy.net
 */
package net.bndy.sc.service.sso;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author Bendy Zhang
 * @version 1.0
 */
@Component
public class ApplicationUtil {

    private static MessageSource messageSource;
    private final static String I18N_ERROR_PREFIX = "error.";
    private final static String I18N_OAUTH_ERROR_PREFIX = "oauthError.";
    
    private static PasswordEncoder passwordEncoder;
    
    
    @Autowired
    private MessageSource _messageSource;
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        // this encoder will be also used to encode client secret, so MUST encode it
        // into store
        return new BCryptPasswordEncoder();
    }
    
    @PostConstruct
    public void init() {
        messageSource = _messageSource;
        passwordEncoder = passwordEncoder();
    }
    
    public static String getLang(String key, Object...args) {
        return messageSource.getMessage(key, args, LocaleContextHolder.getLocale());
    }
    
    public static String getErrorLang(String key, Object...args) {
        return getLang(I18N_ERROR_PREFIX + key, args);
    }
    
    public static String getOauthErrorLang(String key, Object...args) {
        return getLang(I18N_OAUTH_ERROR_PREFIX + key,  args);
    }
    
    public static String passwordEncode(String raw) {
        return passwordEncoder.encode(raw);
    }
    
    public static boolean comparePassword(String raw, String encodedPassword) {
        return passwordEncoder.matches(raw, encodedPassword);
    }
}
