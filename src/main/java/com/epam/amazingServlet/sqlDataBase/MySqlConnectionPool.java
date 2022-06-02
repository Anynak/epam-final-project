package com.epam.amazingServlet.sqlDataBase;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class MySqlConnectionPool implements ConnectionBuilder {
    private MySqlConnectionPool() {
    }

    private static MySqlConnectionPool instance = null;

    public static MySqlConnectionPool getInstance() {
        if (instance == null) {
            instance = new MySqlConnectionPool();
        }
        return instance;
    }

    @Override
    public Connection getConnection() {
        Context context;
        DataSource dataSource;
        Connection connection;
        try {
            context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/pool/local");
            connection = dataSource.getConnection();
            System.out.println("DB connected");
        } catch (SQLException | NamingException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
