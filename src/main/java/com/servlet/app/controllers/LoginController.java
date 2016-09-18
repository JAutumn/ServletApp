package com.servlet.app.controllers;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class LoginController extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write(getResponse(req));
    }

    private String getResponse(HttpServletRequest req) {
        Enumeration<String> headerNames = req.getHeaderNames();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<html><body>");
        while(headerNames.hasMoreElements()) {
            String header = headerNames.nextElement();
            stringBuilder.append("<div>")
                         .append(header)
                         .append(" : ")
                         .append(req.getHeader(header))
                         .append("</div>");
        }
        stringBuilder.append("</body><html>");
        return stringBuilder.toString();
    }
}
