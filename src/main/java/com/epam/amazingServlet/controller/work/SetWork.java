package com.epam.amazingServlet.controller.work;


import com.epam.amazingServlet.controller.Controller;
import com.epam.amazingServlet.domain.project.Work;

import com.epam.amazingServlet.domain.user.Qualification;

import com.epam.amazingServlet.service.ServiceFactory;
import com.epam.amazingServlet.service.exceptions.ServiceException;

import com.epam.amazingServlet.service.projectServices.work.WorkService;
import com.epam.amazingServlet.utils.Utils;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/setWork")
public class SetWork extends Controller {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        checkFormData(req);
        Work work = getWorkFromRequest(req);

        String workId = req.getParameter("workId");
        if (workId == null) {
            saveWork(work);
        } else {
            if (Utils.isNumber(workId)) {
                Long id = Long.parseLong(req.getParameter("workId"));
                if (hasWork(id)) {
                    work.setId(Long.parseLong(req.getParameter("workId")));
                    editWork(work);
                } else {
                    throw new IllegalArgumentException("no work with id=" + id);
                }
            } else {
                throw new IllegalArgumentException("workId must be number");
            }
        }
        resp.sendRedirect(req.getContextPath() + "/technicalTask?id=" + work.getTechnicalTaskId());
    }

    private void checkFormData(HttpServletRequest req) {

        String description = req.getParameter("workDescription");
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("description cant be empty");
        }

        String numberOfSpecialists = req.getParameter("numberOfRequiredSpecialists");
        if (numberOfSpecialists == null || !Utils.isNumber(numberOfSpecialists)) {
            throw new IllegalArgumentException("number of specialists must be number");
        }

        try {
            String requiredQualification = req.getParameter("requiredQualification");
            Qualification.valueOf(requiredQualification);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unacceptable qualification");
        }

        String technicalTaskId = req.getParameter("technicalTaskId");
        if (technicalTaskId == null || !Utils.isNumber(technicalTaskId)) {
            throw new IllegalArgumentException("technicalTaskId must be number");
        }
    }

    private Work getWorkFromRequest(HttpServletRequest req) {
        String description = req.getParameter("workDescription");
        Integer numberOfSpecialists = Integer.valueOf(req.getParameter("numberOfRequiredSpecialists"));
        Qualification qualification = Qualification.valueOf(req.getParameter("requiredQualification"));
        Long technicalTaskId = Long.parseLong(req.getParameter("technicalTaskId"));
        Work work = new Work();
        work.setDescription(description);
        work.setNumberOfRequiredSpecialists(numberOfSpecialists);
        work.setRequiredQualification(qualification);
        work.setTechnicalTaskId(technicalTaskId);
        return work;
    }

    private void saveWork(Work work) throws ServletException {
        ServiceFactory serviceFactory = getServiceFactory();
        WorkService workService = serviceFactory.newWorkServiceInstance();
        try {
            workService.addWork(work);
        } catch (ServiceException e) {
            throw new ServletException(e);
        }
    }

    private void editWork(Work work) throws ServletException {
        ServiceFactory serviceFactory = getServiceFactory();
        WorkService workService = serviceFactory.newWorkServiceInstance();
        try {
            workService.updateWork(work);
        } catch (ServiceException e) {
            throw new ServletException(e);
        }
    }

    private boolean hasWork(Long workId) throws ServletException {
        ServiceFactory serviceFactory = getServiceFactory();
        WorkService workService = serviceFactory.newWorkServiceInstance();
        try {
            Work work = workService.findById(workId);
            return work != null;
        } catch (ServiceException e) {
            throw new ServletException(e);
        }
    }
}
