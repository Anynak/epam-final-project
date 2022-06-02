package com.epam.amazingServlet.controller.project;

import com.epam.amazingServlet.controller.Controller;
import com.epam.amazingServlet.domain.project.Project;
import com.epam.amazingServlet.domain.project.ProjectStatus;
import com.epam.amazingServlet.domain.project.TechnicalTask;
import com.epam.amazingServlet.domain.project.Work;
import com.epam.amazingServlet.domain.user.Developer;
import com.epam.amazingServlet.domain.user.User;
import com.epam.amazingServlet.service.ServiceFactory;
import com.epam.amazingServlet.service.ServiceFactoryImpl;
import com.epam.amazingServlet.service.exceptions.ServiceException;
import com.epam.amazingServlet.service.projectServices.project.ProjectService;
import com.epam.amazingServlet.service.projectServices.technicalTask.TechnicalTaskService;
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

@WebServlet("/setProject")
public class SetProject extends Controller {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        List<TechnicalTask> technicalTasks;
        try {
            technicalTasks = getUnprocessedTasks();
        } catch (ServiceException e) {
            throw new ServletException(e);
        }
        req.setAttribute("technicalTasks", technicalTasks);
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF\\views\\createProject.jsp");
        dispatcher.forward(req, resp);


    }

    private List<TechnicalTask> getUnprocessedTasks() throws ServiceException {
        ServiceFactory serviceFactory = new ServiceFactoryImpl();
        TechnicalTaskService technicalTaskService = serviceFactory.newTechnicalTaskServiceInstance();
        List<TechnicalTask> technicalTasks;
        technicalTasks = technicalTaskService.findUnprocessed();
        return technicalTasks;
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        checkFormData(req);
        createProject(getWorkFromRequest(req));
        resp.sendRedirect(req.getContextPath() + "/projectList");
    }

    private void createProject(Project project) throws ServletException {
        ServiceFactory serviceFactory = new ServiceFactoryImpl();
        ProjectService projectService = serviceFactory.newProjectServiceInstance();
        try {
            projectService.createProject(project);
        } catch (ServiceException e) {
            throw new ServletException(e);
        }
    }



    private Project getWorkFromRequest(HttpServletRequest req) throws ServletException {
        User user = (User) req.getSession().getAttribute("user");
        Long managerId = user.getId();
        String projectName = req.getParameter("projectName");
        ProjectStatus projectStatus = ProjectStatus.valueOf(req.getParameter("projectStatus"));
        Long technicalTaskId = Long.parseLong(req.getParameter("technicalTaskId"));

        ServiceFactory serviceFactory = new ServiceFactoryImpl();
        TechnicalTaskService technicalTaskService = serviceFactory.newTechnicalTaskServiceInstance();
        TechnicalTask technicalTask;
        try {
            technicalTask = technicalTaskService.findById(technicalTaskId);
        } catch (ServiceException e) {
            throw new ServletException(e);
        }
        Project project = new Project();
        project.setProjectName(projectName);
        project.setProjectStatus(projectStatus);
        project.setManagerId(managerId);
        project.setTechnicalTask(technicalTask);
        System.out.println(project);
        return project;

    }

    private void checkFormData(HttpServletRequest req) {

        String projectName = req.getParameter("projectName");
        if (projectName == null || projectName.isBlank()) {
            throw new IllegalArgumentException("projectName cant be empty");
        }

        String technicalTaskId = req.getParameter("technicalTaskId");
        if (technicalTaskId == null || !Utils.isNumber(technicalTaskId)) {
            throw new IllegalArgumentException("technical taskId must be selected");
        }

        try {
            String projectStatus = req.getParameter("projectStatus");
            ProjectStatus.valueOf(projectStatus);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unacceptable projectStatus");
        }
    }
}
