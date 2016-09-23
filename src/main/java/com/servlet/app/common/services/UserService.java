package com.servlet.app.common.services;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import com.servlet.app.common.model.User;
import com.servlet.app.core.utils.RandomUtils;

public class UserService {
    private CopyOnWriteArraySet<User> users;


    public UserService(Set<User> users) {
        this.users = new CopyOnWriteArraySet<>(users);
    }

    public Set<User> getAll() {
        return users;
    }

    public Optional<User> getByEmail(String email) {
        return users.stream().filter(user -> user.getEmail().equals(email)).findAny();
    }

    public List<User> getAllExcept(User currentUser) {
        return users.stream().filter(user -> !user.equals(currentUser)).sorted().collect(toList());
    }

    public Optional<User> getById(Long id) {
        return users.stream().filter(user -> user.getId().equals(id)).findAny();
    }

    public void saveUser(User user) {
        if (users.contains(user)) {
            users.remove(user);
        }
        if (user.getId() == null) {
            Long generatedId = RandomUtils.generateId(users.stream().map(User::getId).collect(toList()));
            user.setId(generatedId);
        }
        users.add(user);
    }

    public void delete(Long id) {
        getById(id).ifPresent(user -> {
            users.remove(user);
        });
    }

    public boolean isExisted(User user) {
        return users.contains(user);
    }

    public boolean isExisted(Long id) {
        return users.stream().map(User::getId).anyMatch(userId -> userId.equals(id));
    }
}
