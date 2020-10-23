/*
 * Class Description: A bridge between JSPs and the Services: This Servlet verifies Delivery and Order information.
 */
package servlets;

import brokers.MenuBroker;
import com.main.actor.Actor;
import com.main.delivery.Delivery;
import com.main.menu.Item;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.CustomerService;
import services.MenuService;

/**
 * Class Description: A bridge between JSPs and the Services: This Servlet verifies Delivery and Order information.
 *
 * @author Chris
 */
public class CheckoutServlet extends HttpServlet {


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
        //prepopulate form fields if they are logged in
        HttpSession session = request.getSession();
        	
        if(session.getAttribute("deliveryInfo") != null)
        {
            Delivery d = (Delivery)session.getAttribute("deliveryInfo");
            Actor a = (Actor)session.getAttribute("loggedActor");
         
            request.setAttribute("deliveryStreet", d.getDeliveryStreet());
            request.setAttribute("deliveryUnit", d.getUnit());
            request.setAttribute("deliveryBuilding", d.getBuilding());
            request.setAttribute("deliveryPhone", d.getRecieverPhonenumber());
            request.setAttribute("deliveryEmail", d.getRecieverEmail());
            request.setAttribute("deliveryFname", a.getFirstName());
            request.setAttribute("deliveryLname", a.getLastName());
            request.setAttribute("deliveryPostal", d.getDeliveryPostalCode());
            request.setAttribute("deliveryNote", d.getDeliveryNote());
        }

        getServletContext().getRequestDispatcher("/WEB-INF/checkout.jsp").forward(request, response);
            
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
        MenuBroker mb = new MenuBroker();
        HttpSession session = request.getSession();
        MenuService ms = new MenuService();
        CustomerService cs = new CustomerService();
        Double total = 0.0;
         
        
        
        //reading from the form fields. This still needs to be pre-populated if they are logged in...
        String d_fname = request.getParameter("recieverFirstname");
        String d_lname = request.getParameter("recieverLastname");
        String d_phone = request.getParameter("recieverPhonenumber");
        d_phone = d_phone.replaceAll("-", "");
        String d_email = request.getParameter("recieverEmail");
        String d_building = request.getParameter("building");
        //setting null ones to "" to avoid null pointer exceptions should they be accessing this servlet via the takeout page. 
        if(d_building == null)
        {
            d_building = "";
        }
        String d_unit = request.getParameter("unit");
        if (d_unit == null)
        {
            d_unit = "";
        }
        String d_street = request.getParameter("deliveryStreet");
        if (d_street == null)
        {
            d_street = "";
        }
        String d_postal = request.getParameter("deliveryPostalCode");
        if(d_postal == null)
        {
            d_postal = "";
        }
        String d_province = request.getParameter("deliveryProvince");
        if(d_province == null)
        {
            d_province = "";
        }
        String d_note = request.getParameter("note");
        if (d_note == null) 
        {
            d_note = "No note";
        }

        String item_string = request.getParameter("item_list");
        ArrayList csv_item_array = new ArrayList();
        ArrayList<Item> itemList = new ArrayList<Item>();
        StringTokenizer tokenizer = new StringTokenizer(item_string, ",");
        
        while (tokenizer.hasMoreTokens()) 
        {
            csv_item_array.add(tokenizer.nextToken());
        }        
        
             double itemTotal = 0;

        for(int i = 0; i<(csv_item_array.size()-1); i+=2)
        {
            Item o = new Item();
            o.setItemId(Integer.parseInt((String) csv_item_array.get(i)));
            Item item = mb.getItemById(o.getItemId());
            int quantity = (Integer.parseInt((String)csv_item_array.get(i+1)));
            item.setQuantity(quantity);
            itemTotal = item.getPrice();
            
            //To get around decimal rounding errors with double multiplication. 
            for(int k = 0; k<quantity-1; k++)
            {
               itemTotal += itemTotal;
            }
          
            item.setPrice(itemTotal);
            itemList.add(item);
            item = null;
            o = null;
            quantity = 0;
            total += itemTotal;
        }
      
        //store itemList to session for use in later pages. 
        session.setAttribute("cartItems", itemList);
        
        //now lets store the delivery object into a session object for use in later pages. 
        Delivery d = new Delivery(d_fname, d_lname, d_phone, d_email, d_building, d_unit, d_street, "Calgary", d_postal, "AB", d_note, "Delivery");
        d.setRecieverPhonenumber(d_phone);      //unsure why it's not setting properly in the line above, sorry this is a bit hack.
        session.setAttribute("delivery_info", d);
        
        session.setAttribute("subtotalString", total.toString());
        
        response.sendRedirect("OrderConfirmation");
    }// </editor-fold>
}