package com.servlet.app.common.exceptions;

public class NoUserWithSuchIdException extends AppRuntimeException {

    public NoUserWithSuchIdException(String param) {
        super(String.format("No user with such id: %s", param));
    }
}
