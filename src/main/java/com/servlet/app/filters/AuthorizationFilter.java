package com.servlet.app.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.servlet.app.entity.Role;
import com.servlet.app.entity.User;

@WebFilter("/users/")
public class AuthorizationFilter extends HttpFilter {

    @Override
    public void doHttpFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        User user = (User) req.getSession(false).getAttribute("user");
        if (Role.ADMIN.equals(user.getRole())) {
            filterChain.doFilter(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/home");
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
