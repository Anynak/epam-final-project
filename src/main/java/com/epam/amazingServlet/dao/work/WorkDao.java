package com.epam.amazingServlet.dao.work;

import com.epam.amazingServlet.dao.Dao;
import com.epam.amazingServlet.dao.DaoException;
import com.epam.amazingServlet.domain.project.Work;

import java.util.List;

public interface WorkDao extends Dao<Work, Long> {
    List<Work> readAllBuTechnicalTaskId(Long id) throws DaoException;
    List<Work> readFitWorksBuDeveloperId(Long id) throws DaoException;
    void updateDescription(String description)throws DaoException;
    void updateNumberOfSpecialists(Integer NumberOfSpecialists)throws DaoException;
    void updateQualification(Integer NumberOfSpecialists)throws DaoException;
    void updateFinishStatus(boolean finishStatus)throws DaoException;
}
