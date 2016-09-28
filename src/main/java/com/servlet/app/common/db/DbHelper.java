package com.servlet.app.common.db;

import java.sql.Connection;
import java.sql.SQLException;

public class DbHelper {
    private DbHelper() {}

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.rollback();
                connection.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
