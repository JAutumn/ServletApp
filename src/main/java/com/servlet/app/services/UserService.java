package com.servlet.app.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

import com.servlet.app.entity.User;

public class UserService {
    private CopyOnWriteArraySet<User> users;


    public UserService(Set<User> users) {
        this.users = new CopyOnWriteArraySet<>(users);
    }

    public Optional<User> getByEmail(String email) {
        return users.stream().filter(user -> user.getEmail().equals(email)).findAny();
    }

    public List<User> getAllExcept(User currentUser) {
        return users.stream().filter(user -> !user.equals(currentUser)).sorted().collect(Collectors.toList());
    }
}
