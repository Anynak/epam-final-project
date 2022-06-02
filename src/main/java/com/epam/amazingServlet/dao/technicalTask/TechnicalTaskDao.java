package com.epam.amazingServlet.dao.technicalTask;

import com.epam.amazingServlet.dao.Dao;
import com.epam.amazingServlet.dao.DaoException;
import com.epam.amazingServlet.domain.user.Developer;
import com.epam.amazingServlet.domain.project.TechnicalTask;

import java.util.List;

public interface TechnicalTaskDao extends Dao<TechnicalTask, Long> {
    List<TechnicalTask> readAllBuCustomerId(Long id) throws DaoException;
    List<TechnicalTask> readAll() throws DaoException;
    List<TechnicalTask> readUnprocessedTasks() throws DaoException;
}
