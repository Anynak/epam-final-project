package com.epam.amazingServlet.dao.work;

import com.epam.amazingServlet.dao.DaoException;
import com.epam.amazingServlet.dao.SQLUtil;
import com.epam.amazingServlet.domain.project.Work;
import com.epam.amazingServlet.domain.user.Qualification;
import com.epam.amazingServlet.sqlDataBase.ConnectionBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkDaoImpl implements WorkDao {
    private final ConnectionBuilder conBuilder;

    public WorkDaoImpl(ConnectionBuilder conBuilder) {
        this.conBuilder = conBuilder;
    }

    private Work getWorkFromRS(ResultSet rs) throws SQLException {
        Work work = new Work();
        work.setId(rs.getLong("work_id"));
        work.setTechnicalTaskId(rs.getLong("technical_task_id"));
        work.setDescription(rs.getString("work_description"));
        work.setFinishStatus(rs.getBoolean("finish_status"));
        work.setNumberOfRequiredSpecialists(rs.getInt("number_of_required_specialists"));
        work.setRequiredQualification(Qualification.valueOf(rs.getString("qualification")));

        return work;
    }

    @Override
    public Long create(Work work) throws DaoException {

        Long workId = null;
        try (Connection con = conBuilder.getConnection()) {
            String query = "INSERT INTO `work` (" +
                    "`technical_task_id`, " +
                    "`required_qualification`, " +
                    "`number_of_required_specialists`, " +
                    "`work_description`) " +
                    "VALUES " +
                    "(?, ?, ?, ?);";
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, work.getTechnicalTaskId());
            ps.setInt(2, work.getRequiredQualification().getId());
            ps.setInt(3, work.getNumberOfRequiredSpecialists());
            ps.setString(4, work.getDescription());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();

            if (rs != null && rs.next()) {
                workId = rs.getLong(1);
                rs.close();
            }
            ps.close();

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return workId;
    }

    @Override
    public Work read(Long id) throws DaoException {
        String query = "SELECT * FROM `work` " +
                "INNER JOIN `qualification`\n" +
                "on `work`.`required_qualification` = `qualification`.`qualification_id` " +
                "WHERE work_id = " + id + ";";
        try (Connection con = conBuilder.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return getWorkFromRS(rs);
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return null;
    }

    @Override
    public void update(Work work) throws DaoException {

        System.out.println(work.getRequiredQualification().getId());
        String query = "UPDATE `work` " +
                "SET `required_qualification` = ?, " +
                "`number_of_required_specialists` = ?, " +
                "`work_description` = ?, " +
                "`technical_task_id` = ? " +
                "WHERE (`work_id` = ?);";
        try (Connection con = conBuilder.getConnection()) {

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, work.getRequiredQualification().getId());
            ps.setInt(2, work.getNumberOfRequiredSpecialists());
            ps.setString(3, work.getDescription());
            ps.setLong(4, work.getTechnicalTaskId());
            ps.setLong(5, work.getId());
            System.out.println(query);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Long id) throws DaoException {
        String query = "DELETE FROM `work` " +
                "WHERE (`work_id` = '" + id + "');\n";
        SQLUtil.executeSqlUpdate(conBuilder, query);
    }

    @Override
    public List<Work> readAllBuTechnicalTaskId(Long id) throws DaoException {
        String query = "SELECT * FROM `work` " +
                "INNER JOIN `finproject`.`qualification`\n" +
                "on `work`.`required_qualification` = `qualification`.`qualification_id` " +
                "WHERE `technical_task_id`=" + id + ";";
        List<Work> works = new ArrayList<>();
        try (Connection con = conBuilder.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                works.add(getWorkFromRS(rs));
            }
            return works;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Work> readFitWorksBuDeveloperId(Long id) throws DaoException {
        String query = "SELECT * FROM `work`\n" +
                "INNER JOIN `project`\n" +
                "ON `work`.`technical_task_id`=`project`.`technical_task_id`\n" +
                "INNER JOIN `developer`\n" +
                "ON `project`.`project_id`=`developer`.`project_id`\n " +
                "INNER JOIN `qualification`\n" +
                "ON `qualification`.`qualification_id`=`developer`.`qualification_id`" +
                "WHERE `developer`.`user_id`= " + id + " " +
                "AND `developer`.`qualification_id`=`work`.`required_qualification`;";
        System.out.println(query);
        List<Work> works = new ArrayList<>();
        try (Connection con = conBuilder.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                works.add(getWorkFromRS(rs));
            }
            return works;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void updateDescription(String description) {

    }

    @Override
    public void updateNumberOfSpecialists(Integer NumberOfSpecialists) {

    }

    @Override
    public void updateQualification(Integer NumberOfSpecialists) {

    }

    @Override
    public void updateFinishStatus(boolean finishStatus) {

    }
}
