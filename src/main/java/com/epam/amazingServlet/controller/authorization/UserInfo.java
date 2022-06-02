package com.epam.amazingServlet.controller.authorization;

import com.epam.amazingServlet.controller.Controller;
import com.epam.amazingServlet.domain.user.User;
import com.epam.amazingServlet.service.exceptions.ServiceException;
import com.epam.amazingServlet.service.ServiceFactory;
import com.epam.amazingServlet.service.userServices.user.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user")
public class UserInfo extends Controller {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //TODO страничку профиля
        if (req.getParameter("userId") == null) {
            resp.sendError(400);
        }

        ServiceFactory serviceFactory = getServiceFactory();
        UserService userService = serviceFactory.newUserServiceInstance();
        Long userId = Long.parseLong(req.getParameter("userId"));
        User user = null;
        try {
            user = userService.findById(userId);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        if (user == null) {
            resp.sendError(400);
        }
        req.setAttribute("user", user);
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF\\views\\mainPage.jsp");
        dispatcher.forward(req, resp);

        resp.sendError(400);

    }
}
