package com.epam.amazingServlet.service.projectServices.work;

import com.epam.amazingServlet.domain.project.Work;
import com.epam.amazingServlet.service.exceptions.ServiceException;

import java.util.List;

public interface WorkService {
    void updateDescription(String description) throws ServiceException;

    void updateNumberOfSpecialists(Integer NumberOfSpecialists) throws ServiceException;

    void updateQualification(Integer NumberOfSpecialists) throws ServiceException;

    void updateFinishStatus(boolean finishStatus) throws ServiceException;

    List<Work> getWorksBuTaskId(Long id) throws ServiceException;

    List<Work> getWorksForDeveloper(Long id) throws ServiceException;

    Work findById(Long id) throws ServiceException;

    Long addWork(Work work) throws ServiceException;

    void updateWork(Work work) throws ServiceException;
}
