package org.ko.analysis.conf.exp;

public class UniqueException extends BusinessException {
    public UniqueException(String message) {
        super(message);
        super.setCode(503);
    }
}
