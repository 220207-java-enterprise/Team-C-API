package com.revature.erm.util;

import com.revature.erm.config.AppConfig;
import com.revature.erm.servlets.AuthServlet;
import com.revature.erm.servlets.ReimbursementServlet;
import com.revature.erm.servlets.UserServlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.ConfigurableWebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoaderListener implements ServletContextListener {

    private static Logger logger = LogManager.getLogger(ContextLoaderListener.class);
    // Spring ApplicationContext (IoC container)
    ApplicationContext appContext;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.debug("Spinning up foundations project web application");

        appContext = new AnnotationConfigApplicationContext(AppConfig.class);

        UserServlet userServlet = appContext.getBean(UserServlet.class);
        AuthServlet authServlet = appContext.getBean(AuthServlet.class);
        ReimbursementServlet reimbursementServlet = appContext.getBean(ReimbursementServlet.class);

        ServletContext context = sce.getServletContext();
        context.addServlet("UserServlet", userServlet).addMapping("/users/*");
        context.addServlet("ReimbursementServlet", reimbursementServlet).addMapping("/reimbursements/*");
        context.addServlet("AuthServlet", authServlet).addMapping("/auth");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.debug("Closing down foundations project web application");
    }

}
