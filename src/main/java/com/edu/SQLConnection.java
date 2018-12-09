package com.edu;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Bartosz Śledź
 */
public interface SQLConnection {

    /**
     * @return {@link Connection}
     */
    Connection getConnection() throws SQLException, ClassNotFoundException;

    /**
     * @return {@link String}
     */
    String getName();
}
