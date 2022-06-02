package com.epam.amazingServlet.dao.bill;

import com.epam.amazingServlet.dao.DaoException;
import com.epam.amazingServlet.domain.project.Bill;
import com.epam.amazingServlet.sqlDataBase.ConnectionBuilder;

import java.math.BigDecimal;
import java.sql.*;

public class BillDaoImpl implements BillDao {
    private final ConnectionBuilder conBuilder;

    public BillDaoImpl(ConnectionBuilder conBuilder) {
        this.conBuilder = conBuilder;
    }

    private Bill getBillFromRs(ResultSet rs) throws SQLException {
        Bill bill = new Bill();
        bill.setId(rs.getLong("bill_id"));
        bill.setPaidAmount(rs.getBigDecimal("paid_amount"));
        bill.setTotalAmount(rs.getBigDecimal("total_amount"));
        return bill;
    }

    @Override
    public Long create(Bill bill, Long projectId) throws DaoException {

        try (Connection con = conBuilder.getConnection()){
            con.setAutoCommit(false);
            String query1 = "INSERT INTO " +
                    "`bill` (`total_amount`) " +
                    "VALUES (?);\n ";
            PreparedStatement ps = con.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
            ps.setBigDecimal(1, bill.getTotalAmount());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();

            if (rs != null && rs.next()) {
                long billId = rs.getLong(1);
                rs.close();
                String query2 = "UPDATE `project` SET `bill_id` = ? WHERE (`project_id` = ?);";
                ps = con.prepareStatement(query2);
                ps.setLong(1,billId);
                ps.setLong(2,projectId);
                ps.executeUpdate();
                con.commit();
                ps.close();
                return billId;

            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return null;
    }

    @Override
    public BigDecimal addToPaidAmountByProjectId(Long projectId, BigDecimal addition) throws DaoException {
        String query = "UPDATE bill INNER JOIN project \n" +
                "ON `bill`.`bill_id` = `project`.`bill_id`\n" +
                "SET `total_amount` = `total_amount`+ ? \n" +
                "WHERE `project`.`project_id`= ?;";
        try (Connection con = conBuilder.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setBigDecimal(1, addition);
            ps.setLong(2, projectId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return null;
    }

    @Override
    public Long create(Bill entity) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Bill read(Long id) throws DaoException {
        String query = "SELECT * FROM bill WHERE `bill_id` = ?;";
        try (Connection con = conBuilder.getConnection();
             PreparedStatement ps = con.prepareStatement(query)
        ) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            Bill bill = null;
            if (rs.next()) {
                bill = getBillFromRs(rs);
            }
            rs.close();
            return bill;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Bill entity) throws DaoException {

    }

    @Override
    public void delete(Long id) throws DaoException {

    }


}
