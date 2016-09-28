package com.servlet.app.core.wrappers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.servlet.app.common.exceptions.BadIdParamException;

public class IdAwareHttpServletRequest extends HttpServletRequestWrapper {
    private static final Logger LOGGER = LoggerFactory.getLogger(ModelAwareHttpServletRequest.class);
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
