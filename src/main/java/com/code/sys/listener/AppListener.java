package com.code.sys.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 */
@WebListener
public class AppListener implements ServletContextListener {

    /**
     * 在Web应用程序启动时被调用
     * @param arg0
     */
    @Override
    public void contextInitialized(ServletContextEvent arg0) {

    }

    /**
     * 在Web应用程序关闭时被调用
     * @param arg0
     */
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        //取到ServletContext
        ServletContext context=arg0.getServletContext();
        context.setAttribute("bjpowernode", context.getContextPath());
    }

}
