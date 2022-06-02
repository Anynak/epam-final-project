package com.epam.amazingServlet.service.projectServices.project;

import com.epam.amazingServlet.dao.DaoException;
import com.epam.amazingServlet.dao.ProjectDao.ProjectDao;
import com.epam.amazingServlet.domain.project.Project;
import com.epam.amazingServlet.domain.project.ProjectStatus;
import com.epam.amazingServlet.service.exceptions.ServiceException;

import java.util.List;

public class ProjectServiceImpl implements ProjectService {
    private final ProjectDao projectDao;

    public ProjectServiceImpl(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    @Override
    public Long createProject(Project project) throws ServiceException {
        try {
            return projectDao.create(project);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void setStatus(ProjectStatus projectStatus) {

    }

    @Override
    public Project findById(Long id) throws ServiceException {
        try {
            return projectDao.readBuId(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Project> findAll() throws ServiceException {
        try {
            return projectDao.readAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Project> findAllBuCustomerId(Long id) throws ServiceException {
        try {
            return projectDao.readAllBuManagerId(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
