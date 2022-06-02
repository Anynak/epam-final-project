package com.epam.amazingServlet.service.projectServices.technicalTask;

import com.epam.amazingServlet.dao.technicalTask.TechnicalTaskDao;
import com.epam.amazingServlet.domain.project.TechnicalTask;
import com.epam.amazingServlet.service.exceptions.ServiceException;

import java.util.List;

public interface TechnicalTaskService {

    Long addTechnicalTask(TechnicalTask technicalTask) throws ServiceException;

    void removeTechnicalTask(Long id) throws ServiceException;

    TechnicalTask findById(Long id) throws ServiceException;

    List<TechnicalTask> findAllBuCustomerId(Long id) throws ServiceException;

    List<TechnicalTask> findAll() throws ServiceException;

    List<TechnicalTask> findUnprocessed() throws ServiceException;
}
