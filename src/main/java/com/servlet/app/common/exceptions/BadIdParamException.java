package com.servlet.app.common.exceptions;

public class BadIdParamException extends AppRuntimeException {

    public BadIdParamException(String param) {
        super(String.format("Bad id param: %s", param));
    }
}
