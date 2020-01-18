package io.github.sigmaol.bigdata.common.security.sm;


public class DecoderException extends IllegalStateException {
    private Throwable cause;

    DecoderException(String msg, Throwable cause) {
        super(msg);
        this.cause = cause;
    }

    @Override
    public Throwable getCause() {
        return this.cause;
    }
}
