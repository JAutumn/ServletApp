package com.servlet.app.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/home")
public class HomeController extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
}
