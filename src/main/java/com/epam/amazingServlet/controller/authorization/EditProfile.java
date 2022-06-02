package com.epam.amazingServlet.controller.authorization;

import com.epam.amazingServlet.controller.Controller;
import com.epam.amazingServlet.domain.user.*;
import com.epam.amazingServlet.service.ServiceFactoryImpl;
import com.epam.amazingServlet.service.exceptions.ServiceException;
import com.epam.amazingServlet.service.ServiceFactory;
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

@WebServlet("/editProfile")
public class EditProfile extends Controller {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");
        ServiceFactory serviceFactory = new ServiceFactoryImpl();
        if (user.getPosition() == Position.DEVELOPER) {
            DeveloperService developerService = serviceFactory.newDeveloperServiceInstance();
            Developer developer;
            try {
                developer = developerService.findById(user.getId());
            } catch (ServiceException e) {
                throw new ServletException(e);
            }
            req.setAttribute("user", developer);
            RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF\\views\\editProfileDeveloper.jsp");
            dispatcher.forward(req, resp);
        }
        if (user.getPosition() == Position.MANAGER) {
            ManagerService managerService = serviceFactory.newManagerServiceInstance();
            Manager manager;
            try {
                manager = managerService.findById(user.getId());
            } catch (ServiceException e) {
                throw new ServletException(e);
            }
            req.setAttribute("user", manager);
            RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF\\views\\editProfileManager.jsp");
            dispatcher.forward(req, resp);
        }
        if (user.getPosition() == Position.CUSTOMER) {
            CustomerService customerService = serviceFactory.newCustomerServiceInstance();
            Customer customer;
            try {
                customer = customerService.findById(user.getId());
            } catch (ServiceException e) {
                throw new ServletException(e);
            }
            RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF\\views\\editProfileCustomer.jsp");
            dispatcher.forward(req, resp);
            req.setAttribute("user", customer);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        checkFormData(req);
        try {
            updateUser(req);
        } catch (ServiceException e) {
            throw new ServletException(e);
        }
        resp.sendRedirect(req.getContextPath() + "/");
    }

    private User getUserFromRequest(HttpServletRequest req) {
        User user = (User) req.getSession().getAttribute("user");
        user.setFirstName(req.getParameter("firstName"));
        user.setLastName(req.getParameter("lastName"));
        user.setEmail(req.getParameter("email"));
        user.setPassHash(Utils.passToHash(req.getParameter("psw")));
        return user;
    }

    private void checkFormData(HttpServletRequest req) {
        User user = getUserFromRequest(req);
        Position position = Position.valueOf(req.getParameter("position"));
        if (user.getPosition() != position) {
            throw new IllegalArgumentException("Unacceptable user");
        }
        String firstName = req.getParameter("firstName");
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("Unacceptable firstname");
        }

        String lastName = req.getParameter("lastName");
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("Unacceptable lastName");
        }

        String email = req.getParameter("email");
        if (email == null || !Utils.validateEmail(email)) {
            throw new IllegalArgumentException("Unacceptable email");
        }
        String psw = req.getParameter("psw");
        String pswRepeat = req.getParameter("psw-repeat");
        if (psw == null || psw.isBlank()) {
            throw new IllegalArgumentException("Unacceptable password");
        }
        if (!psw.equals(pswRepeat)) {
            throw new IllegalArgumentException("Passwords do not match");
        }
    }

    private void updateUser(HttpServletRequest req) throws ServiceException {
        Position position = Position.valueOf(req.getParameter("position"));
        User user = getUserFromRequest(req);
        ServiceFactory serviceFactory = new ServiceFactoryImpl();
        if (position == Position.DEVELOPER) {
            Developer developer = new Developer(user);
            developer.setQualification(Qualification.valueOf(req.getParameter("qualification")));
            DeveloperService developerService = serviceFactory.newDeveloperServiceInstance();
            try {
                developerService.update(developer);
            } catch (ServiceException e) {
                throw new ServiceException(e);
            }
        } else if (position == Position.MANAGER) {
            ManagerService managerService = serviceFactory.newManagerServiceInstance();
            try {
                managerService.update(new Manager(user));
            } catch (ServiceException e) {
                throw new ServiceException(e);
            }
        } else if (position == Position.CUSTOMER) {
            CustomerService customerService = serviceFactory.newCustomerServiceInstance();
            try {
                customerService.update(new Customer(user));
            } catch (ServiceException e) {
                throw new ServiceException(e);
            }
        }
    }

}
