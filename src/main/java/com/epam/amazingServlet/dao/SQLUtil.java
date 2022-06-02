package com.epam.amazingServlet.dao;

import com.epam.amazingServlet.sqlDataBase.ConnectionBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLUtil {
    public static void executeSqlUpdate(ConnectionBuilder conBuilder, String query) throws DaoException {
        try (Connection con = conBuilder.getConnection();
             PreparedStatement ps = con.prepareStatement(query)
        ) {
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
