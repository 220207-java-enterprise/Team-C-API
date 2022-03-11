package com.revature.erm.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.erm.daos.ReimbursementDAO;
import com.revature.erm.daos.UserDAO;
import com.revature.erm.services.ReimbursementService;
import com.revature.erm.services.TokenService;
import com.revature.erm.services.UserService;
import com.revature.erm.daos.UserDAO;
import com.revature.erm.services.UserService;
import com.revature.erm.servlets.AuthServlet;
import com.revature.erm.servlets.ReimbursementServlet;
import com.revature.erm.servlets.UserServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoaderListener implements ServletContextListener {

    private static Logger logger = LogManager.getLogger(ContextLoaderListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        logger.debug("Initializing p2_foundations_project_teamc web application");

        System.out.println("Spinning up foundations project web application");

        ObjectMapper mapper = new ObjectMapper();
        JwtConfig jwtConfig = new JwtConfig(); //Added jwtconfig service Khari
        TokenService tokenService = new TokenService(jwtConfig); //Added token service Khari

        UserDAO userDAO = new UserDAO();
        UserService userService = new UserService(userDAO);
        UserServlet userServlet = new UserServlet(tokenService, userService, mapper);

        ReimbursementDAO reimbursementDAO = new ReimbursementDAO();
        ReimbursementService reimbursementService = new ReimbursementService(reimbursementDAO);
        ReimbursementServlet reimbursementServlet = new ReimbursementServlet(tokenService, reimbursementService, mapper);

        AuthServlet authServlet = new AuthServlet(userService, mapper, tokenService); //Added token service Khari
        //AuthServlet authServlet1 = new AuthServlet(reimbursementService, mapper);
        // TODO instantiate the ReimbursementServlet and wire all of its dependencies

        // TODO register the ReimbursementServlet to handle requests to /reimbursements
        // Programmatic Servlet Registration
        ServletContext context = sce.getServletContext();
        context.addServlet("UserServlet", userServlet).addMapping("/users/*");
        context.addServlet("ReimbursementServlet", reimbursementServlet).addMapping("/reimbursements/*");
        context.addServlet("AuthServlet", authServlet).addMapping("/auth");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.debug("Shutting down p2_foundations_project_teamc web application");
        System.out.println("Closing down foundations project web application");
    }

}
