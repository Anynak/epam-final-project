package com.epam.amazingServlet.service.projectServices.bill;

import com.epam.amazingServlet.dao.DaoException;

import com.epam.amazingServlet.dao.bill.BillDao;
import com.epam.amazingServlet.domain.project.Bill;

import com.epam.amazingServlet.service.exceptions.ServiceException;

import java.math.BigDecimal;

public class BillServiceImpl implements BillService{
    private final BillDao billDao;

    public BillServiceImpl(BillDao billDao) {
        this.billDao = billDao;
    }

    @Override
    public void updateTotalAmount(BigDecimal totalAmount) {

    }

    @Override
    public void updatePaidAmount(BigDecimal paidAmount) {

    }


    @Override
    public Long createBillForProject(Bill bill, Long projectId) throws ServiceException {
        try {
            return billDao.create(bill,projectId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Bill getBill(Long billId) {
        return null;
    }

    @Override
    public void increaseSumByProjectId(Long projectId, BigDecimal addition) throws ServiceException {
        try {
            billDao.addToPaidAmountByProjectId(projectId,addition);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }




}
