package com.servlet.app.common.controllers.users;


import static com.servlet.app.common.AppStarter.getPublicPath;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;

import com.servlet.app.common.exceptions.NoUserWithSuchIdException;
import com.servlet.app.common.model.User;
import com.servlet.app.common.model.UserConverter;
import com.servlet.app.common.model.UserValidator;
import com.servlet.app.common.services.UserService;
import com.servlet.app.core.wrappers.HttpServletWrapper;
import com.servlet.app.core.wrappers.ModelAwareHttpServletRequest;

@WebServlet("/pages/users/delete/*")
public class UsersDeleteController extends HttpServletWrapper<User> {
    private UserService userService;

    public UsersDeleteController() {
        super(User.class, new UserValidator(), new UserConverter(), true);
    }

    @Override
    public void init() throws ServletException {
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void processGet(ModelAwareHttpServletRequest<User> req, HttpServletResponse resp) throws ServletException, IOException {
        throw new UnsupportedOperationException(String.format("GET method unsupported in %s", getClass().getName()));
    }

    @Override
    protected void processPost(ModelAwareHttpServletRequest<User> req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = req.getIdFromRequest();
        if (userService.isExisted(userId)) {
            userService.delete(userId);
            resp.sendRedirect(getPublicPath() + "/users/");
        } else {
            throw new NoUserWithSuchIdException(String.valueOf(userId));
        }
    }
}
