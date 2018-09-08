/**
 * Copyright (c) 2018 BNDY-NET. All Rights Reserved.
 * http://bndy.net
 */
package net.bndy.sc.service.sso.exception;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * @author Bendy Zhang
 * @version 1.0
 */
public class ApplicationException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    
    private final ErrorCode code;
    private final Object[] args;
    
    private MessageSource messageSource;
    
    public ApplicationException(MessageSource messageSource, ErrorCode code, Object... args) {
        super();
        this.messageSource = messageSource;
        this.code = code;
        this.args = args;
    }

    public ErrorCode getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return this.messageSource.getMessage("error." + this.code.getCode(), this.args, LocaleContextHolder.getLocale());
    }

}
