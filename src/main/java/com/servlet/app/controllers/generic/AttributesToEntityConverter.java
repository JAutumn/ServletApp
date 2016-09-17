package com.servlet.app.controllers.generic;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

class AttributesToEntityConverter {

    public <T> T convert(HttpServletRequest req, Class<T> type, List<String> exceptedFieldsNames) {
        Field[] fields = type.getDeclaredFields();
        Method[] methods = type.getMethods();
        List<Method> methodList = Arrays.asList(methods)
                                        .stream()
                                        .filter(method -> method.getName().toLowerCase().contains("set"))
                                        .collect(Collectors.toList());
        try {
            T instance = type.newInstance();
            for (Field field : fields) {
                if (!exceptedFieldsNames.contains(field.getName())) {
                    String attribute = req.getParameter(field.getName());
                    if (attribute == null) {
                        throw new RuntimeException("Cannot convert parameters to entity");
                    }
                    for (Method method : methodList) {
                        if (method.getName().toLowerCase().contains(field.getName().toLowerCase())) {
                            method.invoke(instance, attribute);
                        }
                    }
                }
            }
            return instance;
        } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
            throw new RuntimeException(e);
        }
    }
}
