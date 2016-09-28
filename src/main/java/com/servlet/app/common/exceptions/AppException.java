package com.servlet.app.common.exceptions;

public class AppException extends Exception {
    public AppException(String msg) {
        super(msg);
    }

    public AppException(Throwable cause) {
        super(cause);
    }

}
