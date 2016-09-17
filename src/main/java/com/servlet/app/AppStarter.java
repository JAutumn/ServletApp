package com.servlet.app;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.servlet.app.services.FileService;
import com.servlet.app.services.UserService;

public class AppStarter implements ServletContextListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppStarter.class);
    private static String USERS_FILE;
    private static String FILE_ATTRIBUTE_DELIMITER;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        BasicConfigurator.configure();
        LOGGER.info("App start");
        ServletContext servletContext = servletContextEvent.getServletContext();
        initParams(servletContext);
        initUserStorage(servletContext);
    }

    private void initParams(ServletContext servletContext) {
        LOGGER.info("init params");
        USERS_FILE  = servletContext.getInitParameter("userFile");
        FILE_ATTRIBUTE_DELIMITER = servletContext.getInitParameter("fileAttributeDelimiter");
    }

    private void initUserStorage(ServletContext servletContext) {
        LOGGER.info("init user storage");
        String filePath = getClass().getClassLoader().getResource(USERS_FILE).getPath();
        UserService userService = new UserService(FileService.getUsersFromFile(filePath, FILE_ATTRIBUTE_DELIMITER));
        servletContext.setAttribute("userService", userService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        LOGGER.info("App close");
    }
}
