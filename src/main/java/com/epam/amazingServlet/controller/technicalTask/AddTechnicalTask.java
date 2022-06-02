package com.epam.amazingServlet.controller.technicalTask;

import com.epam.amazingServlet.controller.Controller;
import com.epam.amazingServlet.domain.project.TechnicalTask;
import com.epam.amazingServlet.domain.user.Customer;
import com.epam.amazingServlet.domain.user.User;
import com.epam.amazingServlet.service.ServiceFactory;
import com.epam.amazingServlet.service.exceptions.ServiceException;
import com.epam.amazingServlet.service.projectServices.technicalTask.TechnicalTaskService;
import com.epam.amazingServlet.service.userServices.customer.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addTechnicalTask")
public class AddTechnicalTask extends Controller {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        checkFormData(req);
        User user = (User) (req.getSession().getAttribute("user"));
        ServiceFactory serviceFactory = getServiceFactory();
        CustomerService customerService = serviceFactory.newCustomerServiceInstance();
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
        resp.sendRedirect(req.getHeader("referer"));

    }

    private void checkFormData(HttpServletRequest req) {
        String taskTitle = req.getParameter("taskTitle");
        if (taskTitle == null || taskTitle.isBlank()) {
            throw new IllegalArgumentException("wrong task Title");
        }
    }
}

