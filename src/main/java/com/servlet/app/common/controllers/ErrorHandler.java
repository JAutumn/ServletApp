package com.servlet.app.common.controllers;

import static com.servlet.app.common.AppStarter.getPublicPath;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/pages/errorHandler")
public class ErrorHandler extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandler.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String msg = ((Throwable) req.getAttribute("javax.servlet.error.exception")).getMessage();
        LOGGER.error(msg);
        if (session != null) {
            session.setAttribute("error", msg);
        }
        resp.sendRedirect(getPublicPath() + "/error");
    }
}
