package com.epam.amazingServlet.dao.bill;

import com.epam.amazingServlet.dao.Dao;
import com.epam.amazingServlet.dao.DaoException;
import com.epam.amazingServlet.domain.project.Bill;

import java.math.BigDecimal;

public interface BillDao extends Dao<Bill, Long> {

    Long create(Bill bill, Long projectId)throws DaoException;
    BigDecimal addToPaidAmountByProjectId(Long billId, BigDecimal addition) throws DaoException;
}
