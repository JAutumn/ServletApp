package com.servlet.app.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import com.servlet.app.entity.User;
import com.servlet.app.utils.Validate;

public class FileService {

    public static Set<User> getUsersFromFile(String filePath) {
        Path path = Paths.get(filePath);
        Validate.exist(path, "file should exists");
        Set<User> users = new HashSet<>();
        try (BufferedReader bufferedReader = Files.newBufferedReader(path)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, "|");
                User user = new User();
                user.setEmail(tokenizer.nextToken());
                user.setPassword(tokenizer.nextToken());
                users.add(user);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return users;
    }
}
