package com.epam.amazingServlet.service.userServices.developer;

import com.epam.amazingServlet.dao.DaoException;
import com.epam.amazingServlet.dao.developer.DeveloperDao;
import com.epam.amazingServlet.domain.user.Developer;
import com.epam.amazingServlet.service.exceptions.ServiceException;


import java.util.List;

public class DeveloperServiceImpl implements DeveloperService {
    private final DeveloperDao developerDao;

    public DeveloperServiceImpl(DeveloperDao developerDao){
        this.developerDao = developerDao;
    }
    @Override
    public Developer findById(Long id) throws ServiceException {
        try {
            return developerDao.read(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Developer> findAll() throws ServiceException {
        try {
            return developerDao.readAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }



    @Override
    public void update(Developer developer) throws ServiceException {
        try {
            developerDao.update(developer);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(Developer developer) throws ServiceException {
        try {
            developerDao.create(developer);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            developerDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Long create(Developer developer) throws ServiceException {
        try {
            return developerDao.create(developer);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Developer findByEmail(String email) throws ServiceException {
        try {
            return developerDao.getDeveloperByEmail(email);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void makeDevelopersBusy(List<String> devIds, Long projectId) throws ServiceException {
        try {
            developerDao.setProjectId(devIds, projectId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
    public List<Developer> getNotBusy() throws ServiceException{
        try {
            return developerDao.readNotBusy();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Developer> getDevelopersInProject(Long projectId) throws ServiceException {
        try {
            return developerDao.getDevelopersByProjectId(projectId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
