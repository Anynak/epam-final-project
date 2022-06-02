package com.epam.amazingServlet.service.projectServices.project;

import com.epam.amazingServlet.dao.DaoException;
import com.epam.amazingServlet.domain.project.Project;
import com.epam.amazingServlet.domain.project.ProjectStatus;
import com.epam.amazingServlet.domain.project.TechnicalTask;
import com.epam.amazingServlet.service.exceptions.ServiceException;

import java.util.List;

public interface ProjectService {
    Long createProject(Project project) throws ServiceException;

    void setStatus(ProjectStatus projectStatus);

    Project findById(Long id) throws ServiceException;

    List<Project> findAll() throws ServiceException;

    List<Project> findAllBuCustomerId(Long id) throws ServiceException;

}
