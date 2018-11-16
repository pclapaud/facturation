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
            }
        }
        String laVue = "DetailsClient.jsp";
        getServletConfig().getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/"+laVue).forward(httpServletRequest, httpServletResponse);



    }


}
