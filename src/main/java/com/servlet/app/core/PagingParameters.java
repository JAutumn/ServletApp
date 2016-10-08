package com.servlet.app.core;

public enum PagingParameters {
    PAGE_NUMBER("page"),
    LIMIT("limit");

    private final String value;

    PagingParameters(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
