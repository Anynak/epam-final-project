package com.epam.amazingServlet.dao.user;

import com.epam.amazingServlet.dao.Dao;
import com.epam.amazingServlet.dao.DaoException;
import com.epam.amazingServlet.domain.user.User;

import java.util.List;

public interface UserDao extends Dao<User, Long>  {
    List<User> readAll() throws DaoException;
    User getUserByEmail(String email) throws DaoException;

}
