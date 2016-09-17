package com.servlet.app.controllers.generic;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.servlet.app.exceptions.BadIdParam;

public abstract class RestServlet<T> extends HttpServlet {
    private final Class<T> type;
    private final List<String> exceptedFields;
    private final AttributesToEntityConverter entityConverter;

    public RestServlet(Class<T> type, List<String> exceptedFields) {
        this.type = type;
        this.exceptedFields = exceptedFields;
        this.entityConverter = new AttributesToEntityConverter();
    }

    protected abstract void processGet(HttpServletRequest req, HttpServletResponse resp, Long id) throws ServletException, IOException ;

    protected abstract void processPost(HttpServletRequest req, HttpServletResponse resp, Long id, T requestEntity) throws ServletException, IOException ;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processGet(req, resp, getIdFromPath(req));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processPost(req, resp, getIdFromPath(req), getEntityFromRequest(req));
    }

    private Long getIdFromPath(HttpServletRequest req) {
        String pathInfo = req.getPathInfo();
        String idAsString = pathInfo.replace("/", "");
        try {
            return Long.parseLong(idAsString);

        } catch (NumberFormatException e) {
            throw new BadIdParam(idAsString);
        }
    }

    private T getEntityFromRequest(HttpServletRequest req) {
        return entityConverter.convert(req, type, exceptedFields);
    }
}
