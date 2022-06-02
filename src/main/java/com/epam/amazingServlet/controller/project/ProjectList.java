package com.epam.amazingServlet.controller.project;

import com.epam.amazingServlet.controller.Controller;
import com.epam.amazingServlet.domain.project.Project;
import com.epam.amazingServlet.domain.user.User;
import com.epam.amazingServlet.service.ServiceFactory;
import com.epam.amazingServlet.service.ServiceFactoryImpl;
import com.epam.amazingServlet.service.exceptions.ServiceException;
import com.epam.amazingServlet.service.projectServices.project.ProjectService;
import com.epam.amazingServlet.service.userServices.developer.DeveloperService;
import com.epam.amazingServlet.utils.Utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.List;

@WebServlet("/projectList")
public class ProjectList extends Controller {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) (req.getSession().getAttribute("user"));

        List<Project> projects;
        ServiceFactory serviceFactory = new ServiceFactoryImpl();
        ProjectService projectService = serviceFactory.newProjectServiceInstance();
        try {
            projects = projectService.findAllBuCustomerId(user.getId());
        } catch (ServiceException e) {
            throw new ServletException(e);
        }

        req.setAttribute("projects", projects);
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF\\views\\projectList.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        checkFormData(req);

        ServiceFactory serviceFactory = new ServiceFactoryImpl();
        DeveloperService developerService = serviceFactory.newDeveloperServiceInstance();

        List<String> devIds = List.of(req.getParameterValues("developerId"));
        Long projectId = Long.parseLong(req.getParameter("projectId"));
        try {
            developerService.makeDevelopersBusy(devIds, projectId);
        } catch (ServiceException e) {
            throw new ServletException(e);
        }
        resp.sendRedirect(req.getHeader("referer"));
    }

    private void checkFormData(HttpServletRequest req) {
        String projectId = req.getParameter("projectId");
        if (projectId == null || !Utils.isNumber(projectId)) {
            throw new IllegalArgumentException("wrong project id");
        }
        if (req.getParameterValues("developerId") == null || req.getParameterValues("developerId").length == 0) {
            throw new IllegalArgumentException("developer id is empty");
        }
    }
}
