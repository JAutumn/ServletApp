package com.servlet.app.common.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.servlet.app.common.db.ConnectionProvider;
import com.servlet.app.common.model.User;

public class UserService {
    private static final String FIND_ALL = "SELECT * FROM users";
    private static final String FIND_BY_EMAIL = "SELECT * FROM users WHERE email=?";
    private static final String FIND_BY_ID = "SELECT * FROM users WHERE id=?";
    private static final String FIND_ALL_EXCEPT_ONE = "SELECT * FROM users WHERE id<>? ORDER BY name";

    private static final String INSERT = "INSERT INTO users (name, email, password, role) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE users SET name=?, email=?, password=?, role=? WHERE id=?";
    private static final String DELETE = "DELETE FROM users WHERE id=?";


    private User mapUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"))
            .setName(resultSet.getString("name"))
            .setEmail(resultSet.getString("email"))
            .setPassword(resultSet.getString("password"))
            .setRole(resultSet.getString("role"));
        return user;
    }

    public List<User> getAll() {
        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(mapUser(resultSet));
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<User> getByEmail(String email) {
        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_EMAIL)) {

            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(mapUser(resultSet));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllExcept(User currentUser) {
        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_EXCEPT_ONE)) {

            preparedStatement.setLong(1, currentUser.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(mapUser(resultSet));
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<User> getById(Long id) {
        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(mapUser(resultSet));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createUser(User user) {
        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {

            connection.setAutoCommit(false);

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getRole().toString());

            int i = preparedStatement.executeUpdate();
            if (i == 1) {
                connection.commit();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateUser(User user) {
        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {

            connection.setAutoCommit(false);

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getRole().toString());
            preparedStatement.setLong(5, user.getId());

            int i = preparedStatement.executeUpdate();
            if (i == 1) {
                connection.commit();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Long id) {
        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {

            connection.setAutoCommit(false);

            preparedStatement.setLong(1, id);

            int i = preparedStatement.executeUpdate();
            if (i == 1) {
                connection.commit();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isExisted(String email) {
        return getByEmail(email).isPresent();
    }

    public boolean isExisted(Long id) {
        return getById(id).isPresent();
    }
}
