/*
 * Class Description: A bridge between JSPs and the Services: This Servlet is for Managers getting All Previous Orders dynamically.
 */
package servlets;

import brokers.AdminBroker;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class Description: A bridge between JSPs and the Services: This Servlet is for Managers getting All Previous Orders dynamically.
 *
 * @author cuong
 */
@WebServlet(name = "ManagerOrderServlet", urlPatterns = {"/ManagerOrderServlet"})
public class ManagerOrderServlet extends HttpServlet {



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
           Gson gson = new Gson();
           AdminBroker ab = new AdminBroker();
           List orderLists = ab.getAllPastOrder();
           response.setContentType("application/json");
           response.getWriter().write(gson.toJson(orderLists));
            
      
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
       
    }
// </editor-fold>

}
