package com.epam.amazingServlet.sqlDataBase;

import java.sql.Connection;

public interface ConnectionBuilder {
    Connection getConnection();
}
