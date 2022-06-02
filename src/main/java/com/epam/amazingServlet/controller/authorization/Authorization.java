package com.epam.amazingServlet.controller.authorization;

import com.epam.amazingServlet.controller.Controller;
import com.epam.amazingServlet.controller.filters.security.SecurityUtils;
import com.epam.amazingServlet.domain.user.User;
import com.epam.amazingServlet.service.exceptions.ServiceException;
import com.epam.amazingServlet.service.ServiceFactory;
import com.epam.amazingServlet.service.userServices.user.UserService;
import com.epam.amazingServlet.utils.Utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/auth")
public class Authorization extends Controller {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (SecurityUtils.isAuthorized(req)) {
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF\\views\\authorization.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        checkAuthData(req);
        User user;
        try {
            user = getUserByEmail(req.getParameter("email"));
        } catch (ServiceException e) {
            throw new ServletException(e);
        }
        checkUserPassword(user, req.getParameter("psw"));
        req.getSession().setAttribute("user", user);

        resp.sendRedirect(req.getContextPath() + "/");

    }

    private void checkUserPassword(User user, String pass) {
        String pswHash = Utils.passToHash(pass);
        if (!user.getPassHash().equals(pswHash)) {
            throw new IllegalArgumentException("Wrong password");
        }
    }

    private void checkAuthData(HttpServletRequest req) {
        String email = req.getParameter("email");
        if (email == null || !Utils.validateEmail(email)) {
            throw new IllegalArgumentException("Wrong email");
        }

        String psw = req.getParameter("psw");
        if (psw == null || psw.isBlank()) {
            throw new IllegalArgumentException("Unacceptable password");
        }
    }

    private User getUserByEmail(String email) throws ServiceException {
        ServiceFactory serviceFactory = getServiceFactory();
        UserService userService = serviceFactory.newUserServiceInstance();
        User user;
        try {
            user = userService.findByEmail(email);
        } catch (ServiceException e) {
            throw new ServiceException(e);
        }
        if (user.getPassHash() == null) {
            throw new IllegalArgumentException("Wrong email");
        } else return user;
    }
}
