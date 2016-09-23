package com.servlet.app.common.model;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.servlet.app.core.validation.ModelValidator;

public class UserValidator implements ModelValidator {
    private static final String EMPTY_MESSAGE = "%s cannot be empty";

    @Override
    public Map<String, Object> validate(HttpServletRequest request) {
        Map<String, Object> messages = new HashMap<>();
        if (StringUtils.isBlank(request.getParameter("name"))) {
            messages.put("nameErr", String.format(EMPTY_MESSAGE, "Name"));
        }
        if (StringUtils.isBlank(request.getParameter("email"))) {
            messages.put("emailErr", String.format(EMPTY_MESSAGE, "Email"));
        }
        if (StringUtils.isBlank(request.getParameter("password"))) {
            messages.put("passwordErr", String.format(EMPTY_MESSAGE, "Password"));
        }
        if (StringUtils.isBlank(request.getParameter("role"))) {
            messages.put("roleErr", String.format(EMPTY_MESSAGE, "Role"));
        }
        return messages;
    }
}
