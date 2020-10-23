/*
 * Class Description: A bridge between JSPs and the Services: This Servlet is for when an 
 * Employee(there should only be 1 logged in at a time) interacts with the Orders currently in the system.
 */
package servlets;

import brokers.AdminBroker;
import com.google.gson.Gson;
import com.main.actor.Actor;
import com.main.actor.Customer;
import com.main.delivery.Delivery;
import com.main.menu.Item;
import com.main.menu.Order;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.*;

/**
 * Class Description: A bridge between JSPs and the Services: This Servlet is for when an  
 * Employee(there should only be 1 logged in at a time) interacts with the Orders in the system.
 *
 * @author Shawn Kaldenbach
 */
public class EmployeeServlet extends HttpServlet {

    private ArrayList<Order> allOrders = new ArrayList<>();
    private ArrayList<Order> receivedOrders = new ArrayList<>();
    private ArrayList<Actor> actorList = new ArrayList<>();
    private ArrayList<Actor> customerList = new ArrayList<>();

    private EmployeeService es = new EmployeeService();
    private ManagerService ms = new ManagerService();
    private AdminBroker ab = new AdminBroker();
    private AdminService as = new AdminService();

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

        String action = request.getParameter("action");
        if (action == null) {
            getServletContext().getRequestDispatcher("/WEB-INF/employee.jsp").forward(request, response);
            return;
        }
        
        
        /*
        allOrders = (ArrayList) ab.getAllPastOrder();
        for (int i = 0; i < allOrders.size(); i++) {
            if (allOrders.get(i).getOrderStatus().equalsIgnoreCase("received") || allOrders.get(i).getOrderStatus().equalsIgnoreCase("CONFIRMED")) {
                receivedOrders.add(allOrders.get(i));
            }
        }
        */
        
        
        StaticOrderList.updateList();

        System.out.println(StaticOrderList.getOrderList().get(0).getTotalPrice());
        
        //sdsadsadsad 
        response.setContentType("application/json");
        Gson gson = new Gson();
        String data = gson.toJson(StaticOrderList.getOrderList());
        response.getWriter().println(data);
        response.flushBuffer();
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

        String status = request.getParameter("status"); 
        String customerID = request.getParameter("customerID"); //To get the customer ID
        String orderID = request.getParameter("orderID"); //To get orderID

        Order order = new Order();
        
        //Get order based on orderID
        allOrders = (ArrayList) ab.getAllPastOrder();
        for (int i = 0; i < allOrders.size(); i++) {
            if (allOrders.get(i).getOrderID() == Integer.parseInt(orderID)) {
                order = allOrders.get(i);
                break;
            }
        }

        //Status is for the changing of Order.
        //There is RECEIVED, CONFIRMED, COMPLETE, VOID.
        //RECEIVED is it is in the global orderList.
        //CONFIRMED is we have notified the customer that their order is being ade
        //COMPLETE is the order is done and is removed from the list.
        //VOID is when the order cannot be completed for any reason.
        
        //All requests in Employee POST come with the orderID.
        if (status.equalsIgnoreCase("VOID"))
        {
            //If it's a void, get the managerEmail, managerPassword, and managerReason.
            //Validate everything (probably using voidOrder method)
            //Then set response to true if everything was fine.  False if otherwise.
            //Make sure to do a return at the end of this method.
            String managerEmail = request.getParameter("managerEmail");
            String managerPassword = request.getParameter("managerPassword");
            String managerReason = request.getParameter("managerReason");

            try {
                Actor customer = ab.getActorByID(Integer.parseInt(customerID));
                if(ms.voidOrder(order, customer.getPhoneNumber(), managerEmail, managerPassword, managerReason)) 
                {
                    System.out.println("Order Voided");
                    
                }
                else 
                {
                    System.out.println("Order cannot be Voided");
                }
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NullPointerException ex) {
                Logger.getLogger(EmployeeServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if (status.equalsIgnoreCase("CONFIRMED")) {
            if(es.confirmOrder(order)) 
            {
                System.out.println("Order has been Confirmed");
            }
            else 
            {
                System.out.println("Order cannot be Confirmed");
            }
        }
        if (status.equalsIgnoreCase("COMPLETE")) {
            if(es.completeOrder(order)) 
            {
                System.out.println("Order has been Completed");
                
                /////Remove from list - check lines above from 64 to 69
            }
            else 
            {
                System.out.println("Order cannot be Completed");
            }
        }

        
        //DON'T UNDERSTAND THIS PART
        System.out.println(status);
        System.out.println(orderID);
        allOrders.get(0).setOrderStatus(status);

        response.getWriter().println("True!");

    }

}
