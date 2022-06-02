package com.epam.amazingServlet.controller;

import com.epam.amazingServlet.domain.user.User;
import com.epam.amazingServlet.service.ServiceFactory;
import com.epam.amazingServlet.service.exceptions.ServiceException;
import com.epam.amazingServlet.service.userServices.user.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/test")
public class Test extends Controller{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF\\views\\test.jsp");
        dispatcher.forward(req, resp);



    }
}
