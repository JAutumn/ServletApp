package com.servlet.app.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password =  req.getParameter("password");
        if ("admin@gmail.com".equals(email) && "admin".equals(password)) {
            HttpSession session = req.getSession();
            session.setAttribute("email", email);
            resp.sendRedirect("home");
        } else {
            resp.sendRedirect("error");
        }
    }
}
