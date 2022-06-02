package com.epam.amazingServlet.controller.project;

import com.epam.amazingServlet.controller.Controller;
import com.epam.amazingServlet.domain.project.Project;
import com.epam.amazingServlet.domain.project.Work;
import com.epam.amazingServlet.domain.user.Developer;
import com.epam.amazingServlet.service.ServiceFactory;
import com.epam.amazingServlet.service.ServiceFactoryImpl;
import com.epam.amazingServlet.service.exceptions.ServiceException;
import com.epam.amazingServlet.service.projectServices.project.ProjectService;
import com.epam.amazingServlet.service.projectServices.work.WorkService;
import com.epam.amazingServlet.service.userServices.developer.DeveloperService;
import com.epam.amazingServlet.utils.Utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/editProject")
public class EditProject extends Controller {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        checkReqData(req);
        Long projectId = Long.parseLong(req.getParameter("id"));
        List<Developer> developers;
        try {
            developers = getFreeDevelopers();
        } catch (ServiceException e) {
            throw new ServletException(e);
        }
        req.setAttribute("freeDevelopers", developers);

        Project project;
        try {
            project = getProject(projectId);
        } catch (ServiceException e) {
            throw new ServletException(e);
        }
        req.setAttribute("project", project);

        List<Work> works;
        try {
            works = getWorksInTask(project.getTechnicalTask().getId());
        } catch (ServiceException e) {
            throw new ServletException(e);
        }
        req.setAttribute("works", works);

        List<Developer> involvedDevelopers;
        try {
            involvedDevelopers = getInvolvedDevelopers(projectId);
        } catch (ServiceException e) {
            throw new ServletException(e);
        }

        req.setAttribute("involvedDevelopers", involvedDevelopers);
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF\\views\\editProject.jsp");
        dispatcher.forward(req, resp);
    }

    private void checkReqData(HttpServletRequest req){
        String projectId = req.getParameter("id");
        if(projectId==null || !Utils.isNumber(projectId)){
            throw new IllegalArgumentException("project id incorrect");
        }
    }

    private List<Developer> getFreeDevelopers() throws ServiceException {
        ServiceFactory serviceFactory = new ServiceFactoryImpl();
        DeveloperService developerService = serviceFactory.newDeveloperServiceInstance();
        return developerService.getNotBusy();
    }

    private List<Developer> getInvolvedDevelopers(Long id) throws ServiceException {
        ServiceFactory serviceFactory = new ServiceFactoryImpl();
        DeveloperService developerService = serviceFactory.newDeveloperServiceInstance();
        return developerService.getDevelopersInProject(id);
    }

    private List<Work> getWorksInTask(Long id) throws ServiceException {
        ServiceFactory serviceFactory = new ServiceFactoryImpl();
        WorkService workService = serviceFactory.newWorkServiceInstance();
        return workService.getWorksBuTaskId(id);
    }

    private Project getProject(Long id) throws ServiceException {
        ServiceFactory serviceFactory = new ServiceFactoryImpl();
        ProjectService projectService = serviceFactory.newProjectServiceInstance();
        return projectService.findById(id);
    }
}
