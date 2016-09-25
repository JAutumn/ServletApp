package com.servlet.app.common.controllers.users;

import static com.servlet.app.common.AppStarter.getJspDir;
import static com.servlet.app.common.AppStarter.getPublicPath;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.servlet.app.common.model.UserConverter;
import com.servlet.app.common.model.UserValidator;
import com.servlet.app.core.wrappers.HttpServletWrapper;
import com.servlet.app.common.model.User;
import com.servlet.app.common.exceptions.UserAlreadyExistedException;
import com.servlet.app.common.services.UserService;
import com.servlet.app.core.wrappers.ModelAwareHttpServletRequest;

@WebServlet("/pages/users/add")
public class UsersAddController extends HttpServletWrapper<User> {
    private UserService userService;

    public UsersAddController() {
        super(User.class, new UserValidator(), new UserConverter(), false);
    }

    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    @Override
    protected void processGet(ModelAwareHttpServletRequest<User> req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(getJspDir() + "/users/add.jsp").forward(req, resp);
    }

    @Override
    protected void processPost(ModelAwareHttpServletRequest<User> req, HttpServletResponse resp) throws ServletException, IOException {
        User user = req.getModel();
        if (user.isValid()) {
            if (userService.isExisted(user.getEmail())) {
                throw new UserAlreadyExistedException(user);
            } else {
                userService.createUser(user);
                resp.sendRedirect(getPublicPath() + "/users/");
            }
        } else {
            HttpSession session = req.getSession(false);
            req.getMessages().forEach((key, msg) -> session.setAttribute(key, msg));
            resp.sendRedirect(getPublicPath() + "/users/add");
        }

    }
}
