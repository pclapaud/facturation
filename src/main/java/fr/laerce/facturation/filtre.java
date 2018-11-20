package fr.laerce.facturation;

import javax.servlet.*;
import javax.servlet.ServletContextEvent;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public  class filtre implements Filter {




    @Override
    public void init(FilterConfig filterConfig ) throws ServletException {


    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            ServletContext context = servletRequest.getServletContext();
            servletResponse.setContentType("text/html");

            Connection conn = (Connection)context.getAttribute("conn");
            if (conn==null || !conn.isValid(1)){
                conn = reconnection(context);
                context.setAttribute("conn",conn);
            }
            if (conn==null ) {
                    PrintWriter out = servletResponse.getWriter();
                    out.write("<html><body><div id='servletResponse' style='text-align: center;'>");
                    out.write("<h2>ECHEC</h2>");
                    out.write("Impossible de se connecter la BDD");
                    out.write("</div></body></html>");
                    out.close();
            }
            else {
                    HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
                    filterChain.doFilter(servletRequest,servletResponse);
                }
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void destroy() {

    }
    public Connection reconnection(ServletContext context){
        String user = context.getInitParameter("user");
        String password = context.getInitParameter("password");
        String driver = context.getInitParameter("driver");

        try {
            Class.forName("org.postgresql.Driver");
            Properties props = new Properties();
            props.setProperty("user", user);
            props.setProperty("password", password);
            Connection conn = DriverManager.getConnection(driver, props);
            return conn;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }








}
