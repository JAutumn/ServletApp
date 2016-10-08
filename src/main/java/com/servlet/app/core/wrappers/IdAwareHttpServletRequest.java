package com.servlet.app.core.wrappers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.servlet.app.common.exceptions.BadIdParamException;

public class IdAwareHttpServletRequest extends HttpServletRequestWrapper {
    private final HttpServletRequest request;

    public IdAwareHttpServletRequest(HttpServletRequest request) {
        super(request);
        this.request = request;
    }

    public Long getIdFromRequest() {
        String pathInfo = request.getPathInfo();
        String idAsString = pathInfo.replace("/", "");
        try {
            return Long.parseLong(idAsString);

        } catch (NumberFormatException e) {
            throw new BadIdParamException(idAsString);
        }
    }
}
