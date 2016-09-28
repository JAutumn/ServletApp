package com.servlet.app.common.exceptions;

public class AppRuntimeException extends RuntimeException {

    public AppRuntimeException(String msg) {
        super(msg);
    }

    public AppRuntimeException(Throwable cause) {
        super(cause);
    }
}
