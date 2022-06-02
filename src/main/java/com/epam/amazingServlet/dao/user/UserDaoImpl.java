package com.epam.amazingServlet.dao.user;

import com.epam.amazingServlet.dao.DaoException;
import com.epam.amazingServlet.domain.user.Position;
import com.epam.amazingServlet.domain.user.User;
import com.epam.amazingServlet.sqlDataBase.ConnectionBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    public UserDaoImpl(ConnectionBuilder connectionBuilder) {
        this.connectionBuilder = connectionBuilder;
    }

    private final ConnectionBuilder connectionBuilder;

    private User getUserFromRS(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("user_id"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setEmail(rs.getString("email"));
        user.setPassHash(rs.getString("password_hash"));
        user.setPosition(Position.valueOf(rs.getString("position")));
        rs.close();
        return user;
    }

    @Override
    public User read(Long id) throws DaoException {

        User user = null;
        String query = "select * from user where user_id=" + id;
        try (
                Connection con = connectionBuilder.getConnection();
                PreparedStatement ps = con.prepareStatement(query);
                ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                user = getUserFromRS(rs);
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return user;
    }

    @Override
    public List<User> readAll() throws DaoException {

        List<User> users = new ArrayList<>();
        try (
                Connection con = connectionBuilder.getConnection();
                PreparedStatement ps = con.prepareStatement("select * from user ");
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                users.add(getUserFromRS(rs));
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return users;
    }

    @Override
    public User getUserByEmail(String email) throws DaoException {
        User user = null;
        String query = "select * from user where email=" + "'" + email + "'";
        try (
                Connection con = connectionBuilder.getConnection();
                PreparedStatement ps = con.prepareStatement(query);
                ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                user = getUserFromRS(rs);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return user;
    }

    @Override
    public void update(User user) throws DaoException {
        System.out.println("user update");
        String query = "UPDATE user \n" +
                "SET first_name = '" + user.getFirstName() + "', \n" +
                "last_name = '" + user.getLastName() + "', \n" +
                "email = '" + user.getEmail() + "', \n" +
                "password_hash = '" + user.getPassHash() + "' \n" +
                "WHERE (user_id = " + user.getId() + ");\n";
        System.out.println(query);
        try (Connection con = connectionBuilder.getConnection();
             PreparedStatement ps = con.prepareStatement(query)
        ) {
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("You cannot delete an entity User");
    }

    @Override
    public Long create(User user) {
        throw new UnsupportedOperationException("You cannot create an entity User");
    }
}
