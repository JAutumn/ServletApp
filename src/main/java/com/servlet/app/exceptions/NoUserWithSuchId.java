package com.servlet.app.exceptions;

public class NoUserWithSuchId extends AppExceptions {

    public NoUserWithSuchId(String param) {
        super(String.format("No user with such id: %s", param));
    }
}
