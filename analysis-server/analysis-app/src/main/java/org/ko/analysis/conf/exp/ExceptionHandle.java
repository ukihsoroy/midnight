package org.ko.analysis.conf.exp;

import org.ko.analysis.conf.api.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionHandle extends ResponseEntityExceptionHandler {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(ValidateException.class)
    public Response validateException(ValidateException ve, WebRequest webRequest) {
        return Response.of(false, ve.getCode(), ve.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public Response generalException(BusinessException ge, WebRequest request) {
        return Response.of(false, ge.getCode(), ge.getMessage());
    }

}
