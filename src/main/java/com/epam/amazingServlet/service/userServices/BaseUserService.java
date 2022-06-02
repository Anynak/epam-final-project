package com.epam.amazingServlet.service.userServices;

import com.epam.amazingServlet.domain.user.User;
import com.epam.amazingServlet.service.exceptions.ServiceException;

import java.util.List;

public interface BaseUserService<T extends User> {
    T findById(Long id) throws ServiceException;

    T findByEmail(String email) throws ServiceException;

    List<T> findAll() throws ServiceException;

    void update(T user) throws ServiceException;

    void save(T user) throws ServiceException;

    void delete(Long id) throws ServiceException;

    Long create(T user) throws ServiceException;
}
