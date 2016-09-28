package com.servlet.app.common.exceptions;

public class UserDeletingException extends AppException {

    public UserDeletingException(String msg) {
        super(msg);
    }

    public UserDeletingException(Throwable cause) {
        super(cause);
    }
}
