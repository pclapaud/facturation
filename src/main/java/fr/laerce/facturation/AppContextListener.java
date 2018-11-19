package fr.laerce.facturation;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.sql.SQLException;



import java.sql.*;



@WebListener
public class AppContextListener implements ServletContextListener {
    Connection conn;

    public void contextInitialized(ServletContextEvent servletContextEvent) {


        Context ctx = null;
        try {
            ctx = new InitialContext();
            Context xmlContext = (Context) ctx.lookup("java:comp/env");
            DataSource ds = (DataSource) xmlContext.lookup("jdbc/connection");
            conn = ds.getConnection();
            servletContextEvent.getServletContext().setAttribute("conn",conn);


        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}