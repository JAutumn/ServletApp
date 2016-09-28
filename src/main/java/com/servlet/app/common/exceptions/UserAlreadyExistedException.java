package com.servlet.app.common.exceptions;

import com.servlet.app.common.model.User;

public class UserAlreadyExistedException extends AppRuntimeException {

    public UserAlreadyExistedException(User user) {
        super(String.format("Such user already existed: %s", user));
    }
}
