package com.servlet.app.common.exceptions;

public class UserUpdatingException extends AppException {

    public UserUpdatingException(String msg) {
        super(msg);
    }

    public UserUpdatingException(Throwable cause) {
        super(cause);
    }
}
