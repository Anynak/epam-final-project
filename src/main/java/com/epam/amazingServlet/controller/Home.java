package com.epam.amazingServlet.controller;

import com.epam.amazingServlet.domain.user.Position;
import com.epam.amazingServlet.domain.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class Home extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");
        Position position = user.getPosition();
        if (position == Position.DEVELOPER) {
            resp.sendRedirect(req.getContextPath() + "/currentWorks");
        } else if (position == Position.MANAGER) {
            resp.sendRedirect(req.getContextPath() + "/projectList");
        } else if (position == Position.CUSTOMER) {
            resp.sendRedirect(req.getContextPath() + "/technicalTaskList");
        }

    }
}
