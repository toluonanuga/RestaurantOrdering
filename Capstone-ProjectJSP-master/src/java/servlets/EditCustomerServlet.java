/*
 * Class Description: A bridge between JSPs and the Services: This Servlet is for when a Customer edits their own Account.
 */
package servlets;

import brokers.AdminBroker;
import brokers.CustomerBroker;
import com.main.actor.Actor;
import com.main.actor.Customer;
import com.main.delivery.Delivery;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Class Description: A bridge between JSPs and the Services: This Servlet is for when an Administrator edits an Account.
 *
 * @author Shawn Kaldenbach
 */
public class EditCustomerServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditCustomerServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditCustomerServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
            throws ServletException, IOException 
    {
        HttpSession session = request.getSession();
        
        String email = (String) session.getAttribute("actorEmail");
        
        AdminBroker ab = new AdminBroker();
        CustomerBroker cb = new CustomerBroker();
        Actor currActor = ab.getActorbyEmail(email);
        Delivery deliveryInfo = cb.getDeliveryByID(currActor.getId());
        
        
        
        request.setAttribute("firstName", currActor.getFirstName());
        request.setAttribute("lastName", currActor.getLastName());
        request.setAttribute("email", currActor.getEmail());
        request.setAttribute("phoneNumber", currActor.getPhoneNumber());
        request.setAttribute("buildNo", currActor.getHouseNumber());
        request.setAttribute("unitNo", currActor.getUnitNumber());
        request.setAttribute("street", currActor.getStreet());
        request.setAttribute("postal", currActor.getPostalCode());
        request.setAttribute("deliveryEmail", deliveryInfo.getRecieverEmail());
        request.setAttribute("deliveryPhoneNumber", deliveryInfo.getRecieverPhonenumber());
        request.setAttribute("deliveryBuildNo", deliveryInfo.getBuilding());
        request.setAttribute("deliveryUnitNo", deliveryInfo.getUnit());
        request.setAttribute("deliveryStreet", deliveryInfo.getDeliveryStreet());
        request.setAttribute("deliveryPostal", deliveryInfo.getDeliveryPostalCode());
        request.setAttribute("deliveryNote", deliveryInfo.getDeliveryNote());
        
        getServletContext().getRequestDispatcher("/WEB-INF/editAccount.jsp").forward(request, response);
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
        
        
        //actor info
         String actorFirstName =  request.getParameter("recieverFirstname");
         String actorLastName =  request.getParameter("recieverLastname");
         String actorEmail =  request.getParameter("recieverEmail");
         String password = request.getParameter("password");
         String actorBuilding = request.getParameter("building");
         String actorUnit = request.getParameter("unit");
         String actorPhoneNumber = request.getParameter("recieverPhonenumber");
         String actorStreet = request.getParameter("recieverStreet");
         String actorPostalCode = request.getParameter("recieverPostalCode");
         //delivery info
         String deliEmail =  request.getParameter("deliveryEmail");
         String deliBuilding = request.getParameter("deliverybuilding");
         String deliUnit = request.getParameter("deliveryunit");
         String deliPhoneNumber = request.getParameter("deliveryPhonenumber");
         String deliStreet = request.getParameter("deliveryStreet");
         String deliPostalCode = request.getParameter("deliveryPostalCode");
         String deliNote = request.getParameter("deliveryNote");
         
         Customer customer = new Customer();
         customer.setPassword(password);
         customer.setFirstName(actorFirstName);
         customer.setLastName(actorLastName);
         customer.setEmail(actorEmail);
         customer.setHouseNumber(actorBuilding);
         customer.setUnitNumber(actorUnit);
         customer.setPhoneNumber(actorPhoneNumber);
         customer.setStreet(actorStreet);
         customer.setPostalCode(actorPostalCode);
         customer.setProvince("AB");
         customer.setCity("Calgary");
         customer.setRole("c");
         customer.setState(true);
         Delivery deliveryInfo = new Delivery();
         deliveryInfo.setBuilding(deliBuilding);
         deliveryInfo.setDeliveryCity("Calgary");
         deliveryInfo.setDeliveryNote(deliNote);
         deliveryInfo.setDeliveryPostalCode(deliPostalCode);
         deliveryInfo.setDeliveryProvince("AB");
         deliveryInfo.setDeliveryStreet(deliStreet);
         deliveryInfo.setRecieverEmail(deliEmail);
         deliveryInfo.setRecieverPhonenumber(deliPhoneNumber);
         deliveryInfo.setUnit(deliUnit);
         
         
         AdminBroker ab = new AdminBroker();
         CustomerBroker cb = new CustomerBroker();
         Actor tempActor = ab.getActorbyEmail(actorEmail);
         
         customer.setId(tempActor.getId());
         
         boolean successfulUpdate = cb.updateCustomerInfo(customer, deliveryInfo);
         
         if (successfulUpdate)
         {
            response.sendRedirect("menu");
         }else
         {
            response.sendRedirect("edit");
         }
         
         
         
    }
// </editor-fold>

}
