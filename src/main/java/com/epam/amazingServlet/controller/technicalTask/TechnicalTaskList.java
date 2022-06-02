package com.epam.amazingServlet.controller.technicalTask;

import com.epam.amazingServlet.controller.Controller;
import com.epam.amazingServlet.domain.project.TechnicalTask;
import com.epam.amazingServlet.domain.user.User;
import com.epam.amazingServlet.service.ServiceFactory;
import com.epam.amazingServlet.service.exceptions.ServiceException;
import com.epam.amazingServlet.service.projectServices.technicalTask.TechnicalTaskService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/technicalTaskList")
public class TechnicalTaskList extends Controller {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServiceFactory serviceFactory = getServiceFactory();
        User user = (User) (req.getSession().getAttribute("user"));
        try {
            TechnicalTaskService technicalTaskService = serviceFactory.newTechnicalTaskServiceInstance();
            List<TechnicalTask> technicalTasks = technicalTaskService.findAllBuCustomerId(user.getId());
            req.setAttribute("technicalTasks", technicalTasks);
        } catch (ServiceException e) {
            throw new ServletException(e);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF\\views\\technicalTaskList.jsp");
        dispatcher.forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
