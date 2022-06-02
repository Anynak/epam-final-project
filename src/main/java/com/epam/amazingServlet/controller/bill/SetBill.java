package com.epam.amazingServlet.controller.bill;

import com.epam.amazingServlet.controller.Controller;
import com.epam.amazingServlet.domain.project.Bill;
import com.epam.amazingServlet.domain.project.Project;
import com.epam.amazingServlet.domain.user.Developer;
import com.epam.amazingServlet.domain.user.User;
import com.epam.amazingServlet.service.ServiceFactory;
import com.epam.amazingServlet.service.ServiceFactoryImpl;
import com.epam.amazingServlet.service.exceptions.ServiceException;
import com.epam.amazingServlet.service.projectServices.bill.BillService;
import com.epam.amazingServlet.service.projectServices.project.ProjectService;

import com.epam.amazingServlet.service.userServices.developer.DeveloperService;
import com.epam.amazingServlet.utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/setBill")
public class SetBill extends Controller {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        checkForm(req);

        ServiceFactory serviceFactory = new ServiceFactoryImpl();
        User user = (User) req.getSession().getAttribute("user");
        long hours = Long.parseLong(req.getParameter("hours"));
        DeveloperService developerService = serviceFactory.newDeveloperServiceInstance();

        try {
            Developer developer = developerService.findById(user.getId());
            ProjectService projectService = serviceFactory.newProjectServiceInstance();
            Project project = projectService.findById(developer.getProjectId());
            BillService billService = serviceFactory.newBillServiceInInstance();
            BigDecimal amount = developer.getQualification().getSalaryPerHourUSD().multiply(new BigDecimal(hours));
            if (project.getBill() == null) {
                Bill bill = new Bill();
                bill.setTotalAmount(amount);
                billService.createBillForProject(bill, project.getId());
            } else {
                billService.increaseSumByProjectId(project.getBill().getId(), amount);
            }
        } catch (ServiceException e) {
            throw new ServletException(e);
        }
        resp.sendRedirect(req.getContextPath() + "/");
    }

    void checkForm(HttpServletRequest req) {

        String hours = req.getParameter("hours");
        if (hours == null || !Utils.isNumber(hours)) {
            throw new IllegalArgumentException("hours must be number");
        }
        if (Long.parseLong(hours) <= 0) {
            throw new IllegalArgumentException("hours must be > 0");
        }
    }
}
