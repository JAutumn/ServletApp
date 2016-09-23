package com.servlet.app.common.filters;

import static com.servlet.app.common.AppStarter.getPublicPath;
import static com.servlet.app.common.AppStarter.getResourcePath;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class AppFilter extends HttpFilter {

    @Override
    public void doHttpFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        String requestURI = req.getRequestURI();
        if (requestURI.startsWith(getPublicPath()) || requestURI.startsWith(getResourcePath())) {
            filterChain.doFilter(req, resp);
        } else {
            resp.sendRedirect(getPublicPath() + "/home");
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //nothing to do
    }

    @Override
    public void destroy() {
        //nothing to do
    }
}
