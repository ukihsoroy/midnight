package org.ko.prototype.core.exception;


import org.ko.prototype.core.type.AppCode;

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
