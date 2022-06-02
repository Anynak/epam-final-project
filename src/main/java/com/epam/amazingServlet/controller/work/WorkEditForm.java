package com.epam.amazingServlet.controller.work;

import com.epam.amazingServlet.controller.Controller;
import com.epam.amazingServlet.domain.project.Work;
import com.epam.amazingServlet.service.ServiceFactory;
import com.epam.amazingServlet.service.exceptions.ServiceException;
import com.epam.amazingServlet.service.projectServices.work.WorkService;
import com.epam.amazingServlet.utils.Utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/work")
public class WorkEditForm extends Controller {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (!Utils.isNumber(req.getParameter("Id"))) {
            throw new IllegalArgumentException();
        }
        Long workId = Long.parseLong(req.getParameter("Id"));
        ServiceFactory serviceFactory = getServiceFactory();
        try {
            WorkService workService = serviceFactory.newWorkServiceInstance();
            Work work = workService.findById(workId);
            req.setAttribute("work", work);
        } catch (ServiceException e) {
            throw new ServletException(e);
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF\\views\\workEdit.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

}
