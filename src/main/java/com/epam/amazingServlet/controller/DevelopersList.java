package com.epam.amazingServlet.controller;

import com.epam.amazingServlet.service.exceptions.ServiceException;
import com.epam.amazingServlet.service.ServiceFactory;
import com.epam.amazingServlet.service.userServices.developer.DeveloperService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/developers")
public class DevelopersList extends Controller {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServiceFactory serviceFactory = getServiceFactory();
        DeveloperService developerService = serviceFactory.newDeveloperServiceInstance();
        try {
            req.setAttribute("developers", developerService.findAll());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF\\views\\developers.jsp");
        dispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }
}
