package com.servlet.app.core.converters;

import javax.servlet.http.HttpServletRequest;

import com.servlet.app.core.model.Model;

public interface ModelConverter<T extends Model> {

    T convert(HttpServletRequest request);
}
