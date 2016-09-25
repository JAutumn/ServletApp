package com.servlet.app.common;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.servlet.app.common.db.ConnectionProvider;

@WebListener
public class AppStarter implements ServletContextListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppStarter.class);
    private static String CTX;
    private static String PUBLIC_PATH;
    private static String RESOURCE_PATH;
    private static String JSP_DIR;

    public static String getPublicPath() {
        return CTX + PUBLIC_PATH;
    }

    public static String getResourcePath() {
        return CTX + RESOURCE_PATH;
    }

    public static String getJspDir() {
        return JSP_DIR;
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        BasicConfigurator.configure();
        LOGGER.info("App start");
        ServletContext servletContext = servletContextEvent.getServletContext();
        initParams(servletContext);
        initContextVariables(servletContext);
        try {
            Class.forName(ConnectionProvider.class.getName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void initParams(ServletContext servletContext) {
        LOGGER.info("init params");
        CTX = servletContext.getContextPath();
        PUBLIC_PATH = servletContext.getInitParameter("PUBLIC_PATH");
        RESOURCE_PATH = servletContext.getInitParameter("RESOURCE_PATH");
        JSP_DIR = servletContext.getInitParameter("JSP_DIR");
    }

    private void initContextVariables(ServletContext servletContext) {
        servletContext.setAttribute("pagesPath", getPublicPath());
        servletContext.setAttribute("resPath", getResourcePath());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        LOGGER.info("App close");
    }
}
