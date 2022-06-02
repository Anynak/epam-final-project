package com.epam.amazingServlet.service;

import com.epam.amazingServlet.controller.Controller;
import com.epam.amazingServlet.service.projectServices.bill.BillService;
import com.epam.amazingServlet.service.projectServices.project.ProjectService;
import com.epam.amazingServlet.service.projectServices.technicalTask.TechnicalTaskService;
import com.epam.amazingServlet.service.projectServices.work.WorkService;
import com.epam.amazingServlet.service.userServices.customer.CustomerService;
import com.epam.amazingServlet.service.userServices.user.UserService;
import com.epam.amazingServlet.service.userServices.developer.DeveloperService;
import com.epam.amazingServlet.service.userServices.manager.ManagerService;

public interface ServiceFactory {
    UserService newUserServiceInstance();
    DeveloperService newDeveloperServiceInstance();
    ManagerService newManagerServiceInstance();
    CustomerService newCustomerServiceInstance();
    TechnicalTaskService newTechnicalTaskServiceInstance();
    WorkService newWorkServiceInstance();
    ProjectService newProjectServiceInstance();
    BillService newBillServiceInInstance();
}
