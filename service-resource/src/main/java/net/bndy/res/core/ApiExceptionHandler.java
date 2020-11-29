package net.bndy.res.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ApiExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError defaultExceptionHandler(Exception ex, HttpServletRequest request) {

        this.logger.error("{} - {} \n{}",
                request.getRequestURI(),
                ex.getMessage(),
                ex.getStackTrace());

        ApiError error = new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage(),
                ex);

        return error;
    }
}
