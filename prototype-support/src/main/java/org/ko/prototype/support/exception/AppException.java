package org.ko.prototype.support.exception;


import org.ko.prototype.support.type.AppCode;

public class AppException extends Exception {

    private String code;

    public AppException(AppCode appCode) {
        super(appCode.getMessage());
        this.code = appCode.getCode();
    }

    public String getCode() {
        return code;
    }
}
