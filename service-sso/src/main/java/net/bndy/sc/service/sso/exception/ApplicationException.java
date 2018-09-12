/**
 * Copyright (c) 2018 BNDY-NET. All Rights Reserved.
 * http://bndy.net
 */
package net.bndy.sc.service.sso.exception;

/**
 * @author Bendy Zhang
 * @version 1.0
 */
public class ApplicationException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    
    private final ErrorCode code;
    private final Object[] args;
    
    
    public Object[] getArgs() {
        return this.args;
    }

    public ApplicationException(ErrorCode code, Object... args) {
        super();
        this.code = code;
        this.args = args;
    }

    public ErrorCode getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return String.valueOf(this.getCode().getCode());
    }
    
}
