package com.servlet.app.common.controllers.users;

import static com.servlet.app.common.AppStarter.getJspDir;
import static com.servlet.app.common.AppStarter.getPublicPath;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.servlet.app.common.exceptions.UserCreatingException;
import com.servlet.app.common.model.UserConverter;
import com.servlet.app.common.model.UserValidator;
import com.servlet.app.common.services.UserService;
import com.servlet.app.core.wrappers.ModelAwareHttpServletWrapper;
import com.servlet.app.common.model.User;
import com.servlet.app.common.exceptions.UserAlreadyExistedException;
import com.servlet.app.core.wrappers.ModelAwareHttpServletRequest;

@WebServlet("/pages/users/add")
@MultipartConfig
public class UsersAddController extends ModelAwareHttpServletWrapper<User> {
    private UserService userService;

    public UsersAddController() {
        super(User.class, new UserValidator(), new UserConverter(), false);
    }

    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    @Override
    protected void processModelAwareGet(ModelAwareHttpServletRequest<User> req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(getJspDir() + "/users/add.jsp").forward(req, resp);
    }

    @Override
    protected void processModelAwarePost(ModelAwareHttpServletRequest<User> req, HttpServletResponse resp) throws ServletException, IOException {
        User user = req.getModel();
        HttpSession session = req.getSession(false);
        if (user.isValid()) {
            if (userService.isUserExisted(user.getEmail())) {
                throw new UserAlreadyExistedException(user);
            } else {
                try {
                    userService.create(user, req.getPart("avatar"));
                    resp.sendRedirect(getPublicPath() + "/users/");
                } catch (UserCreatingException e) {
                    session.setAttribute("creatingErr", e.getMessage());
                    resp.sendRedirect(getPublicPath() + "/users/add");
                }
            }
        } else {
            req.getMessages().forEach((key, msg) -> session.setAttribute(key, msg));
            resp.sendRedirect(getPublicPath() + "/users/add");
        }

    }
}
