/*
 * Class Description: A bridge between JSPs and the Services: This Servlet is for handling an Order as it gets ready for Order Confirmation.
 */
package servlets;

import com.main.delivery.Delivery;
import com.main.menu.Item;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.CustomerService;
import services.MenuService;

/**
 * Class Description: A bridge between JSPs and the Services: This Servlet is for handling an Order as it gets ready for Order Confirmation.
 *
 * @author Chris
 */
public class TakeoutServlet extends HttpServlet {


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
         getServletContext().getRequestDispatcher("/WEB-INF/checkout_takeout.jsp").forward(request, response);
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
        MenuService ms = new MenuService();
        CustomerService cs = new CustomerService();
        //reading from the form fields. This still needs to be pre-populated if they are logged in...
        String d_fname = request.getParameter("recieverFirstname");
        String d_lname = request.getParameter("recieverLastname");
        String d_phone = request.getParameter("recieverPhonenumber");
        String d_email = request.getParameter("recieverEmail");
        String d_note = request.getParameter("note");
       
        String total_string = request.getParameter("total_price");
        System.out.println("TOTALPRICE FROM JSP IS: " + total_string);
        
        String item_string = request.getParameter("item_list");
        ArrayList csv_item_array = new ArrayList();
        ArrayList<Item> itemList = new ArrayList<Item>();
        StringTokenizer tokenizer = new StringTokenizer(item_string, ",");
        
        while (tokenizer.hasMoreTokens()) 
        {
            csv_item_array.add(tokenizer.nextToken());
        }        
        
        for(int i = 0; i<(csv_item_array.size()-1); i+=2)
        {

            Item item = new Item();
            item.setItemId(Integer.parseInt((String) csv_item_array.get(i)));
            item.setQuantity(Integer.parseInt((String)csv_item_array.get(i+1)));
            
            itemList.add(item);
            item = null;
        }
      
        //store item_list2 into a session object so I can use it on the confirmation page...
        session.setAttribute("cart_items", itemList);

        
        //now lets store the delivery object into a session object for the confirmation page...
        Delivery d = new Delivery(d_fname, d_lname, d_phone, d_email, "n/a", "n/a", "n/a", "Calgary", "n/a", "AB", d_note, "Takeout");
        session.setAttribute("delivery_info", d);
        session.setAttribute("subtotal_string", total_string);
        

        String date1 = "2012-12-13";
        java.sql.Date startdate = null;
        startdate = java.sql.Date.valueOf(date1);


        String date2 = "2019-12-13";
        java.sql.Date duedate = null;
        duedate = java.sql.Date.valueOf(date2);


        
        
        //cs.createOrder(3, d_building, d_unit, d_street, d_postal, "Calgary", "AB", "Note String", d_email, d_phone, startdate, duedate, Double.valueOf(total_string), "unconfirmed", "pay_method_string", "delivery", itemList);
           

        response.sendRedirect("OrderConfirmation");
    }// </editor-fold>

}
