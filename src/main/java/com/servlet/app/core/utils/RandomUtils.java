package com.servlet.app.core.utils;

import java.util.List;

public class RandomUtils {

    public static Long generateId(List<Long> excludeIds) {
        long id = org.apache.commons.lang3.RandomUtils.nextLong(1L, 10000L);
        while(excludeIds.contains(id)) {
            id = org.apache.commons.lang3.RandomUtils.nextLong(1L, 10000L);
        }
        return id;
    }

    private RandomUtils() {}
}
