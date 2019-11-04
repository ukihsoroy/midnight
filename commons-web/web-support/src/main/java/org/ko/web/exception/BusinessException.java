package org.ko.web.exception;

public class BusinessException extends RuntimeException {

    private String code;

    public BusinessException(String code, String msg) {
        super(msg);
        this.code = code;
    }

}
