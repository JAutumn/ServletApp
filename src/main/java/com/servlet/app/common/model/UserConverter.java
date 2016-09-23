package com.servlet.app.common.model;

import javax.servlet.http.HttpServletRequest;

import com.servlet.app.core.converters.ModelConverter;

public class UserConverter implements ModelConverter<User> {
    @Override
    public User convert(HttpServletRequest request) {
        User user = new User();
        user.setName(request.getParameter("name"));
        user.setPassword(request.getParameter("password"));
        user.setEmail(request.getParameter("email"));
        user.setRole(request.getParameter("role"));
        return user;
    }
}
