package com.epam.amazingServlet.service.userServices.customer;

import com.epam.amazingServlet.dao.DaoException;
import com.epam.amazingServlet.dao.customer.CustomerDao;
import com.epam.amazingServlet.domain.user.Customer;
import com.epam.amazingServlet.service.exceptions.ServiceException;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerDao customerDao;

    public CustomerServiceImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public Customer findById(Long id) throws ServiceException {
        try {
            return customerDao.read(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Customer findByEmail(String email) throws ServiceException {
        try {
            return customerDao.getCustomerByEmail(email);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Customer> findAll() throws ServiceException {
        try {
            return customerDao.readAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(Customer customer) throws ServiceException {
        try {
            customerDao.update(customer);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(Customer customer) throws ServiceException {
        try {
            customerDao.create(customer);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            customerDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Long create(Customer customer) throws ServiceException {
        try {
            return customerDao.create(customer);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
