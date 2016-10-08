package com.servlet.app.core;

public enum SortParameters {
        SORT("sort"), TYPE("type");

        private final String value;

        SortParameters(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
}