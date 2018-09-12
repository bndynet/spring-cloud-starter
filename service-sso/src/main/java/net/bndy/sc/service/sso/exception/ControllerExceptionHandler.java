/**
 * Copyright (c) 2018 BNDY-NET. All Rights Reserved.
 * http://bndy.net
 */
package net.bndy.sc.service.sso.exception;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Bendy Zhang
 * @version 1.0
 */
@ControllerAdvice(annotations = { Controller.class })
public class ControllerExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public static final String DEFAULT_ERROR_VIEW = "error";
    
    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(value = ApplicationException.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, ApplicationException e) {

        this.logger.error("{} for {}", e.getMessage(), req.getRequestURL());

        ModelAndView mView = new ModelAndView();
        mView.addObject("status", HttpStatus.SC_BAD_REQUEST);
        mView.addObject("error", this.messageSource.getMessage("error.title", null, LocaleContextHolder.getLocale()));
        mView.addObject("timestamp",  Calendar.getInstance().getTime());
        mView.addObject("message",  this.messageSource.getMessage("error." + e.getCode().getCode(), e.getArgs(), LocaleContextHolder.getLocale()));
        mView.addObject("path", req.getRequestURL());
        mView.addObject("exception", e);
        mView.addObject("trace", e.getStackTrace());
        mView.addObject("url", req.getRequestURL());
        mView.setViewName(DEFAULT_ERROR_VIEW);

        return mView;
    }
}