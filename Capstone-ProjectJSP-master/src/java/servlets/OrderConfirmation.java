/*
 * Class Description: A bridge between JSPs and the Services: This Servlet is for handling Paypal information upon confirmation.
 */
package servlets;

import com.main.actor.Actor;
import com.main.delivery.Delivery;
import com.main.menu.Item;
import com.paypal.base.rest.PayPalRESTException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import payment.OrderDetail;
import payment.PaymentServices;
import services.CustomerService;

/**
 * Class Description: A bridge between JSPs and the Services: This Servlet is for handling Paypal information upon confirmation.
 *
 * @author Chris
 */
public class OrderConfirmation extends HttpServlet {

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
          DecimalFormat df = new DecimalFormat("#.##");
          Delivery d = (Delivery)session.getAttribute("delivery_info");      
          String subTotalString =(String)session.getAttribute("subtotalString");
       //   subTotalString = df.format(subTotalString);
          
          double subtotal = Double.parseDouble(subTotalString);
          double tax = 0;
          
          int precision = 100;
          tax = subtotal * 0.05;
          tax = Math.floor(tax * precision +.5)/precision;
          double total = tax + subtotal;
          
          //subtotal = df.format(subtotal);
          
       
          
          
          subTotalString = String.format("%.2f", subtotal);
          String totalString =  String.format("%.2f", total);  
          String taxString = String.format("%.2f", tax);
          request.setAttribute("total", "Subtotal: $" + subTotalString + "<br>Tax: $" + taxString + "<br>Total: $" + totalString);
   
          
          request.setAttribute("deliveryName", d.getRecieverFirstname() + " " + d.getRecieverLastname() + "<br>" +
                   d.getRecieverPhonenumber() + "<br>" +  d.getRecieverEmail());
   
          if(d.getBuilding().equals("n/a"))
          {
              //request.setAttribute("deliveryAdd", "At: " + d.getRecieverPhonenumber() + ", " + );
          }
          else
          {
             request.setAttribute("deliveryAdd", "<tr><td colspan=\"2\"><p>" + d.getUnit() + " " + d.getBuilding() + " " + d.getDeliveryStreet() 
               + " " + d.getDeliveryPostalCode() + " " + "Calgary, AB</p></td></tr>" );
          }



        
        getServletContext().getRequestDispatcher("/WEB-INF/confirmation.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        CustomerService cs = new CustomerService();
        DecimalFormat df = new DecimalFormat("#.##");
        String subTotalString = (String)session.getAttribute("subtotalString");
        
        double subtotal = Double.parseDouble(subTotalString);
        double tax = 0;

        int precision = 100;
        tax = subtotal * 0.05;
        tax = Math.floor(tax * precision + .5) / precision;
        double total = tax + subtotal;

        String totalString = df.format(total);
        String taxString = df.format(tax);
        String subtotalString = df.format(subtotal);
        
        ArrayList<Item> itemList = new ArrayList<Item>();
        itemList = (ArrayList<Item>)session.getAttribute("cartItems");
        
        String product = "Tandoori Grill Order";
	String shipping = "0";

       
        Delivery d = (Delivery)session.getAttribute("delivery_info");        
        String fname = d.getRecieverFirstname();
        String lname = d.getRecieverLastname();
        String dEmail = d.getRecieverEmail();
        String dBuilding = d.getBuilding();
        String dUnit = d.getUnit();
        String dPostal = d.getDeliveryPostalCode();
        String dStreet = d.getDeliveryStreet();
        String dPhone = d.getRecieverPhonenumber();
        String method = (String)session.getAttribute("pickUpDelivery");
        String dNote = d.getDeliveryNote();


        String date1 = "2012-12-13";
        java.sql.Date startdate = null;
        startdate = java.sql.Date.valueOf(date1);

        String date2 = "2019-12-13";
        java.sql.Date duedate = null;
        duedate = java.sql.Date.valueOf(date2);
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
        
        
        if (request.getParameter("PayAtDoor") != null) {
            cs.createOrder(actorId, dBuilding, dUnit, dStreet, dPostal, "Calgary", "AB", dNote, dEmail, dPhone, /*startdate, */duedate, total, "unconfirmed", "Pay in Person", method, itemList);

            response.sendRedirect("ThankyouServlet");
        } else if (request.getParameter("PayPal") != null) {
                session.setAttribute("total", total);
            	OrderDetail orderDetail = new OrderDetail(product, subtotalString, shipping, taxString, totalString);
        
        try {
            PaymentServices paymentServices = new PaymentServices();
            String approvalLink = paymentServices.authorizePayment(orderDetail, fname, lname, dEmail);
            response.sendRedirect(approvalLink);
        } catch (PayPalRESTException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            request.setAttribute("errorMessage", e.getMessage());		
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }

        }


    }
// </editor-fold>

}
