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
import java.util.Properties;


@WebListener
public class AppContextListener implements ServletContextListener  {
    Connection conn;

    public void contextInitialized(ServletContextEvent servletContextEvent) {


        Context ctx = null;
        try {
            String user = servletContextEvent.getServletContext().getInitParameter("user");
            String password = servletContextEvent.getServletContext().getInitParameter("password");
            String driver = servletContextEvent.getServletContext().getInitParameter("driver");

            Class.forName("org.postgresql.Driver");
            Properties props = new Properties();
            props.setProperty("user", user);
            props.setProperty("password", password);
            conn = DriverManager.getConnection(driver, props);



            servletContextEvent.getServletContext().setAttribute("conn",conn);
            String reqe = "UPDATE clients SET clt_nom= ? , clt_pnom= ? , clt_loc= ? , clt_pays= ? where clt_nom= ? and clt_pnom= ?and clt_loc= ? and clt_pays= ? ";
            PreparedStatement updt = conn.prepareStatement(reqe);
            servletContextEvent.getServletContext().setAttribute("updt",updt);
            String reqe2 = "DELETE FROM clients where clt_nom= ? and clt_pnom= ?and clt_loc= ? and clt_pays= ? ";
            PreparedStatement dele = conn.prepareStatement(reqe2);
            servletContextEvent.getServletContext().setAttribute("dele",dele);



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("pas de driver sql");
            System.exit(4);
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