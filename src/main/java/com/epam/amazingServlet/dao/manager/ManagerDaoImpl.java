package com.epam.amazingServlet.dao.manager;

import com.epam.amazingServlet.dao.DaoException;
import com.epam.amazingServlet.domain.user.Manager;
import com.epam.amazingServlet.domain.user.Position;
import com.epam.amazingServlet.sqlDataBase.ConnectionBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerDaoImpl implements ManagerDao {

    public ManagerDaoImpl(ConnectionBuilder conBuilder) {
        this.conBuilder = conBuilder;
    }

    private final ConnectionBuilder conBuilder;

    private Manager getManagerFromRS(ResultSet rs) throws SQLException {
        Manager manager = new Manager();
        manager.setId(rs.getLong("user_id"));
        manager.setFirstName(rs.getString("first_name"));
        manager.setLastName(rs.getString("last_name"));
        manager.setEmail(rs.getString("email"));
        manager.setPassHash(rs.getString("password_hash"));
        manager.setPosition(Position.valueOf(rs.getString("position")));
        return manager;
    }

    @Override
    public Manager getManagerByEmail(String email) throws DaoException {
        Manager manager = new Manager();
        String query =
                "SELECT user.user_id, first_name, email, last_name, password_hash \n" +
                        "FROM \n" +
                        "user INNER JOIN manager\n" +
                        "ON user.user_id = manager.user_id\n" +
                        "WHERE user.email = " + email + ";";
        try (Connection con = conBuilder.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                manager = getManagerFromRS(rs);
                rs.close();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return manager;
    }

    @Override
    public List<Manager> readAll() throws DaoException {
        List<Manager> managers = new ArrayList<>();
        String query =
                "SELECT user.user_id, first_name, email, last_name, password_hash, position \n" +
                        "FROM \n" +
                        "user INNER JOIN manager\n" +
                        "ON user.user_id = manager.user_id\n" +
                        ";";
        try (Connection con = conBuilder.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                managers.add(getManagerFromRS(rs));
                rs.close();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return managers;
    }

    @Override
    public Long create(Manager manager) throws DaoException {

        try (Connection con = conBuilder.getConnection()) {
            con.setAutoCommit(false);
            String query1 = "INSERT INTO user (`first_name`, `last_name`, `email`, `password_hash`, `position`) VALUES " +
                    "(?, ?, ?, ?, ?);";
            try (PreparedStatement ps1 = con.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS)) {
                ps1.setString(1, manager.getFirstName());
                ps1.setString(2, manager.getLastName());
                ps1.setString(3, manager.getEmail());
                ps1.setString(4, manager.getPassHash());
                ps1.setString(5, manager.getPosition().name());
                ps1.executeUpdate();
                ResultSet rs = ps1.getGeneratedKeys();
                if (rs != null && rs.next()) {
                    long userId = rs.getLong(1);
                    rs.close();
                    String query2 = "INSERT INTO manager (`user_id`) VALUES (" + userId + ");";
                    try (PreparedStatement ps = con.prepareStatement(query2)) {
                        ps.executeUpdate();
                        con.commit();
                        return userId;
                    }
                } else throw new DaoException("Transaction failed");
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Manager read(Long id) throws DaoException {
        Manager manager = new Manager();
        String query =
                "SELECT * \n" +
                        "FROM \n" +
                        "user INNER JOIN manager\n" +
                        "ON user.user_id = manager.user_id\n" +
                        "WHERE user.user_id = " + id + ";";
        try (Connection con = conBuilder.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                manager = getManagerFromRS(rs);
                rs.close();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return manager;
    }

    @Override
    public void update(Manager manager) throws DaoException {

        String query = "UPDATE user \n" +
                "SET first_name = ? " + ", \n" +
                "last_name = ? " + ", \n" +
                "email = ? " + ", \n" +
                "password_hash = ? " + ", \n" +
                "position = ? " + " \n" +
                "WHERE (user_id = ? " + ");\n";

        try (Connection con = conBuilder.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, manager.getFirstName());
            ps.setString(2, manager.getLastName());
            ps.setString(3, manager.getEmail());
            ps.setString(4, manager.getPassHash());
            ps.setString(5, manager.getPosition().name());
            ps.setLong(6, manager.getId());
            System.out.println(ps);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Long id) throws DaoException {
        String query = "DELETE FROM user WHERE (user_id = " + id + ");" +
                "DELETE FROM manager WHERE (user_id = " + id + ");";
        try (Connection con = conBuilder.getConnection();
             PreparedStatement ps = con.prepareStatement(query)
        ) {
            ps.executeQuery();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
