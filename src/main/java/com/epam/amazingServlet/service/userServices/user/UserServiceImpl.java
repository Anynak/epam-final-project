package com.epam.amazingServlet.service.userServices.user;

import com.epam.amazingServlet.dao.DaoException;
import com.epam.amazingServlet.dao.user.UserDao;
import com.epam.amazingServlet.domain.user.User;
import com.epam.amazingServlet.service.exceptions.ServiceException;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User findById(Long id) throws ServiceException {
        try {
            return userDao.read(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User findByEmail(String email) throws ServiceException {
        try {
            return userDao.getUserByEmail(email);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> findAll() throws ServiceException {
        //UserDao userDao = new UserDaoImpl(MySqlConnectionPool.getInstance());
        try {
            userDao.readAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return null;
    }

    @Override
    public void update(User user) throws ServiceException {
        try {
            userDao.update(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(User developer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Long id) throws ServiceException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Long create(User developer) throws ServiceException {
        throw new UnsupportedOperationException();
    }

}
