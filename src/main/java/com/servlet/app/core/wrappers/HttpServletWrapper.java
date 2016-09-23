package com.servlet.app.core.wrappers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.servlet.app.core.validation.ModelValidator;
import com.servlet.app.core.model.Model;
import com.servlet.app.core.converters.ModelConverter;

public abstract class HttpServletWrapper<T extends Model> extends HttpServlet {
    private final boolean extractId;
    private final Class<T> type;
    private final ModelValidator modelValidator;
    private final ModelConverter<T> modelConverter;


    public HttpServletWrapper(Class<T> type, ModelValidator modelValidator, ModelConverter<T> modelConverter, boolean extractId) {
        this.type = type;
        this.extractId = extractId;
        this.modelValidator = modelValidator;
        this.modelConverter = modelConverter;
    }

    protected abstract void processGet(ModelAwareHttpServletRequest<T> req, HttpServletResponse resp) throws ServletException, IOException ;

    protected abstract void processPost(ModelAwareHttpServletRequest<T> req, HttpServletResponse resp) throws ServletException, IOException ;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ModelAwareHttpServletRequest<T> modelAwareRequest = new ModelAwareHttpServletRequest<>(type, req, modelValidator, modelConverter, extractId);
        processGet(modelAwareRequest, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ModelAwareHttpServletRequest<T> modelAwareRequest = new ModelAwareHttpServletRequest<>(type, req, modelValidator, modelConverter, extractId);
        processPost(modelAwareRequest, resp);
    }
}
