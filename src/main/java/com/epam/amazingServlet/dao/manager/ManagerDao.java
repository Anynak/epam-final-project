package com.epam.amazingServlet.dao.manager;

import com.epam.amazingServlet.dao.Dao;
import com.epam.amazingServlet.dao.DaoException;
import com.epam.amazingServlet.domain.user.Manager;

import java.util.List;

public interface ManagerDao extends Dao<Manager, Long> {
    Manager getManagerByEmail(String email) throws DaoException;
    List<Manager> readAll() throws DaoException;
}
