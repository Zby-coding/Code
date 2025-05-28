package com.zby.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

@javax.servlet.annotation.WebListener("/")
public class ContextLoaderListener implements ServletContextListener{

    public ContextLoaderListener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        /* This method is called when the servlet context is initialized(when the Web application is deployed). */
        System.out.println("application已被加载");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        /* This method is called when the servlet Context is undeployed or Application Server shuts down. */
        System.out.println("application已被销毁");

    }
}
