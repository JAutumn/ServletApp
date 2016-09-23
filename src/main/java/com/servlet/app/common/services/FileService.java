package com.servlet.app.common.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.servlet.app.common.model.User;
import com.servlet.app.core.utils.Validate;

public class FileService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileService.class);

    public static Set<User> getUsersFromFile(String filePath, String delimiter) {
        Path path = Paths.get(filePath);
        Validate.exist(path, "file should exists");
        Set<User> users = new HashSet<>();
        LOGGER.info("Read users file");
        try (BufferedReader bufferedReader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, delimiter);
                User user = new User();
                user.setId(tokenizer.nextToken())
                    .setName(tokenizer.nextToken())
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

    public static void writeUsersToFile(String filePath, String delimiter, Set<User> users) {
        Path path = Paths.get(filePath);
        Validate.exist(path, "file should exists");
        LOGGER.info("Write users to file");
        try (Writer bufferedWriter = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            for (User user : users) {
                bufferedWriter.write(convertToLine(user, delimiter));
            }
        } catch (IOException ex) {
            LOGGER.error("Users not save");
        }
    }

    private static String convertToLine(User user, String delimiter) {
        StringBuilder builder = new StringBuilder();
        builder.append(user.getId())
               .append(delimiter)
               .append(user.getName())
               .append(delimiter)
               .append(user.getEmail())
               .append(delimiter)
               .append(user.getPassword())
               .append(delimiter)
               .append(user.getRole())
               .append(System.lineSeparator());
        return builder.toString();
    }
}
