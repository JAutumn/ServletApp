package com.servlet.app.core.utils;

import java.nio.file.Files;
import java.nio.file.Path;

public class Validate {

    public static void exist(Path path, String msg) {
        if (path == null || !Files.exists(path)) {
            throw new IllegalArgumentException(msg);
        }
    }


    private Validate() {}
}
