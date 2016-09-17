package com.servlet.app.services;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import com.servlet.app.entity.User;

public class UserService {
    private CopyOnWriteArraySet<User> users;


     public UserService(Set<User> users) {
        this.users = new CopyOnWriteArraySet<>(users);
    }

    public Optional<User> getUserByEmail(String email) {
        return users.stream().filter(user -> user.getEmail().equals(email)).findAny();
    }
}
