package com.servlet.app.common.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import javax.servlet.http.Part;

import com.servlet.app.common.db.ConnectionProvider;

public class AvatarDAO {
    private static final String FIND_DATA_BY_ID = "SELECT data FROM avatars WHERE user_id = ?";
    private static final String INSERT = "INSERT INTO avatars (data, user_id) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE avatars SET data = ? WHERE user_id = ?";
    private static final String DELETE = "DELETE FROM avatars WHERE user_id =?";

    public Optional<InputStream> findData(Long userId) throws SQLException {
        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_DATA_BY_ID)) {

            preparedStatement.setLong(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(resultSet.getBlob("data").getBinaryStream());
            }
            return Optional.empty();
        }
    }

    public Long create(Part part, Long userId, Connection connection) throws IOException, SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setBlob(1, part.getInputStream(), part.getSize());
            preparedStatement.setLong(2, userId);

            int affectedRowsCount = preparedStatement.executeUpdate();
            if (affectedRowsCount == 0) {
                return 0L;
            }
            if (affectedRowsCount > 1) {
                return 0L;
            }
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getLong(1);
            }
            return 0L;
        }
    }

    public Long update(Part part, Long userId, Connection connection) throws IOException, SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setBlob(1, part.getInputStream(), part.getSize());
            preparedStatement.setLong(2, userId);
            return Long.valueOf(preparedStatement.executeUpdate());
        }
    }

    public Long delete(Long userId, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setLong(1, userId);
            return Long.valueOf(preparedStatement.executeUpdate());
        }
    }
}
