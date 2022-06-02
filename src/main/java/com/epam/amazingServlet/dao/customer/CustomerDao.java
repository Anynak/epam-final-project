package com.epam.amazingServlet.dao.customer;

import com.epam.amazingServlet.dao.Dao;
import com.epam.amazingServlet.dao.DaoException;
import com.epam.amazingServlet.domain.user.Customer;
import com.epam.amazingServlet.domain.user.Developer;

import java.util.List;

public interface CustomerDao extends Dao<Customer, Long> {
    Customer getCustomerByEmail(String email) throws DaoException;
    List<Customer> readAll() throws DaoException;
}
