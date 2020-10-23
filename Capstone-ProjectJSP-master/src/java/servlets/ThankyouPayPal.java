/*
 * Class Description: A bridge between JSPs and the Services: This Servlet is for creating a new Order after Paypal processing is complete.
 */
package servlets;

import com.main.actor.Actor;
import com.main.delivery.Delivery;
import com.main.menu.Item;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.CustomerService;

/**
 * Class Description: A bridge between JSPs and the Services: This Servlet is for creating a new Order after Paypal processing is complete.
 *
 * @author Chris
 */
public class ThankyouPayPal extends HttpServlet {


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
        CustomerService cs = new CustomerService();
        try{
        Delivery d = (Delivery)session.getAttribute("delivery_info");        
        String dEmail = d.getRecieverEmail();
        String dBuilding = d.getBuilding();
        String dUnit = d.getUnit();
        String dPostal = d.getDeliveryPostalCode();
        String dStreet = d.getDeliveryStreet();
        String dPhone = d.getRecieverPhonenumber();
        String method = (String)session.getAttribute("pickUpDelivery");
        String dNote = d.getDeliveryNote();
        double total = (Double)session.getAttribute("total");
        ArrayList<Item> itemList = new ArrayList<Item>();
        itemList = (ArrayList<Item>)session.getAttribute("cartItems");
        
        int actorId = 0;
        if (session.getAttribute("loggedActor") == null || session.getAttribute("loggedActor").equals(""))
        {
            actorId = 2;
        }
        else 
        {
            Actor actor = (Actor)session.getAttribute("loggedActor");
            actorId = actor.getId();
        }
        //This will be overriden when the employee recieves order anyways...
        String date2 = "2019-12-13";
        java.sql.Date duedate = null;
        duedate = java.sql.Date.valueOf(date2);
        System.out.println("actorID: " + actorId);
        System.out.println("dBuilding: " + dBuilding);
        System.out.println("dUnit: " + dUnit);
        System.out.println("dStreet: " + dStreet);
        System.out.println("dPostal: " + dPostal);
        System.out.println("dnote: " + dNote);
        System.out.println("dEmail: " + dEmail);
        System.out.println("total: " + total);
        System.out.println("Metod: " + method);
        System.out.println("ITEM LIST: ");
                for(Item i : itemList)
                {
                    System.out.println(i.getItemName());
                }
        
        
        
         cs.createOrder(actorId, dBuilding, dUnit, dStreet, dPostal, "Calgary", "AB", dNote, dEmail, dPhone, duedate, total, "unconfirmed", "PayPal", method, itemList);
            getServletContext().getRequestDispatcher("/WEB-INF/thankyouPayPal.jsp").forward(request, response);

        }
        catch(Exception e)  //catch is mostly just if a user tries to navigate to this page without first going through the proper checkout flow. 
        {
         getServletContext().getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);         
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
    }// </editor-fold>

}
