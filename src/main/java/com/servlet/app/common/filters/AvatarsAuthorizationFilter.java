package com.servlet.app.common.filters;


import static com.servlet.app.common.AppStarter.getPublicPath;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.servlet.app.common.model.Role;
import com.servlet.app.common.model.User;
import com.servlet.app.core.wrappers.IdAwareHttpFilter;
import com.servlet.app.core.wrappers.IdAwareHttpServletRequest;

@WebFilter("/pages/avatars/*")
public class AvatarsAuthorizationFilter extends IdAwareHttpFilter {

    @Override
    public void doIdAwareHttpFilter(IdAwareHttpServletRequest req, HttpServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        Long userId = req.getIdFromRequest();
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");
        if (Role.ADMIN == user.getRole()) {
            filterChain.doFilter(req, resp);
        } else if (userId.equals(userId)) {
            filterChain.doFilter(req, resp);
        } else {
            resp.sendRedirect(getPublicPath() + "/home");
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
