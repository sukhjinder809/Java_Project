/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finalproject.listeners;

import com.finalproject.data.DbMgr;
import java.sql.SQLException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author Jaskaran singh
 */
public class MyServletContextListener implements ServletContextListener {
 public MyServletContextListener() {
        // TODO Auto-generated constructor stub
    }
    @Override
    public void contextInitialized(ServletContextEvent sce) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    ServletContext ctx = sce.getServletContext();
         String user = ctx.getInitParameter("user");
         String password = ctx.getInitParameter("password");
         String url = ctx.getInitParameter("url");
         String className = ctx.getInitParameter("classname");
         //create db Manager
         DbMgr myDbMgr = null;
         try {
        	 myDbMgr = new DbMgr(user, password, url, className);
              
		}
         catch (ClassNotFoundException | SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
        ctx.setAttribute("DbMgr", myDbMgr);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
