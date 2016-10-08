package com.servlet.app.common.controllers.users;

import static com.servlet.app.common.AppStarter.getJspDir;
import static com.servlet.app.common.AppStarter.getPublicPath;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.servlet.app.common.exceptions.UserUpdatingException;
import com.servlet.app.common.model.UserConverter;
import com.servlet.app.common.model.UserValidator;
import com.servlet.app.common.services.UserService;
import com.servlet.app.core.wrappers.ModelAwareHttpServletWrapper;
import com.servlet.app.common.model.User;
import com.servlet.app.common.exceptions.NoUserWithSuchIdException;
import com.servlet.app.core.wrappers.ModelAwareHttpServletRequest;

@WebServlet("/pages/users/edit/*")
@MultipartConfig
public class UsersEditController extends ModelAwareHttpServletWrapper<User> {
    private UserService userService;

    public UsersEditController() {
        super(User.class, new UserValidator(), new UserConverter(), true);
    }

    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    @Override
    protected void processModelAwareGet(ModelAwareHttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = req.getIdFromRequest();
        Optional<User> userOptional = userService.getById(userId);
        if (userOptional.isPresent()) {
            req.setAttribute("targetUser", userOptional.get());
            getServletContext().getRequestDispatcher(getJspDir() + "/users/edit.jsp").forward(req, resp);
        } else {
            throw new NoUserWithSuchIdException(String.valueOf(userId));
        }
    }

    @Override
    protected void processModelAwarePost(ModelAwareHttpServletRequest<User> req, HttpServletResponse resp) throws ServletException, IOException {
        User requestUser = req.getModel();
        HttpSession session = req.getSession(false);
        if (requestUser.isValid()) {
            Long userId = req.getIdFromRequest();
            Optional<User> userOptional = userService.getById(userId);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                requestUser.setId(user.getId());
                try {
                    userService.update(user, req.getPart("avatar"));
                    resp.sendRedirect(getPublicPath() + "/users/");
                } catch (UserUpdatingException e) {
                    session.setAttribute("editingErr", e.getMessage());
                    resp.sendRedirect(getPublicPath() + "/users/add");
                }
            } else {
                throw new NoUserWithSuchIdException(String.valueOf(userId));
            }
        } else {
            req.getMessages().forEach((key, msg) -> session.setAttribute(key, msg));
            resp.sendRedirect(getPublicPath() + "/users/add");
        }
    }
}
