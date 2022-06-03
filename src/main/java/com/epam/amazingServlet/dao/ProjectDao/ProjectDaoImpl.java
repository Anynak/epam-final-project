package com.epam.amazingServlet.dao.ProjectDao;

import com.epam.amazingServlet.dao.DaoException;
import com.epam.amazingServlet.domain.project.*;
import com.epam.amazingServlet.sqlDataBase.ConnectionBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDaoImpl implements ProjectDao {
    private final ConnectionBuilder connectionBuilder;

    public ProjectDaoImpl(ConnectionBuilder connectionBuilder) {
        this.connectionBuilder = connectionBuilder;
    }

    private static Project getProjectFromRS(ResultSet rs) throws SQLException {
        String projectName = rs.getString("project_name");
        Long projectId = rs.getLong("project_id");
        Long managerId = rs.getLong("manager_id");
        ProjectStatus projectStatus = (ProjectStatus.valueOf(rs.getString("status")));
        Long technicalTaskId = rs.getLong("technical_task_id");
        Long billId = rs.getLong("bill_id");
        Bill bill = new Bill();
        bill.setId(billId);

        TechnicalTask technicalTask = new TechnicalTask();
        technicalTask.setId(technicalTaskId);

        Project project = new Project();
        project.setProjectName(projectName);
        project.setId(projectId);
        project.setManagerId(managerId);
        project.setProjectStatus(projectStatus);
        project.setTechnicalTask(technicalTask);
        project.setBill(bill);
        return project;

    }

    @Override
    public Long create(Project project) throws DaoException {
        String query = "INSERT INTO `project` " +
                "(`manager_id`, `technical_task_id`, `project_name`) " +
                "VALUES (?, ?, ?);\n";
        Long projectId = null;
        try (Connection con = connectionBuilder.getConnection();
             PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, project.getManagerId());
            ps.setLong(2, project.getTechnicalTask().getId());
            ps.setString(3, project.getProjectName());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs != null && rs.next()) {
                projectId = rs.getLong(1);
                rs.close();
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return projectId;
    }

    @Override
    public List<Project> readAll() throws DaoException {

        String query = "SELECT * FROM `project` ";
        try (Connection con = connectionBuilder.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()
        ) {
            List<Project> projects = new ArrayList<>();
            while (rs.next()) {
                projects.add(getProjectFromRS(rs));
            }
            rs.close();
            return projects;
        } catch (SQLException throwables) {
            throw new DaoException();
        }

    }

    @Override
    public Project readBuId(Long id) throws DaoException {
        String query = "SELECT * FROM `project` " +
                "WHERE project_id = " + id;
        try (Connection con = connectionBuilder.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()
        ) {
            Project project = new Project();
            if (rs.next()) {
                project = (getProjectFromRS(rs));
            }
            rs.close();
            return project;
        } catch (SQLException throwables) {
            throw new DaoException();
        }
    }

    @Override
    public List<Project> readAllBuManagerId(Long id) throws DaoException {

        String query = "SELECT * FROM `project` " +
                "WHERE manager_id = " + id;
        try (Connection con = connectionBuilder.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()
        ) {
            List<Project> projects = new ArrayList<>();
            while (rs.next()) {
                projects.add(getProjectFromRS(rs));
            }
            rs.close();
            return projects;
        } catch (SQLException throwables) {
            throw new DaoException();
        }
    }

}
