package org.ko.analysis.core.exception;

/**
 * CustomRuntimeException
 */
public class CustomRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 5287205175840676327L;

    public CustomRuntimeException() {
        super();
    }

    public CustomRuntimeException(Throwable cause) {

        super(cause);
    }

    public CustomRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomRuntimeException(String message) {
        super(message);
    }
}
