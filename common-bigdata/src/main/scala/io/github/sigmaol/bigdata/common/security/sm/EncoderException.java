package io.github.sigmaol.bigdata.common.security.sm;


public class EncoderException extends IllegalStateException {
    private Throwable cause;

    EncoderException(String msg, Throwable cause) {
        super(msg);
        this.cause = cause;
    }

    @Override
    public Throwable getCause() {
        return this.cause;
    }
}
