package com.servlet.app.filters;

import static com.servlet.app.AppStarter.getPublicPath;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.servlet.app.AppStarter;

@WebFilter("/pages/*" )
public class AuthenticationFilter extends HttpFilter {

    @Override
    public void doHttpFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        String requestURI = req.getRequestURI();
        HttpSession session = req.getSession(false);
        if (requestURI.endsWith(getPublicPath() + "/login")) {
            if (session != null && session.getAttribute("user") != null) {
                resp.sendRedirect(getPublicPath() + "/home");
            } else {
                filterChain.doFilter(req, resp);
            }
        } else {
            if (session != null && session.getAttribute("user") != null) {
                filterChain.doFilter(req, resp);
            } else {
                resp.sendRedirect(getPublicPath() + "/login");
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
