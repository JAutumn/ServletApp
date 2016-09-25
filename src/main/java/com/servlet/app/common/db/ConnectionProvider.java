package com.servlet.app.common.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionProvider {
    private static final DataSource dataSource;

    static {
        try {
            InitialContext initialContext = new InitialContext();
            dataSource = (DataSource) initialContext.lookup("java:/comp/env/jdbc/servletAppDB");
            if (dataSource == null) {
                throw new RuntimeException("Data source not found");
            }
        } catch (NamingException e) {
            throw new RuntimeException("Can't initialize context");
        }
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    private ConnectionProvider() {}
}
