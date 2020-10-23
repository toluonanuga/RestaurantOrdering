/*
 * Class Description: A bridge between JSPs and the Services: This Servlet is for handling Account Creation for Customers.
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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.AdminService;
import services.CustomerService;

/**
 * Class Description: A bridge between JSPs and the Services: This Servlet is for handling Account Creation for Customers.
 *
 * @author cuong
 */

public class RegistryServlet extends HttpServlet {


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
        getServletContext().getRequestDispatcher("/WEB-INF/registry.jsp").forward(request, response);
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
         actorPhoneNumber = actorPhoneNumber.replaceAll("-", "");
         String actorStreet = request.getParameter("recieverStreet");
         String actorPostalCode = request.getParameter("recieverPostalCode");
         //delivery info
         String deliEmail =  request.getParameter("deliveryEmail");
         String deliBuilding = request.getParameter("deliverybuilding");
         System.out.print("Building " + deliBuilding);
         String deliUnit = request.getParameter("deliveryunit");
         String deliPhoneNumber = request.getParameter("deliveryPhonenumber");
         deliPhoneNumber = deliPhoneNumber.replaceAll("-", "");
         String deliStreet = request.getParameter("deliveryStreet");
         String deliPostalCode = request.getParameter("deliveryPostalCode");
         String deliNote    = request.getParameter("deliveryNote");
         
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
         AdminBroker ab = new AdminBroker();
         CustomerBroker cb = new CustomerBroker();
         int rows = ab.insertActor(customer);
         
         Actor actor = ab.getActorbyEmail(actorEmail);
         
     
         
     //   boolean check = cb.addDeliveryInfo(actor.getId(), deliBuilding, deliUnit, deliStreet, deliPostalCode, "Calgary", "AB", deliNote, deliEmail, deliPhoneNumber );
   //if(check == true && rows > 0)
   //     {
   //         
    //        request.setAttribute("message", "Successful Create Account");
    //    }
    //    else
    //    {
   //         request.setAttribute("message", "Please refill the form");
    //    }
          
        getServletContext().getRequestDispatcher("/WEB-INF/registry.jsp").forward(request, response);
         

    }

}


