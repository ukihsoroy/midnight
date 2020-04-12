package org.ko.analysis.core.exception;

public class UniqueException extends BusinessException {
    public UniqueException(String message) {
        super(message);
        super.setCode(503);
    }
}
