/*
 * Class Description: A bridge between JSPs and the Services: This Servlet is for Managers getting All Previous Orders upon first loading the page.
 */
package servlets;

import brokers.AdminBroker;
import com.main.menu.Order;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.EmployeeService;
import services.ManagerService;

/**
 * Class Description: A bridge between JSPs and the Services: This Servlet is for Managers getting All Previous Orders upon first loading the page.
 *
 * @author cuong
 */
public class ManagerServlet extends HttpServlet {

    private final EmployeeService es = new EmployeeService();
    private final ManagerService ms = new ManagerService();
    private final String myPhone = "+15875784301";
    

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
        AdminBroker ab = new AdminBroker();
        List<Order> orders = null;
        orders = ab.getAllPastOrder();
        request.setAttribute("orderList", orders);
         getServletContext().getRequestDispatcher("/WEB-INF/manager.jsp").forward(request, response);
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
        String mybutton = request.getParameter("mybutton");
        System.out.println("mybutton: " + mybutton);
//        switch(mybutton) 
//        {
//            case "confirm":
//                if(es.confirmOrder(myPhone))
//                System.out.println("Confirm Worked!");
//                break;
//            case "complete":
//                if(es.completeOrder(myPhone))
//                System.out.println("Complete Worked!");
//                break;
//            case "voidOrder":
//                if(ms.voidOrder(null, myPhone, null, "Testing purpose"))
//                System.out.println("Void Order Worked!");
//                break;
//            default:
//                break;
//        }
        
       
        getServletContext().getRequestDispatcher("/WEB-INF/manager.jsp").forward(request, response);
    }
// </editor-fold>

}
