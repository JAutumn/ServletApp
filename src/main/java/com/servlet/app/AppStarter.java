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
    private static String CTX;
    private static String PUBLIC_PATH;
    private static String RESOURCE_PATH;
    private static String JSP_DIRECTORY;

    public static String getPublicPath() {
        return CTX + PUBLIC_PATH;
    }

    public static String getResourcePath() {
        return CTX + RESOURCE_PATH;
    }

    public static String getJspDirectory() {
        return JSP_DIRECTORY;
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        BasicConfigurator.configure();
        LOGGER.info("App start");
        ServletContext servletContext = servletContextEvent.getServletContext();
        initParams(servletContext);
        initContextVariables(servletContext);
    }

    private void initParams(ServletContext servletContext) {
        LOGGER.info("init params");
        CTX = servletContext.getContextPath();
        PUBLIC_PATH = servletContext.getInitParameter("PUBLIC_PATH");
        RESOURCE_PATH = servletContext.getInitParameter("RESOURCE_PATH");
        JSP_DIRECTORY = servletContext.getInitParameter("JSP_DIRECTORY");
        USERS_FILE = servletContext.getInitParameter("USERS_FILE");
        FILE_ATTRIBUTE_DELIMITER = servletContext.getInitParameter("FILE_ATTRIBUTE_DELIMITER");
    }

    private void initContextVariables(ServletContext servletContext) {
        initUserStorage(servletContext);
        servletContext.setAttribute("pagesPath", getPublicPath());
        servletContext.setAttribute("resPath", getResourcePath());
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
        String filePath = getClass().getClassLoader().getResource(USERS_FILE).getPath();
        UserService userService = (UserService) servletContextEvent.getServletContext().getAttribute("userService");
        FileService.writeUsersToFile(filePath, FILE_ATTRIBUTE_DELIMITER, userService.getAll());
    }
}
