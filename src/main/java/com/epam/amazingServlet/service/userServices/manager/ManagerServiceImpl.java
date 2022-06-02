package com.epam.amazingServlet.service.userServices.manager;

import com.epam.amazingServlet.dao.DaoException;
import com.epam.amazingServlet.dao.manager.ManagerDao;
import com.epam.amazingServlet.domain.user.Manager;
import com.epam.amazingServlet.domain.user.User;
import com.epam.amazingServlet.service.exceptions.ServiceException;

import java.util.List;

public class ManagerServiceImpl implements ManagerService {

    private final ManagerDao managerDao;

    public ManagerServiceImpl(ManagerDao managerDao) {
        this.managerDao = managerDao;
    }

    @Override
    public Manager findById(Long id) throws ServiceException {

        try {
            return managerDao.read(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Manager findByEmail(String email) throws ServiceException {
        try {
            return managerDao.getManagerByEmail(email);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Manager> findAll() throws ServiceException {
        try {
            return managerDao.readAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(Manager manager) throws ServiceException {
        try {
            managerDao.update(manager);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }

    @Override
    public void save(Manager manager) throws ServiceException {
        try {
            managerDao.create(manager);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            managerDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Long create(Manager manager) throws ServiceException {
        try {
            return managerDao.create(manager);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
