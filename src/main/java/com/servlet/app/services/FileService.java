package com.servlet.app.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.servlet.app.entity.User;
import com.servlet.app.utils.Validate;

public class FileService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileService.class);

    public static Set<User> getUsersFromFile(String filePath, String delimiter) {
        Path path = Paths.get(filePath);
        Validate.exist(path, "file should exists");
        Set<User> users = new HashSet<>();
        LOGGER.info("Read users file");
        try (BufferedReader bufferedReader = Files.newBufferedReader(path)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, delimiter);
                User user = new User();
                user.setName(tokenizer.nextToken())
                    .setEmail(tokenizer.nextToken())
                    .setPassword(tokenizer.nextToken())
                    .setRole(tokenizer.nextToken());
                users.add(user);
            }
        } catch (IOException ex) {
            new RuntimeException("Can't read users file", ex);
        }
        return users;
    }
}
