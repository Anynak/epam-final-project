package com.epam.amazingServlet.controller.work;

import com.epam.amazingServlet.controller.Controller;
import com.epam.amazingServlet.domain.project.Work;
import com.epam.amazingServlet.domain.user.User;
import com.epam.amazingServlet.service.ServiceFactory;
import com.epam.amazingServlet.service.exceptions.ServiceException;
import com.epam.amazingServlet.service.projectServices.work.WorkService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/currentWorks")
public class CurrentWorks extends Controller {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        ServiceFactory serviceFactory = getServiceFactory();
        WorkService workService = serviceFactory.newWorkServiceInstance();
        List<Work> currentWorks;
        try {
            currentWorks = workService.getWorksForDeveloper(user.getId());
        } catch (ServiceException e) {
            throw new ServletException(e);
        }
        req.setAttribute("currentWorks", currentWorks);
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF\\views\\currentWorks.jsp");
        dispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

}
