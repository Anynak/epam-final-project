package com.epam.amazingServlet.dao.technicalTask;

import com.epam.amazingServlet.dao.DaoException;
import com.epam.amazingServlet.dao.SQLUtil;
import com.epam.amazingServlet.domain.user.Customer;
import com.epam.amazingServlet.domain.project.TechnicalTask;
import com.epam.amazingServlet.sqlDataBase.ConnectionBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TechnicalTaskDaoImpl implements TechnicalTaskDao {
    private final ConnectionBuilder conBuilder;

    public TechnicalTaskDaoImpl(ConnectionBuilder conBuilder) {
        this.conBuilder = conBuilder;
    }

    private TechnicalTask getTechnicalTaskFromRS(ResultSet rs) throws SQLException {
        TechnicalTask technicalTask = new TechnicalTask();
        String title = rs.getString("technical_task_title");
        technicalTask.setTitle(title);
        Long customerId = rs.getLong("customer_id");
        Customer customer = new Customer();
        customer.setId(customerId);
        technicalTask.setCustomer(customer);
        technicalTask.setId(rs.getLong("technical_task_id"));
        return technicalTask;
    }

    @Override
    public Long create(TechnicalTask technicalTask) throws DaoException {
        String query = "INSERT INTO `technical_task` " +
                "(`technical_task_title`, `customer_id`) VALUES (?, ?);\n";
        try (Connection con = conBuilder.getConnection();
             PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, technicalTask.getTitle());
            ps.setLong(2, technicalTask.getCustomer().getId());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            Long technicalTaskId = null;
            if (rs != null && rs.next()) {
                technicalTaskId = rs.getLong(1);
                rs.close();
            }
            return technicalTaskId;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public TechnicalTask read(Long id) throws DaoException {
        String query = "SELECT * FROM `technical_task` WHERE `technical_task_id`=" + id + ";";
        try (Connection con = conBuilder.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()
        ) {
            if (rs.next()) {
                TechnicalTask technicalTask = getTechnicalTaskFromRS(rs);
                rs.close();
                return technicalTask;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return null;
    }

    @Override
    public void update(TechnicalTask techTask) throws DaoException {
        String query = "UPDATE `technical_task` " +
                "SET `technical_task_title` = '" + techTask.getTitle() + "', " +
                "`customer_id` = '" + techTask.getCustomer().getId() + "' " +
                "WHERE (`technical_task_id` = '" + techTask.getId() + "');\n";
        SQLUtil.executeSqlUpdate(conBuilder, query);

    }

    @Override
    public void delete(Long id) throws DaoException {
        String query = "DELETE FROM `technical_task` " +
                "WHERE (`technical_task_id` = '" + id + "');\n";
        SQLUtil.executeSqlUpdate(conBuilder, query);

    }

    @Override
    public List<TechnicalTask> readAllBuCustomerId(Long id) throws DaoException {
        String query = "SELECT * FROM `technical_task` WHERE `customer_id` = " + id;
        System.out.println(query);
        try (Connection con = conBuilder.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()
        ) {
            List<TechnicalTask> tasks = new ArrayList<>();
            while (rs.next()) {
                tasks.add(getTechnicalTaskFromRS(rs));
            }
            System.out.println("3 " + tasks);
            rs.close();
            return tasks;
        } catch (SQLException throwables) {
            throw new DaoException();
        }
    }

    @Override
    public List<TechnicalTask> readAll() throws DaoException {
        String query = "SELECT * FROM `technical_task`";
        try (Connection con = conBuilder.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()
        ) {
            List<TechnicalTask> tasks = new ArrayList<>();
            while (rs.next()) {
                tasks.add(getTechnicalTaskFromRS(rs));
            }
            rs.close();
            return tasks;
        } catch (SQLException throwables) {
            throw new DaoException();
        }
    }

    @Override
    public List<TechnicalTask> readUnprocessedTasks() throws DaoException {
        String query = "SELECT * FROM `technical_task`" +
                "LEFT JOIN finproject.project\n" +
                "ON technical_task.technical_task_id=project.technical_task_id\n" +
                "WHERE project.technical_task_id is null";
        System.out.println(query);
        try (Connection con = conBuilder.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()
        ) {
            List<TechnicalTask> tasks = new ArrayList<>();
            while (rs.next()) {
                tasks.add(getTechnicalTaskFromRS(rs));
            }
            rs.close();
            return tasks;
        } catch (SQLException throwables) {
            throw new DaoException();
        }
    }


}
