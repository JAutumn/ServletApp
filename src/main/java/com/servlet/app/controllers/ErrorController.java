package com.servlet.app.controllers;

import static com.servlet.app.AppStarter.getJspDirectory;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.servlet.app.AppStarter;

@WebServlet("/pages/error/")
public class ErrorController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(getJspDirectory() + "/error.jsp").forward(req, resp);
    }
}
