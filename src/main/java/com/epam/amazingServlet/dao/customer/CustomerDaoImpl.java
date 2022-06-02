package com.epam.amazingServlet.dao.customer;

import com.epam.amazingServlet.dao.DaoException;
import com.epam.amazingServlet.domain.user.Customer;
import com.epam.amazingServlet.domain.user.Position;
import com.epam.amazingServlet.sqlDataBase.ConnectionBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao{

    public CustomerDaoImpl(ConnectionBuilder conBuilder) {
        this.conBuilder = conBuilder;
    }

    private final ConnectionBuilder conBuilder;

    private Customer getCustomerFromRS(ResultSet rs) throws SQLException {
        Customer customer = new Customer();
        customer.setId(rs.getLong("user_id"));
        customer.setFirstName(rs.getString("first_name"));
        customer.setLastName(rs.getString("last_name"));
        customer.setEmail(rs.getString("email"));
        customer.setPassHash(rs.getString("password_hash"));
        customer.setPosition(Position.valueOf(rs.getString("position")));
        return customer;
    }

    @Override
    public Long create(Customer customer) throws DaoException {
        Long userId = null;
        try (Connection con = conBuilder.getConnection()) {
            con.setAutoCommit(false);
            String query1 = "INSERT INTO user (`first_name`, `last_name`, `email`, `password_hash`, `position`) VALUES " +
                    "(?, ?, ?, ?, ?);";
            PreparedStatement ps = con.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, customer.getFirstName());
            ps.setString(2, customer.getLastName());
            ps.setString(3, customer.getEmail());
            ps.setString(4, customer.getPassHash());
            ps.setString(5, customer.getPosition().name());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs != null && rs.next()) {
                userId = rs.getLong(1);
                rs.close();
            }
            String query3 = "INSERT INTO customer (`user_id`) VALUES (" + userId + ");";
            ps = con.prepareStatement(query3);
            ps.executeUpdate();
            con.commit();
            ps.close();

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return userId;
    }

    @Override
    public Customer read(Long id) throws DaoException {
        Customer customer = new Customer();
        String query =
                "SELECT *  \n" +
                        "FROM \n" +
                        "user INNER JOIN customer\n" +
                        "ON user.user_id = customer.user_id\n" +
                        "WHERE user.user_id = " + id + ";";
        try (Connection con = conBuilder.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                customer = getCustomerFromRS(rs);
                System.out.println("customer DAO "+ customer);
                rs.close();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return customer;
    }

    @Override
    public void update(Customer customer) throws DaoException {

            String query = "UPDATE user \n" +
                    "SET first_name = ? " + ", \n" +
                    "last_name = ? " + ", \n" +
                    "email = ? " + ", \n" +
                    "password_hash = ? " + ", \n" +
                    "position = ? " + " \n" +
                    "WHERE (user_id = ? " + ");\n";

            try (Connection con = conBuilder.getConnection()) {
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, customer.getFirstName());
                ps.setString(2, customer.getLastName());
                ps.setString(3, customer.getEmail());
                ps.setString(4, customer.getPassHash());
                ps.setString(5, customer.getPosition().name());
                ps.setLong(6, customer.getId());
                System.out.println(ps);
                ps.executeUpdate();
                ps.close();

            } catch (SQLException e) {
                throw new DaoException(e);
            }
    }

    @Override
    public void delete(Long id) throws DaoException {
        String query = "DELETE FROM user WHERE (user_id = " + id + ");" +
                "DELETE FROM customer WHERE (user_id = " + id + ");";
        try (Connection con = conBuilder.getConnection();
             PreparedStatement ps = con.prepareStatement(query)
        ) {
            ps.executeQuery();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Customer getCustomerByEmail(String email) throws DaoException {
        Customer customer = new Customer();
        String query =
                "SELECT user.user_id, first_name, email, last_name, password_hash \n" +
                        "FROM \n" +
                        "user INNER JOIN customer c on user.user_id = c.user_id\n" +
                        "ON user.user_id = сustomer.user_id\n" +
                        "WHERE user.email = " + email + ";";
        try (Connection con = conBuilder.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                customer = getCustomerFromRS(rs);
                rs.close();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return customer;
    }

    @Override
    public List<Customer> readAll() throws DaoException {
        List<Customer> customers = new ArrayList<>();
        String query =
                "SELECT user.user_id, first_name, email, last_name, password_hash, position \n" +
                        "FROM \n" +
                        "user INNER JOIN customer\n" +
                        "ON user.user_id = customer.user_id\n" +
                        ";";
        try (Connection con = conBuilder.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                customers.add(getCustomerFromRS(rs));
                rs.close();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return customers;
    }
}
