package com.servlet.app.common.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.servlet.app.common.db.ConnectionProvider;
import com.servlet.app.common.model.User;

public class UserDAO {
    private static final String COUNT_ALL = "SELECT count(*) FROM users";

    private static final String FIND_BY_EMAIL = "SELECT * FROM users WHERE email=?";
    private static final String FIND_BY_ID = "SELECT * FROM users WHERE id=?";
    private static final String FIND_ALL_EXCEPT_ONE = "SELECT * FROM users WHERE id<>? ORDER BY %s %s LIMIT %d OFFSET %d";

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

    public Optional<User> getByEmail(String email) {
        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_EMAIL)) {

            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapUser(resultSet));
                } else {
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllExceptOne(User currentUser, long offset, int limit, String sortField, String sortType) {
        String query = String.format(FIND_ALL_EXCEPT_ONE, sortField, sortType, limit, offset);
        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, currentUser.getId());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<User> users = new ArrayList<>();
                while (resultSet.next()) {
                    users.add(mapUser(resultSet));
                }
                return users;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<User> getById(Long id) {
        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {

            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapUser(resultSet));
                } else {
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public long countAll() {
        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(COUNT_ALL)) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    long count = resultSet.getLong(1);
                    return count;
                }
                return 0L;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getColumnsNames() {
        try (Connection connection = ConnectionProvider.getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet columns = metaData.getColumns(null, "public", "users", "");
            List<String> columnsNames = new ArrayList<>();
            while (columns.next()) {
                columnsNames.add(columns.getString("COLUMN_NAME"));
            }
            return columnsNames;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Long create(User user, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getRole().toString());

            int affectedRowsCount = preparedStatement.executeUpdate();
            if (affectedRowsCount == 0) {
                return 0L;
            }
            if (affectedRowsCount > 1) {
                return 0L;
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    connection.commit();
                    return generatedKeys.getLong(1);
                }
                return 0L;
            }
        }
    }

    public Long update(User user, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getRole().toString());
            preparedStatement.setLong(5, user.getId());

            return Long.valueOf(preparedStatement.executeUpdate());
        }
    }

    public Long delete(Long id, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setLong(1, id);
            return Long.valueOf(preparedStatement.executeUpdate());
        }
    }

    public boolean isExisted(String email) {
        return getByEmail(email).isPresent();
    }

    public boolean isExisted(Long id) {
        return getById(id).isPresent();
    }
}
