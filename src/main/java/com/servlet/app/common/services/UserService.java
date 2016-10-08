package com.servlet.app.common.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.Part;

import com.servlet.app.common.dao.AvatarDAO;
import com.servlet.app.common.dao.UserDAO;
import com.servlet.app.common.db.ConnectionProvider;
import com.servlet.app.common.db.DbHelper;
import com.servlet.app.common.exceptions.UserCreatingException;
import com.servlet.app.common.exceptions.UserDeletingException;
import com.servlet.app.common.exceptions.UserUpdatingException;
import com.servlet.app.common.model.User;
import com.servlet.app.core.Paging;

public class UserService {
    private UserDAO userDAO;
    private AvatarDAO avatarDAO;


    public UserService() {
        userDAO = new UserDAO();
        avatarDAO = new AvatarDAO();
    }

    public void create(User user, Part part) throws UserCreatingException {
        Connection connection = null;
        try {
            connection = ConnectionProvider.getConnection();
            connection.setAutoCommit(false);
            
            Long generatedUserId = userDAO.create(user, connection);
            if (generatedUserId == 0) {
                connection.rollback();
                throw new UserCreatingException("Cannot create user");
            }
            if (part.getSize() > 0) {
                Long generatedAvatarId = avatarDAO.create(part, generatedUserId, connection);
                if (generatedAvatarId == 0) {
                    connection.rollback();
                    throw new UserCreatingException("Cannot create avatar for user");
                }
            }
            connection.commit();
        } catch (IOException | SQLException e) {
            DbHelper.closeConnection(connection);
            throw new UserCreatingException(e);
        }
    }

    public void update(User user, Part part) throws UserUpdatingException {
        Connection connection = null;
        try {
            connection = ConnectionProvider.getConnection();
            connection.setAutoCommit(false);

            Long affectedUserRows = userDAO.update(user, connection);
            if (affectedUserRows != 1) {
                connection.rollback();
                throw new UserUpdatingException("Cannot update user");
            }
            Long affectedAvatarRows = avatarDAO.update(part, user.getId(), connection);
            if (affectedAvatarRows != 1) {
                connection.rollback();
                throw new UserUpdatingException("Cannot update user avatar");
            }
        } catch (IOException | SQLException e) {
            DbHelper.closeConnection(connection);
            throw new UserUpdatingException(e);
        }
    }

    public void delete(Long userId) throws UserDeletingException {
        Connection connection = null;
        try {
            connection = ConnectionProvider.getConnection();
            connection.setAutoCommit(false);

            Long affectedUserRows = userDAO.delete(userId, connection);
            if (affectedUserRows != 1) {
                connection.rollback();
                throw new UserDeletingException("Cannot delete user");
            }
            Long generatedAvatarId = avatarDAO.delete(userId, connection);
            if (generatedAvatarId == 0) {
                connection.rollback();
                throw new UserDeletingException("Cannot delete user's avatar");
            }
            connection.commit();
        } catch (SQLException e) {
            DbHelper.closeConnection(connection);
            throw new UserDeletingException(e);
        }
    }


    public Optional<User> getById(Long userId) {
        return userDAO.getById(userId);
    }

    public Optional<User> getByEmail(String email) {
        return userDAO.getByEmail(email);
    }

    public List<User> getAllUsersExceptOne(User currentUser, Paging paging) {
        long offset = paging.getLimit() * (paging.getPageNumber() - 1);
        return userDAO.getAllExceptOne(currentUser, offset, paging.getLimit(), paging.getSortField(), paging.getSortType());
    }

    public long countAll() {
        return userDAO.countAll();
    }

    public List<String> getColumnsNames() {
        return userDAO.getColumnsNames();
    }

    public boolean isUserExisted(String email) {
        return userDAO.isExisted(email);
    }

    public boolean isExisted(Long userId) {
        return userDAO.isExisted(userId);
    }
}
