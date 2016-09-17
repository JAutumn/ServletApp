package com.servlet.app.controllers;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.servlet.app.controllers.generic.RestServlet;
import com.servlet.app.entity.User;
import com.servlet.app.exceptions.NoUserWithSuchId;
import com.servlet.app.services.UserService;

@WebServlet("/users/*")
public class UsersController extends RestServlet<User> {
    private UserService userService;

    public UsersController() {
        super(User.class, Collections.singletonList("id"));
    }

    @Override
    public void init() throws ServletException {
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void processGet(HttpServletRequest req, HttpServletResponse resp, Long id) throws ServletException, IOException {
        Optional<User> userOptional = userService.getById(id);
        if (userOptional.isPresent()) {
            req.setAttribute("targetUser", userOptional.get());
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/users/edit.jsp").forward(req, resp);
        } else {
            throw new NoUserWithSuchId(String.valueOf(id));
        }
    }

    @Override
    protected void processPost(HttpServletRequest req, HttpServletResponse resp, Long id, User requestUser) throws ServletException, IOException {
        Optional<User> userOptional = userService.getById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            requestUser.setId(user.getId());
            userService.saveUser(requestUser);
            resp.sendRedirect(req.getContextPath() + "/home");
        } else {
            throw new NoUserWithSuchId(String.valueOf(id));
        }
    }
}
