package com.servlet.app.entity;

import java.util.Objects;

public class User implements Comparable<User>{
    private Long id;
    private String name;
    private String email;
    private String password;
    private Role role;

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public User setId(String id) {
        this.id = Long.valueOf(id);
        return this;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public User setRole(String role) {
        this.role = Role.valueOf(role);
        return this;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public int compareTo(User user) {
        return getName().compareTo(user.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
