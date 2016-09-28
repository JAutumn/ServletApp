package com.servlet.app.core.wrappers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class IdAwareHttpServletWrapper extends HttpServlet {

    protected abstract void processIdAwareGet(IdAwareHttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException ;

    protected abstract void processIdAwarePost(IdAwareHttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException ;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IdAwareHttpServletRequest idAwareRequest = new IdAwareHttpServletRequest(req);
        processIdAwareGet(idAwareRequest, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IdAwareHttpServletRequest idAwareRequest = new IdAwareHttpServletRequest(req);
        processIdAwarePost(idAwareRequest, resp);
    }
}
