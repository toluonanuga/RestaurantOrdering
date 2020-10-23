/*
 * Class Description: A bridge between JSPs and the Services: This Servlet allows Customers to de-activate their accounts.
 */
package servlets;

import brokers.AdminBroker;
import brokers.CustomerBroker;
import com.main.actor.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Class Description: A bridge between JSPs and the Services: This Servlet allows Customers to de-activate their accounts.
 *
 * @author Shawn Kaldenbach
 */
public class DeleteCustomerServlet extends HttpServlet {

    
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
            throws ServletException, IOException 
    {
         String customerEmail =  request.getParameter("deleteEmail");
         CustomerBroker cb = new CustomerBroker();
         
         boolean checker = cb.deleteCustomerByEmail(customerEmail);
         if (checker)
         {         
             HttpSession session = request.getSession();
             session.invalidate();
             getServletContext().getRequestDispatcher("/WEB-INF/homepage.jsp").forward(request, response);
             
         }else
         {
             response.sendRedirect("edit");
         }

    }
// </editor-fold>

}
