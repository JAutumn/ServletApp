package com.servlet.app.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*" )
public class AuthenticationFilter extends HttpFilter {

    @Override
    public void doHttpFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = req.getSession(false);
        if (req.getRequestURI().endsWith("/login")) {
            if (session != null && session.getAttribute("user") != null) {
                resp.sendRedirect(req.getContextPath() + "/home");
            } else {
                filterChain.doFilter(req, resp);
            }
        } else {
            if (session != null && session.getAttribute("user") != null) {
                filterChain.doFilter(req, resp);
            } else {
                resp.sendRedirect(req.getContextPath() + "/login");
            }
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
