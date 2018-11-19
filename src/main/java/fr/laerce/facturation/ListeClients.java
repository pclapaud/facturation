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
import javax.naming.*;
import javax.sql.*;
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

public class ListeClients extends HttpServlet {
    Connection conn;



    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {


        try {
            conn = (Connection)getServletContext().getAttribute("conn");
            Statement req = conn.createStatement();
            String query = "SELECT clt_num, clt_nom, clt_pnom, clt_loc, clt_pays FROM clients";
            ResultSet res = req.executeQuery(query);
            List<Client> clients = new ArrayList<Client>();
            while(res.next()){
                clients.add(new Client(res.getString("clt_num"),
                        res.getString("clt_nom"),
                        res.getString("clt_pnom"),
                        res.getString("clt_loc"),
                        res.getString("clt_pays")));
            }
            HttpSession session = httpServletRequest.getSession();
            session.setAttribute("clients", clients);
            httpServletRequest.setAttribute("clients", clients);

            String laVue = "clients.jsp";
            getServletConfig().getServletContext()
                    .getRequestDispatcher("/WEB-INF/jsp/"+laVue).forward(httpServletRequest, httpServletResponse);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession();
        try {
            String numString = null;
            conn = (Connection)getServletContext().getAttribute("conn");
            Statement req = conn.createStatement();
            String query = "SELECT MAX(clt_num)FROM clients";
            ResultSet res = req.executeQuery(query);
            while(res.next()) {
                Integer num = Integer.parseInt(res.getString(1).substring(1).trim()) + 1;
                numString = "C" + num;
            }
            
            String name = httpServletRequest.getParameter("nom");
            String prenom = httpServletRequest.getParameter("prenom");
            String loc = httpServletRequest.getParameter("loc");
            String pays = httpServletRequest.getParameter("pays");
            String reqe = "INSERT INTO clients VALUES ('"+numString+"','"+name+"', '"+prenom+"', '"+pays+"',' "+loc+"',null,null)";
            req.executeUpdate(reqe);

            httpServletResponse.sendRedirect("/clients.html");
        } catch (SQLException e) {
            e.printStackTrace();
        }}



    }

