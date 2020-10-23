/*
 * Class Description: A bridge between JSPs and the Services: This Servlet is for when an Administrator edits an Account.
 */
package servlets;

import brokers.AdminBroker;
import com.google.gson.Gson;
import com.main.actor.Actor;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.AdminService;

/**
 * Class Description: A bridge between JSPs and the Services: This Servlet is for when an Administrator edits an Account.
 *
 * @author cuong
 */
@WebServlet(name = "EditActorServlet", urlPatterns = {"/EditActorServlet"})
public class EditActorServlet extends HttpServlet {


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Gson gson = new Gson();
            AdminBroker ab = new AdminBroker();
            List actorList = ab.getAll();
            response.setContentType("application/json");
            response.getWriter().write(gson.toJson(actorList));
        } catch (SQLException ex) {
            Logger.getLogger(EditActorServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
    }
        
            

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        AdminService as = new AdminService();
        
        if (action.equals("1"))
        {
            
            String recievedActor = request.getParameter("sendActor");
            Actor actor = null;
            Gson gson = new Gson();
            actor = gson.fromJson(recievedActor, Actor.class);
            boolean updateCheck = as.editActor(actor);
            response.setContentType("application/json");
            response.getWriter().write(gson.toJson(updateCheck));
        }else if (action.equals("2"))
        {
            as.deleteActor(Integer.parseInt(request.getParameter("deleteActor")));
        }
    }// </editor-fold>

}
