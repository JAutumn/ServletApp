package com.servlet.app.core.validation;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface ModelValidator {

    Map<String, Object> validate(HttpServletRequest request);
}
