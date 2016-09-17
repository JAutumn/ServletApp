package com.servlet.app.exceptions;

public class BadIdParam extends AppExceptions {

    public BadIdParam(String param) {
        super(String.format("Bad id param: %s", param));
    }
}
