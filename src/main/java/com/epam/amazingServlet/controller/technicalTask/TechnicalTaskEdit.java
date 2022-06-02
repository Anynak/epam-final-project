package com.epam.amazingServlet.controller.technicalTask;

import com.epam.amazingServlet.controller.Controller;
import com.epam.amazingServlet.domain.project.TechnicalTask;
import com.epam.amazingServlet.domain.project.Work;
import com.epam.amazingServlet.domain.user.Customer;
import com.epam.amazingServlet.domain.user.User;
import com.epam.amazingServlet.service.ServiceFactory;
import com.epam.amazingServlet.service.exceptions.ServiceException;
import com.epam.amazingServlet.service.projectServices.technicalTask.TechnicalTaskService;
import com.epam.amazingServlet.service.projectServices.work.WorkService;
import com.epam.amazingServlet.service.userServices.customer.CustomerService;
import com.epam.amazingServlet.utils.Utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/technicalTask")
public class TechnicalTaskEdit extends Controller {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        checkFormData(req);

        Long taskId = Long.parseLong(req.getParameter("id"));
        TechnicalTask technicalTask = null;
        try {
            technicalTask = getTask(taskId);
        } catch (ServiceException e) {
            throw new ServletException(e);
        }

        User user = (User) (req.getSession().getAttribute("user"));

        //закрывает доступ к чужим таскам
        if (!technicalTask.getCustomer().getId().equals(user.getId())) {
            resp.sendError(403);
        }

        List<Work> works;
        try {
            works = getWorksBuTaskId(taskId);
        } catch (ServiceException e) {
            throw new ServletException(e);
        }

        req.setAttribute("technicalTask", technicalTask);
        req.setAttribute("works", works);
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF\\views\\technicalTask.jsp");
        dispatcher.forward(req, resp);

    }

    private void checkFormData(HttpServletRequest req) {
        if (req.getParameter("id") == null || !Utils.isNumber(req.getParameter("id"))) {
            throw new IllegalArgumentException("task doesn't exist");
        }
    }

    private TechnicalTask getTask(Long id) throws ServiceException {
        ServiceFactory serviceFactory = getServiceFactory();
        TechnicalTaskService technicalTaskService = serviceFactory.newTechnicalTaskServiceInstance();
        return technicalTaskService.findById(id);

    }

    private List<Work> getWorksBuTaskId(Long taskId) throws ServiceException {
        ServiceFactory serviceFactory = getServiceFactory();
        WorkService workService = serviceFactory.newWorkServiceInstance();
        return workService.getWorksBuTaskId(taskId);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServiceFactory serviceFactory = getServiceFactory();

        CustomerService customerService = serviceFactory.newCustomerServiceInstance();
        User user = (User) (req.getSession().getAttribute("user"));
        Customer customer;
        try {
            customer = customerService.findById(user.getId());
        } catch (ServiceException e) {
            throw new ServletException(e);
        }

        TechnicalTaskService technicalTaskService = serviceFactory.newTechnicalTaskServiceInstance();
        String taskTitle = req.getParameter("taskTitle");
        TechnicalTask technicalTask = new TechnicalTask();
        technicalTask.setTitle(taskTitle);
        technicalTask.setCustomer(customer);
        try {
            technicalTaskService.addTechnicalTask(technicalTask);
        } catch (ServiceException e) {
            throw new ServletException(e);
        }
        resp.sendRedirect(req.getContextPath() + "/technicalTasks");

    }
}
