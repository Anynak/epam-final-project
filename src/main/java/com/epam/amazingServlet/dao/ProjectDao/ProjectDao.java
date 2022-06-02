package com.epam.amazingServlet.dao.ProjectDao;

import com.epam.amazingServlet.dao.DaoException;
import com.epam.amazingServlet.domain.project.Project;
import com.epam.amazingServlet.domain.project.TechnicalTask;

import java.util.List;

public interface ProjectDao {
    Long create(Project project) throws DaoException;
    List<Project> readAll() throws DaoException;
    Project readBuId(Long id) throws DaoException;

    List<Project> readAllBuManagerId(Long id) throws DaoException;

}
