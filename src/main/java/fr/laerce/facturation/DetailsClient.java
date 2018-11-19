package fr.laerce.facturation;

import fr.laerce.facturation.model.Client;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
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
            conn = (Connection)getServletContext().getAttribute("conn");
            Statement req = conn.createStatement();
            String name = httpServletRequest.getParameter("nom");
            String prenom = httpServletRequest.getParameter("prenom");
            String loc = httpServletRequest.getParameter("loc");
            String pays = httpServletRequest.getParameter("pays");
            String mod = httpServletRequest.getParameter("button");
            ArrayList<Client> clients = (ArrayList<Client>)session.getAttribute("clients");
            Client original = (Client)session.getAttribute("original");
            if(mod.equals("mod")) {

                PreparedStatement updt = (PreparedStatement)getServletContext().getAttribute("updt");
                updt.setString(1,name);
                updt.setString(2,prenom);
                updt.setString(3,loc);
                updt.setString(4,pays);
                updt.setString(5,original.getNom());
                updt.setString(6,original.getPnom());
                updt.setString(7,original.getLoc());
                updt.setString(8,original.getPays());

                updt.executeUpdate();


            }
            else {
                PreparedStatement dele = (PreparedStatement)getServletContext().getAttribute("dele");
                dele.setString(1,original.getNom());
                dele.setString(2,original.getPnom());
                dele.setString(3,original.getLoc());
                dele.setString(4,original.getPays());
                dele.executeUpdate();
            }
            httpServletResponse.sendRedirect("/clients.html");
        } catch (SQLException e) {
            e.printStackTrace();
        }





    }


    }



