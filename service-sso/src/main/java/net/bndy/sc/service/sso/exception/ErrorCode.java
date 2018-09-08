/**
 * Copyright (c) 2018 BNDY-NET. All Rights Reserved.
 * http://bndy.net
 */
package net.bndy.sc.service.sso.exception;

/**
 * @author Bendy Zhang
 * @version 1.0
 */
public enum ErrorCode {
    
    USER_EXISTED_USERNAME(40001),
    ;
    
    private final int code;

    public int getCode() {
        return code;
    }

    ErrorCode(int code) {
        this.code = code;
    }
    
}
