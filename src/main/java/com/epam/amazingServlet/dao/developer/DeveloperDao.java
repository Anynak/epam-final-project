package com.epam.amazingServlet.dao.developer;

import com.epam.amazingServlet.dao.Dao;
import com.epam.amazingServlet.dao.DaoException;
import com.epam.amazingServlet.domain.user.Developer;


import java.util.List;

public interface DeveloperDao extends Dao<Developer, Long> {
    Developer getDeveloperByEmail(String email) throws DaoException;
    List<Developer> readAll() throws DaoException;
    void setProjectId(List<String> devIds, Long projectId) throws DaoException;
    List<Developer> readNotBusy() throws DaoException;
    List<Developer> getDevelopersByProjectId(Long projectId) throws DaoException;
}
