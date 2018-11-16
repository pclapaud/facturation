package fr.laerce.facturation;

import fr.laerce.facturation.model.Client;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class DetailsClient extends HttpServlet {
    Connection conn;


    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession();
        String name = httpServletRequest.getParameter("nom");
        ArrayList<Client> clients = (ArrayList<Client>)session.getAttribute("clients");
        for (Client cl:clients
             ) {
           if(cl.getNom().equals(name)){
                httpServletRequest.setAttribute("client", cl);
               session.setAttribute("original",cl);
            }
        }

        String laVue = "DetailsClient.jsp";
        getServletConfig().getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/"+laVue).forward(httpServletRequest, httpServletResponse);



    }
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession();
        try {
            Statement req = conn.createStatement();
            String name = httpServletRequest.getParameter("nom");
            String prenom = httpServletRequest.getParameter("prenom");
            String loc = httpServletRequest.getParameter("loc");
            String pays = httpServletRequest.getParameter("pays");
            ArrayList<Client> clients = (ArrayList<Client>)session.getAttribute("clients");
            Client original = (Client)session.getAttribute("original");
            for (Client cl:clients
            ) {
                if(cl.getNom().equals(name)){
                    String reqe = "UPDATE clients SET clt_nom='"+name+"', clt_pnom='"+prenom+"', clt_loc='"+loc+"', clt_pays='"+pays+
                            "' where clt_nom='"+original.getNom()+"'and clt_pnom='"+original.getPnom()+"'and clt_loc='"+original.getLoc()+"'and clt_pays='"+original.getPays()+"'";
                    req.executeUpdate(reqe);
                }
            }
            httpServletResponse.sendRedirect("/clients.html");
        } catch (SQLException e) {
            e.printStackTrace();
        }





    }
    public void init() throws ServletException {
        super.init();
        try {
            String user = getInitParameter("user");
            String password = getInitParameter("password");
            String driver = getInitParameter("driver");

            Class.forName("org.postgresql.Driver");
            Properties props = new Properties();
            props.setProperty("user", user);
            props.setProperty("password", password);
            conn = DriverManager.getConnection(driver, props);
            //conn = DriverManager.getConnection("jdbc:postgresql://localhost/exemple", props);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new ServletException("Pas de Driver SQL");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Pas de connexion à la base");
        }

    }


}
