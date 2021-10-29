package com.zhongshi.joey.exception;

/**
 * joey异常
 *
 * @author ycc
 */
public class JoeyException extends RuntimeException {
    private static final long serialVersionUID = -2213960748124606155L;

    public JoeyException() {
        super();
    }

    public JoeyException(String message) {
        super(message);
    }

    public JoeyException(String message, Throwable cause) {
        super(message, cause);
    }

    public JoeyException(Throwable cause) {
        super(cause);
    }

    protected JoeyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
