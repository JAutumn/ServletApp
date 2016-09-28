package com.servlet.app.core.wrappers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.servlet.app.core.validation.ModelValidator;
import com.servlet.app.core.model.Model;
import com.servlet.app.core.converters.ModelConverter;

public abstract class ModelAwareHttpServletWrapper<T extends Model> extends IdAwareHttpServletWrapper {
    private final boolean extractId;
    private final Class<T> type;
    private final ModelValidator modelValidator;
    private final ModelConverter<T> modelConverter;


    public ModelAwareHttpServletWrapper(Class<T> type, ModelValidator modelValidator, ModelConverter<T> modelConverter, boolean extractId) {
        this.type = type;
        this.extractId = extractId;
        this.modelValidator = modelValidator;
        this.modelConverter = modelConverter;
    }

    protected abstract void processModelAwareGet(ModelAwareHttpServletRequest<T> req, HttpServletResponse resp) throws ServletException, IOException ;

    protected abstract void processModelAwarePost(ModelAwareHttpServletRequest<T> req, HttpServletResponse resp) throws ServletException, IOException ;

    @Override
    protected void processIdAwareGet(IdAwareHttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ModelAwareHttpServletRequest<T> modelAwareRequest = new ModelAwareHttpServletRequest<>(type, req, modelValidator, modelConverter, extractId);
        processModelAwareGet(modelAwareRequest, resp);
    }

    @Override
    protected void processIdAwarePost(IdAwareHttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ModelAwareHttpServletRequest<T> modelAwareRequest = new ModelAwareHttpServletRequest<>(type, req, modelValidator, modelConverter, extractId);
        processModelAwarePost(modelAwareRequest, resp);
    }
}
