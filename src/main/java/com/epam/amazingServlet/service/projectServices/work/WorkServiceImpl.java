package com.epam.amazingServlet.service.projectServices.work;

import com.epam.amazingServlet.dao.DaoException;
import com.epam.amazingServlet.dao.work.WorkDao;
import com.epam.amazingServlet.domain.project.Work;
import com.epam.amazingServlet.service.exceptions.ServiceException;

import java.util.List;

public class WorkServiceImpl implements WorkService {
    private final WorkDao workDao;

    public WorkServiceImpl(WorkDao workDao) {
        this.workDao = workDao;
    }

    @Override
    public void updateDescription(String description) {

    }

    @Override
    public void updateNumberOfSpecialists(Integer NumberOfSpecialists) {

    }

    @Override
    public void updateQualification(Integer NumberOfSpecialists) {

    }

    @Override
    public void updateFinishStatus(boolean finishStatus) {

    }

    @Override
    public List<Work> getWorksBuTaskId(Long id) {
        try {
            return workDao.readAllBuTechnicalTaskId(id);
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Work> getWorksForDeveloper(Long id) throws ServiceException {
        try {
            return workDao.readFitWorksBuDeveloperId(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Work findById(Long id) throws ServiceException {
        try {
            return workDao.read(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Long addWork(Work work) throws ServiceException {
        try {
            return workDao.create(work);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateWork(Work work) throws ServiceException {
        try {
            workDao.update(work);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
