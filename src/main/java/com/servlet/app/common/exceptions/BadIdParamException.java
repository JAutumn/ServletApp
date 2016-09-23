package com.servlet.app.common.exceptions;

public class BadIdParamException extends AppExceptions {

    public BadIdParamException(String param) {
        super(String.format("Bad id param: %s", param));
    }
}
