package com.epam.amazingServlet.controller;

import com.epam.amazingServlet.service.ServiceFactory;
import com.epam.amazingServlet.service.ServiceFactoryImpl;

import javax.servlet.http.HttpServlet;

public abstract class Controller extends HttpServlet {

    public ServiceFactory getServiceFactory() {
        return new ServiceFactoryImpl();
    }
}
