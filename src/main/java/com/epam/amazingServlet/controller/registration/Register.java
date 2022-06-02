package com.epam.amazingServlet.controller.registration;

import com.epam.amazingServlet.controller.Controller;
import com.epam.amazingServlet.controller.filters.security.SecurityUtils;
import com.epam.amazingServlet.domain.user.*;
import com.epam.amazingServlet.service.ServiceFactory;
import com.epam.amazingServlet.service.exceptions.ServiceException;
import com.epam.amazingServlet.service.userServices.customer.CustomerService;
import com.epam.amazingServlet.service.userServices.developer.DeveloperService;
import com.epam.amazingServlet.service.userServices.manager.ManagerService;
import com.epam.amazingServlet.utils.Utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class Register extends Controller {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (SecurityUtils.isAuthorized(req)) {
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF\\views\\register.jsp");
        dispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        checkRegFormData(req);
        User user = getUserFromReq(req);
        if (user.getPosition() == Position.DEVELOPER) {
            Developer developer = new Developer(user);
            regDeveloper(developer);
        }
        if (user.getPosition() == Position.MANAGER) {
            Manager manager = new Manager(user);
            System.out.println(manager);
            regManager(manager);

        }
        if (user.getPosition() == Position.CUSTOMER) {
            Customer customer = new Customer(user);
            regCustomer(customer);
        }
        resp.sendRedirect(req.getContextPath() + "/");
    }


    private void regDeveloper(Developer developer) throws ServletException {

        ServiceFactory serviceFactory = getServiceFactory();
        DeveloperService developerService = serviceFactory.newDeveloperServiceInstance();
        try {
            developerService.save(developer);
        } catch (ServiceException e) {
            throw new ServletException(e);
        }
    }

    private void regManager(Manager manager) throws ServletException {

        ServiceFactory serviceFactory = getServiceFactory();
        ManagerService managerService = serviceFactory.newManagerServiceInstance();

        try {
            managerService.save(manager);
        } catch (ServiceException e) {
            throw new ServletException(e);
        }
    }

    private void regCustomer(Customer customer) throws ServletException {

        ServiceFactory serviceFactory = getServiceFactory();
        CustomerService customerService = serviceFactory.newCustomerServiceInstance();

        try {
            customerService.save(customer);
        } catch (ServiceException e) {
            throw new ServletException(e);
        }
    }

    private User getUserFromReq(HttpServletRequest req) {

        User user = new User();

        String firstName = req.getParameter("firstName");
        user.setFirstName(firstName);

        String lastName = req.getParameter("lastName");
        user.setLastName(lastName);

        String email = req.getParameter("email");
        user.setEmail(email);

        String psw = req.getParameter("psw");
        String pswHash = Utils.passToHash(psw);
        user.setPassHash(pswHash);

        String position = req.getParameter("position");
        user.setPosition(Position.valueOf(position));
        return user;

    }

    private void checkRegFormData(HttpServletRequest req) {
        try {
            String position = req.getParameter("position");
            Position.valueOf(position);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unacceptable position");
        }

        String firstName = req.getParameter("firstName");
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("Unacceptable firstname");
        }

        String lastName = req.getParameter("lastName");
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("Unacceptable lastName");
        }

        String psw = req.getParameter("psw");
        String pswRepeat = req.getParameter("psw-repeat");
        if (psw == null || psw.isBlank()) {
            throw new IllegalArgumentException("Unacceptable password");
        }
        if (!psw.equals(pswRepeat)) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        String email = req.getParameter("email");
        if (email == null || !Utils.validateEmail(email)) {
            throw new IllegalArgumentException("Unacceptable email");
        }
    }
}
