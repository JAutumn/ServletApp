package com.servlet.app.common.controllers;

import static com.servlet.app.common.AppStarter.getJspDir;
import static com.servlet.app.common.AppStarter.getPublicPath;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.servlet.app.common.model.User;
import com.servlet.app.common.services.UserService;

@WebServlet("/pages/login")
public class LoginController extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
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
        getServletContext().getRequestDispatcher(getJspDir() + "/login.jsp").forward(req, resp);
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
                resp.sendRedirect(getPublicPath() + "/home");
            } else {
                resp.sendRedirect(getPublicPath() + "/login?error=pass");
            }
        } else {
            resp.sendRedirect(getPublicPath() + "/login?error=email");
        }
    }
}
