package org.ko.analysis.core.exception;

import org.springframework.security.core.AuthenticationException;

public class SigmaAuthenticationException extends AuthenticationException {
    public SigmaAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }

    public SigmaAuthenticationException(String message) {
        super(message);
    }
}
