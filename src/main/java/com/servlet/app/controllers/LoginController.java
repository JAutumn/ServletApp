package com.servlet.app.controllers;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.servlet.app.entity.User;
import com.servlet.app.services.UserService;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional.ofNullable(req.getParameter("error")).ifPresent(error -> {
            switch (error) {
            case "pass" :
                req.setAttribute("passError", "Wrong password, please try again");
                break;
            case "email" :
                req.setAttribute("emailError", "Such email is not registered");
                break;
            }
        });
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password =  req.getParameter("password");
        Optional<User> userOptional = userService.getByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getPassword().equals(password)) {
                HttpSession session = req.getSession(true);
                session.setAttribute("user", user);
                resp.sendRedirect("home");
            } else {
                resp.sendRedirect(req.getContextPath() + "/login?error=pass");
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/login?error=email");
        }
    }
}
