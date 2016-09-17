package com.servlet.app.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.servlet.app.entity.User;
import com.servlet.app.services.UserService;

@WebServlet("/home")
public class HomeController extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession(false).getAttribute("user");
        req.setAttribute("users", userService.getAllExcept(user));
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(req, resp);
    }
}
