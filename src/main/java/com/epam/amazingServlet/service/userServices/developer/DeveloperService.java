package com.epam.amazingServlet.service.userServices.developer;

import com.epam.amazingServlet.domain.user.Developer;
import com.epam.amazingServlet.service.exceptions.ServiceException;
import com.epam.amazingServlet.service.userServices.BaseUserService;

import java.util.List;

public interface DeveloperService extends BaseUserService<Developer> {

    void makeDevelopersBusy(List<String> devIds, Long projectId) throws ServiceException;

    List<Developer> getNotBusy() throws ServiceException;

    List<Developer> getDevelopersInProject(Long projectId) throws ServiceException;
}
