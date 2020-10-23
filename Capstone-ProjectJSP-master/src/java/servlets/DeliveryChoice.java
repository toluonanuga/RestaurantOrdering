/*
 * Class Description: A bridge between JSPs and the Services: This Servlet verifies whether a Customer wants delivery or pickup
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Class Description: A bridge between JSPs and the Services: This Servlet verifies whether a Customer wants delivery or pickup
 *
 * @author Chris
 */
public class DeliveryChoice extends HttpServlet {

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
        
        System.out.println("TESSSSSSSSST");
        getServletContext().getRequestDispatcher("/WEB-INF/deliverychoice.jsp").forward(request, response);
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
               HttpSession session = request.getSession();

               
        if (request.getParameter("takeout") != null) {
            session.setAttribute("pickUpDelivery", "takeout");
              getServletContext().getRequestDispatcher("/WEB-INF/checkout_takeout.jsp").forward(request, response);   
        }
        else
        {
            session.setAttribute("pickUpDelivery", "delivery");
              getServletContext().getRequestDispatcher("/WEB-INF/checkout.jsp").forward(request, response);   
        }
       
    }
// </editor-fold>

}
