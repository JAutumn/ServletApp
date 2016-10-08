package com.servlet.app.core;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum SortTypes {
    ASC("asc"),
    DESC("desc");

    private final String value;

    SortTypes(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static List<String> valuesAsStringList() {
        return Arrays.stream(values())
                     .map(SortTypes::getValue)
                     .collect(Collectors.toList());
    }
}
