package com.epam.amazingServlet.service.projectServices.technicalTask;

import com.epam.amazingServlet.dao.DaoException;
import com.epam.amazingServlet.dao.technicalTask.TechnicalTaskDao;
import com.epam.amazingServlet.domain.project.TechnicalTask;
import com.epam.amazingServlet.service.exceptions.ServiceException;

import java.util.List;

public class TechnicalTaskServiceImpl implements TechnicalTaskService {
    private final TechnicalTaskDao technicalTaskDao;

    public TechnicalTaskServiceImpl(TechnicalTaskDao technicalTaskDao) {
        this.technicalTaskDao = technicalTaskDao;
    }

    @Override
    public Long addTechnicalTask(TechnicalTask technicalTask) throws ServiceException {
        try {
            return technicalTaskDao.create(technicalTask);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void removeTechnicalTask(Long id) throws ServiceException {
        try {
            technicalTaskDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public TechnicalTask findById(Long id) throws ServiceException {
        try {
            return technicalTaskDao.read(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<TechnicalTask> findAllBuCustomerId(Long id) throws ServiceException {
        try {
            return technicalTaskDao.readAllBuCustomerId(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }

    @Override
    public List<TechnicalTask> findAll() throws ServiceException {
        try {
            return technicalTaskDao.readAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<TechnicalTask> findUnprocessed() throws ServiceException {
        try {
            return technicalTaskDao.readUnprocessedTasks();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
