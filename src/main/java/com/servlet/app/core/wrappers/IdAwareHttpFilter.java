package com.servlet.app.core.wrappers;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

abstract public class IdAwareHttpFilter extends HttpFilter {

    public abstract void doIdAwareHttpFilter(IdAwareHttpServletRequest req, HttpServletResponse resp, FilterChain filterChain) throws IOException, ServletException;

    @Override
    public void doHttpFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        IdAwareHttpServletRequest idAwareRequest = new IdAwareHttpServletRequest(req);
        doIdAwareHttpFilter(idAwareRequest, resp, filterChain);
    }
}
