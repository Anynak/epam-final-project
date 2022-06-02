package com.epam.amazingServlet.service;

import com.epam.amazingServlet.dao.ProjectDao.ProjectDao;
import com.epam.amazingServlet.dao.ProjectDao.ProjectDaoImpl;
import com.epam.amazingServlet.dao.bill.BillDao;
import com.epam.amazingServlet.dao.bill.BillDaoImpl;
import com.epam.amazingServlet.dao.customer.CustomerDao;
import com.epam.amazingServlet.dao.customer.CustomerDaoImpl;
import com.epam.amazingServlet.dao.developer.DeveloperDao;
import com.epam.amazingServlet.dao.developer.DeveloperDaoImpl;
import com.epam.amazingServlet.dao.manager.ManagerDao;
import com.epam.amazingServlet.dao.manager.ManagerDaoImpl;
import com.epam.amazingServlet.dao.technicalTask.TechnicalTaskDao;
import com.epam.amazingServlet.dao.technicalTask.TechnicalTaskDaoImpl;
import com.epam.amazingServlet.dao.user.UserDao;
import com.epam.amazingServlet.dao.user.UserDaoImpl;
import com.epam.amazingServlet.dao.work.WorkDao;
import com.epam.amazingServlet.dao.work.WorkDaoImpl;
import com.epam.amazingServlet.service.projectServices.bill.BillService;
import com.epam.amazingServlet.service.projectServices.bill.BillServiceImpl;
import com.epam.amazingServlet.service.projectServices.project.ProjectService;
import com.epam.amazingServlet.service.projectServices.project.ProjectServiceImpl;
import com.epam.amazingServlet.service.projectServices.technicalTask.TechnicalTaskService;
import com.epam.amazingServlet.service.projectServices.technicalTask.TechnicalTaskServiceImpl;
import com.epam.amazingServlet.service.projectServices.work.WorkService;
import com.epam.amazingServlet.service.projectServices.work.WorkServiceImpl;
import com.epam.amazingServlet.service.userServices.customer.CustomerService;
import com.epam.amazingServlet.service.userServices.customer.CustomerServiceImpl;
import com.epam.amazingServlet.service.userServices.manager.ManagerServiceImpl;
import com.epam.amazingServlet.service.userServices.user.UserService;
import com.epam.amazingServlet.service.userServices.user.UserServiceImpl;
import com.epam.amazingServlet.service.userServices.developer.DeveloperService;
import com.epam.amazingServlet.service.userServices.developer.DeveloperServiceImpl;
import com.epam.amazingServlet.service.userServices.manager.ManagerService;
import com.epam.amazingServlet.sqlDataBase.MySqlConnectionPool;

public class ServiceFactoryImpl implements ServiceFactory {

    @Override
    public UserService newUserServiceInstance() {
        UserDao userDao = new UserDaoImpl(MySqlConnectionPool.getInstance());
        return new UserServiceImpl(userDao);
    }

    @Override
    public DeveloperService newDeveloperServiceInstance() {
        DeveloperDao developerDao = new DeveloperDaoImpl(MySqlConnectionPool.getInstance());
        return new DeveloperServiceImpl(developerDao);
    }

    @Override
    public ManagerService newManagerServiceInstance() {
        ManagerDao managerDao = new ManagerDaoImpl(MySqlConnectionPool.getInstance());
        return new ManagerServiceImpl(managerDao);

    }

    @Override
    public CustomerService newCustomerServiceInstance() {
        CustomerDao customerDao = new CustomerDaoImpl(MySqlConnectionPool.getInstance());
        return new CustomerServiceImpl(customerDao);

    }

    @Override
    public TechnicalTaskService newTechnicalTaskServiceInstance() {
        TechnicalTaskDao technicalTaskDao = new TechnicalTaskDaoImpl(MySqlConnectionPool.getInstance());
        return new TechnicalTaskServiceImpl(technicalTaskDao);
    }

    @Override
    public WorkService newWorkServiceInstance() {
        WorkDao workDao = new WorkDaoImpl(MySqlConnectionPool.getInstance());
        return new WorkServiceImpl(workDao);
    }

    @Override
    public ProjectService newProjectServiceInstance() {
        ProjectDao projectDao = new ProjectDaoImpl(MySqlConnectionPool.getInstance());
        return new ProjectServiceImpl(projectDao);
    }

    @Override
    public BillService newBillServiceInInstance() {
        BillDao billDao = new BillDaoImpl(MySqlConnectionPool.getInstance());
        return new BillServiceImpl(billDao);
    }
}
