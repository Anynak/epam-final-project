package com.epam.amazingServlet.service.projectServices.bill;

import com.epam.amazingServlet.domain.project.Bill;
import com.epam.amazingServlet.service.exceptions.ServiceException;

import java.math.BigDecimal;

public interface BillService {
    void updateTotalAmount(BigDecimal totalAmount)  throws ServiceException;
    void updatePaidAmount(BigDecimal paidAmount)  throws ServiceException;

    Long createBillForProject(Bill bill, Long projectId) throws ServiceException;

    Bill getBill(Long billId) throws ServiceException;

    void increaseSumByProjectId(Long billId, BigDecimal addition) throws ServiceException;;
}
