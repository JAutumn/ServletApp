package com.servlet.app.core.wrappers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.servlet.app.core.validation.ModelValidator;
import com.servlet.app.core.model.Model;
import com.servlet.app.core.converters.ModelConverter;

public class ModelAwareHttpServletRequest<T extends Model> extends IdAwareHttpServletRequest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ModelAwareHttpServletRequest.class);
    private final Map<String, Object> messages;
    private final HttpServletRequest request;
    private final T model;
    private final boolean extractId;

    public ModelAwareHttpServletRequest(Class<T> type, HttpServletRequest request, ModelValidator modelValidator, ModelConverter<T> modelConverter, boolean extractId) {
        super(request);
        this.request = request;
        this.extractId = extractId;
        messages = modelValidator.validate(request);
        if (messages.isEmpty()) {
            model = modelConverter.convert(request);
            model.setValid(true);
        } else {
            try {
                model = type.newInstance();
                model.setValid(false);
            } catch (InstantiationException | IllegalAccessException e) {
                LOGGER.error(String.format("can't instantiate %s", type.getClass().getName()));
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Long getIdFromRequest() {
        if (extractId) {
           return super.getIdFromRequest();
        } else {
            throw new IllegalArgumentException("extract id set to false");
        }
    }

    public T getModel() {
        return model;
    }

    public Map<String, Object> getMessages() {
        return messages;
    }

}
