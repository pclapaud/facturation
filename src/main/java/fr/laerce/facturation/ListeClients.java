package fr.laerce.facturation;

import fr.laerce.facturation.model.Client;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

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
    Template listeClients;
    Template DetailsClient;
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        try {
            conn = (Connection)getServletContext().getAttribute("conn");
            Statement req = conn.createStatement();
            HttpSession session = httpServletRequest.getSession();
            Map<String,Object> datas = new HashMap<>();
            String path = httpServletRequest.getPathInfo().substring(1);


            if(path.contains("index.jsp")) {
                Template index = (Template)getServletContext().getAttribute("templateindex");
                index.process(datas, httpServletResponse.getWriter());
            }
            if(path.contains("clients.html")){
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

                session.setAttribute("clients", clients);


                datas.put("clients",clients);
                listeClients = (Template)getServletContext().getAttribute("templatelisteClients");
                listeClients.process(datas, httpServletResponse.getWriter());
            }
            if (path.contains("DetailsClient")){

                String name = httpServletRequest.getParameter("nom");
                ArrayList<Client> clients = (ArrayList<Client>)session.getAttribute("clients");



                for (Client cl:clients
                ) {
                    if(cl.getNom().equals(name)){
                        datas.put("client",cl);
                        session.setAttribute("original",cl);
                    }
                }


                    DetailsClient = (Template)getServletContext().getAttribute("templateDetailsClient");
                    DetailsClient.process(datas, httpServletResponse.getWriter());

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }

    }
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        try {
            HttpSession session = httpServletRequest.getSession();
            conn = (Connection)getServletContext().getAttribute("conn");
            Statement req = conn.createStatement();
            String path = httpServletRequest.getPathInfo().substring(1);


            if(path.contains("clients.html")){
                String numString = null;

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
            }
            if (path.contains("DetailsClient")) {
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

        }

        } catch (SQLException e) {
            e.printStackTrace();
        }}



    }

