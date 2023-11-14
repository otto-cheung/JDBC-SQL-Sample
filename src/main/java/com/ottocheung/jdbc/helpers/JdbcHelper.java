package com.ottocheung.jdbc.helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class JdbcHelper {

    // logger
    private static final Logger LOGGER = Logger.getLogger(JdbcHelper.class.getName());

    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        try {
            LOGGER.info("Connecting to database...");
            Connection connection = DriverManager.getConnection(JdbcConstants.DB_URL, JdbcConstants.username,
                    JdbcConstants.password);
            return connection;
        } catch (Exception e) {
            LOGGER.severe("Error connecting to database: " + e.getMessage());
            throw e;
        }
    }

    public static void closeConnection(Connection con) throws SQLException {
        if (con != null) {
            con.close();
        }
    }
}
