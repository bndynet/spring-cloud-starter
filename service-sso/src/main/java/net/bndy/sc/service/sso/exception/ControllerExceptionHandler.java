/**
 * Copyright (c) 2018 BNDY-NET. All Rights Reserved.
 * http://bndy.net
 */
package net.bndy.sc.service.sso.exception;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import net.bndy.sc.service.sso.ApplicationUtil;

/**
 * @author Bendy Zhang
 * @version 1.0
 */
@ControllerAdvice(annotations = { Controller.class })
public class ControllerExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public static final String DEFAULT_ERROR_VIEW = "error";
    
    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) {

        this.logger.error("{} for {}", e.getMessage(), req.getRequestURL());

        ModelAndView mView = new ModelAndView();
        mView.addObject("path", req.getRequestURL());
        mView.addObject("exception", e);
        mView.addObject("trace", e.getStackTrace());
        mView.addObject("url", req.getRequestURL());
        mView.addObject("timestamp",  Calendar.getInstance().getTime());
        mView.addObject("error", ApplicationUtil.getErrorLang("title"));

        if (e instanceof ApplicationException) {
            ApplicationException applicationException = (ApplicationException)e;
            mView.addObject("message",  ApplicationUtil.getErrorLang(String.valueOf(applicationException.getCode().getCode()), applicationException.getArgs()));
        } else {
            mView.addObject("message",  e.getMessage());
        }

        mView.setViewName(DEFAULT_ERROR_VIEW);

        return mView;
    }
}