package org.ko.analysis.conf.exp;

import org.ko.analysis.core.exp.CustomRuntimeException;

import java.util.List;

/**
 * ValidateException
 */
public class ValidateException extends CustomRuntimeException {

    private static final long serialVersionUID = 3196735858881429614L;

    private Integer code;

    /**
     * 验证结果
     */
    private List<String> validateMsg;

    public ValidateException() {
    }

    public ValidateException(String message) {
        super(message);
    }

    public ValidateException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidateException(Throwable cause) {
        super(cause);
    }

    public ValidateException(List<String> msg) {
        this.validateMsg = msg;
    }

    public List<String> getValidateMsg() {
        return this.validateMsg;
    }

    public Integer getCode() {
        return code;
    }
}