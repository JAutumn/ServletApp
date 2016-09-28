package com.servlet.app.common.exceptions;

public class UserCreatingException extends AppException {

    public UserCreatingException(String msg) {
        super(msg);
    }

    public UserCreatingException(Throwable cause) {
        super(cause);
    }
}
