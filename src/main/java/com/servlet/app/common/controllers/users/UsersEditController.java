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
import javax.servlet.http.Part;

import com.servlet.app.common.model.UserConverter;
import com.servlet.app.common.model.UserValidator;
import com.servlet.app.core.wrappers.HttpServletWrapper;
import com.servlet.app.common.model.User;
import com.servlet.app.common.exceptions.NoUserWithSuchIdException;
import com.servlet.app.common.services.UserService;
import com.servlet.app.core.wrappers.ModelAwareHttpServletRequest;

@WebServlet("/pages/users/*")
@MultipartConfig
public class UsersEditController extends HttpServletWrapper<User> {
    private UserService userService;

    public UsersEditController() {
        super(User.class, new UserValidator(), new UserConverter(), true);
    }

    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    @Override
    protected void processGet(ModelAwareHttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
    protected void processPost(ModelAwareHttpServletRequest<User> req, HttpServletResponse resp) throws ServletException, IOException {
        User requestUser = req.getModel();
        if (requestUser.isValid()) {
            Long userId = req.getIdFromRequest();
            Optional<User> userOptional = userService.getById(userId);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                requestUser.setId(user.getId());
                Part part = req.getPart("photo");
                part.write(getServletContext().getRealPath("/WEB-INF/") + part.getSubmittedFileName());
                userService.updateUser(requestUser);
                resp.sendRedirect(getPublicPath() + "/users/");
            } else {
                throw new NoUserWithSuchIdException(String.valueOf(userId));
            }
        } else {
            HttpSession session = req.getSession(false);
            req.getMessages().forEach((key, msg) -> session.setAttribute(key, msg));
            resp.sendRedirect(getPublicPath() + "/users/add");
        }
    }

    private void downloadPhoto() {

    }
}
