package org.ko.prototype.core.handler;

import org.ko.prototype.core.exception.AppException;
import org.ko.prototype.core.type.AppCode;
import org.ko.prototype.core.view.View;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public View defaultErrorHandler (Exception e) {
        View view = new View(AppCode.ERROR);
        view.setMassage(e.getMessage());
        return view;
    }

    @ExceptionHandler(value = AppException.class)
    @ResponseBody
    public View apiErrorHandler (AppException e) {
        View view = new View();
        view.setCode(e.getCode());
        view.setMassage(e.getMessage());
        return view;
    }
}
