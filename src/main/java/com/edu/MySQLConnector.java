package com.edu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Bartosz Śledź
 */
public class MySQLConnector implements SQLConnection {

    private final static String CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private final static String URL = "jdbc:mysql://127.0.0.1:3306/library";
    private final static String USER = "user";
    private final static String PASS = "user";
    private final static String NAME = "MySQL";

    /**
     * Return connection for MySql db.
     *
     * @return {@link Connection}
     * @throws SQLException           {@link SQLException}
     * @throws ClassNotFoundException {@link ClassNotFoundException}
     */
    @Override
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName(CLASS_NAME);

        return DriverManager.getConnection(URL, USER, PASS);
    }

    /**
     * Get db name.
     *
     * @return {@link String}
     */
    @Override
    public String getName() {
        return NAME;
    }

}
