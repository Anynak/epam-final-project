package com.epam.amazingServlet.dao.developer;

import com.epam.amazingServlet.dao.DaoException;
import com.epam.amazingServlet.dao.SQLUtil;
import com.epam.amazingServlet.domain.user.Position;
import com.epam.amazingServlet.domain.user.Developer;
import com.epam.amazingServlet.domain.user.Qualification;
import com.epam.amazingServlet.sqlDataBase.ConnectionBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeveloperDaoImpl implements DeveloperDao {


    public DeveloperDaoImpl(ConnectionBuilder conBuilder) {
        this.conBuilder = conBuilder;
    }

    private final ConnectionBuilder conBuilder;

    private Developer getDeveloperFromRS(ResultSet rs) throws SQLException {
        Developer developer = new Developer();
        developer.setId(rs.getLong("user_id"));
        developer.setFirstName(rs.getString("first_name"));
        developer.setLastName(rs.getString("last_name"));
        developer.setEmail(rs.getString("email"));
        developer.setPassHash(rs.getString("password_hash"));
        developer.setPosition(Position.valueOf(rs.getString("position")));
        developer.setQualification(Qualification.valueOf(rs.getString("qualification")));
        developer.setProjectId(rs.getLong("project_id"));
        return developer;
    }

    @Override
    public Developer getDeveloperByEmail(String email) throws DaoException {
        Developer developer = new Developer();
        String query =
                "SELECT user.user_id, first_name, email, last_name, password_hash, qualification, project_id \n" +
                        "FROM \n" +
                        "user INNER JOIN developer\n" +
                        "ON user.user_id = developer.user_id\n" +
                        "INNER JOIN qualification \n" +
                        "ON developer.qualification_id = qualification.qualification_id\n" +
                        "WHERE user.email = " + email + ";";
        try (Connection con = conBuilder.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()
        ) {
            if (rs.next()) {
                developer = getDeveloperFromRS(rs);
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return developer;
    }

    @Override
    public List<Developer> getDevelopersByProjectId(Long projectId) throws DaoException {
        List<Developer> developers = new ArrayList<>();
        String query =
                "SELECT user.user_id, first_name, email, last_name, password_hash, qualification, position, project_id \n" +
                        "FROM \n" +
                        "user INNER JOIN developer\n" +
                        "ON user.user_id = developer.user_id\n" +
                        "INNER JOIN qualification \n" +
                        "ON developer.qualification_id = qualification.qualification_id \n" +
                        "WHERE developer.project_id = " + projectId + ";";
        try (Connection con = conBuilder.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                developers.add(getDeveloperFromRS(rs));
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return developers;
    }

    @Override
    public List<Developer> readAll() throws DaoException {
        List<Developer> developers = new ArrayList<>();
        String query =
                "SELECT user.user_id, first_name, email, last_name, password_hash, qualification, position, project_id \n" +
                        "FROM \n" +
                        "user INNER JOIN developer\n" +
                        "ON user.user_id = developer.user_id\n" +
                        "INNER JOIN qualification \n" +
                        "ON developer.qualification_id = qualification.qualification_id\n" +
                        ";";
        System.out.println(query);
        try (Connection con = conBuilder.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                developers.add(getDeveloperFromRS(rs));
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return developers;
    }

    @Override
    public List<Developer> readNotBusy() throws DaoException {
        List<Developer> developers = new ArrayList<>();
        String query =
                "SELECT user.user_id, first_name, email, last_name, password_hash, qualification, position, project_id \n" +
                        "FROM \n" +
                        "user INNER JOIN developer\n" +
                        "ON user.user_id = developer.user_id\n" +
                        "INNER JOIN qualification \n" +
                        "ON developer.qualification_id = qualification.qualification_id \n" +
                        "WHERE developer.project_id is null;";
        try (Connection con = conBuilder.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                developers.add(getDeveloperFromRS(rs));
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return developers;
    }

    @Override
    public Long create(Developer developer) throws DaoException {

        try (Connection con = conBuilder.getConnection()) {
            con.setAutoCommit(false);
            String query1 = "INSERT INTO user (`first_name`, `last_name`, `email`, `password_hash`, `position`) VALUES " +
                    "(?, ?, ?, ?, ?);";
            try (PreparedStatement ps1 = con.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS)) {
                ps1.setString(1, developer.getFirstName());
                ps1.setString(2, developer.getLastName());
                ps1.setString(3, developer.getEmail());
                ps1.setString(4, developer.getPassHash());
                ps1.setString(5, developer.getPosition().name());
                ps1.executeUpdate();
                ResultSet rs = ps1.getGeneratedKeys();
                if (rs != null && rs.next()) {
                    long userId = rs.getLong(1);
                    rs.close();
                    String query3 = "INSERT INTO developer (`user_id` ) VALUES (" + userId + ");";
                    try (PreparedStatement ps2 = con.prepareStatement(query3)) {
                        ps2.executeUpdate();
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
    public Developer read(Long id) throws DaoException {
        Developer developer = new Developer();
        String query =
                "SELECT user.user_id, first_name, last_name, email, password_hash, qualification, project_id, `position` \n" +
                        "FROM \n" +
                        "user INNER JOIN developer\n" +
                        "ON user.user_id = developer.user_id\n" +
                        "INNER JOIN qualification \n" +
                        "ON developer.qualification_id = qualification.qualification_id\n" +
                        "WHERE user.user_id = " + id + ";";

        try (Connection con = conBuilder.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()
        ) {
            if (rs.next()) {
                developer = getDeveloperFromRS(rs);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return developer;
    }

    @Override
    public void update(Developer developer) throws DaoException {
        String query = "UPDATE user \n" +
                "SET first_name = ? " + ", \n" +
                "last_name = ? " + ", \n" +
                "email = ? " + ", \n" +
                "password_hash = ? " + ", \n" +
                "position = ? " + " \n" +
                "WHERE (user_id = ? " + ");\n" +
                "UPDATE developer \n" +
                "SET qualification_id = ?" + " \n" +
                "WHERE (user_id = ?" + ");\n";

        try (Connection con = conBuilder.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1, developer.getFirstName());
                ps.setString(2, developer.getLastName());
                ps.setString(3, developer.getEmail());
                ps.setString(4, developer.getPassHash());
                ps.setString(5, developer.getPosition().name());
                ps.setLong(6, developer.getId());
                ps.setInt(7, developer.getQualification().getId());
                ps.setLong(8, developer.getId());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    @Override
    public void setProjectId(List<String> devIds, Long projectId) throws DaoException {
        StringBuilder query = new StringBuilder();
        for (String devId : devIds) {
            query.append("UPDATE `developer` SET `project_id` = '").append(projectId).append("' WHERE (`user_id` = '").append(devId).append("');");
        }
        try (Connection con = conBuilder.getConnection();
             PreparedStatement ps = con.prepareStatement(String.valueOf(query))
        ) {
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    @Override
    public void delete(Long id) throws DaoException {

    }
}
