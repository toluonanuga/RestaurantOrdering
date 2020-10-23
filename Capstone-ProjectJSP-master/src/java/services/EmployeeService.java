/*
 * Class Description: This class acts as the intermediary between the database broker and Employee Servlets.
 */
package services;

import brokers.*;
import com.main.actor.Customer;
import com.main.menu.Order;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class Description: This class acts as the intermediary between the database broker and Employee Servlets.
 *
 * @author Nguyen Khanh Duy Phan
 */
public class EmployeeService {
    
    private List<Order> orderList = new ArrayList<>();
    private final EmployeeBroker eb = new EmployeeBroker(); //Checking the final keyword
    private final AdminBroker ab = new AdminBroker();
    
    /**
     * Gets ALL Orders and keeps it in memory.
     * @return
     * @throws NullPointerException 
     */
    public List<Order> displayOrder() throws NullPointerException 
    {
        orderList = eb.getAll();
        return orderList;
    }
    
    /**
     * Verifies the Order and places it into the global Orders list.
     * @param o
     * @throws NullPointerException 
     */
    public void receiveOrder(Order o) throws NullPointerException 
    {
        StaticOrderList.receiveOrder(o);
    }
    
    /**
     * Verifies that an Order has been confirmed and sends off a text message to the Customer.
     * @param o
     * @return
     * @throws NullPointerException 
     */
    public boolean confirmOrder(Order o) throws NullPointerException 
    {
        int cusID = o.getCustomerID();
        try {
            if(eb.changeStatus(o, "CONFIRMED")) //Changing Status
            {
                //Sending SMS if status is changed
                Customer cus = (Customer) ab.getActorByID(cusID);
                return SmsSender.complete(cus.getPhoneNumber());
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Cannot get customer info in EmployeeService");
        }
        
        return false;
    }
    
    /**
     * Verifies that an Order has been complete and sends off a text message to the Customer.
     * 
     * @param o
     * @return
     * @throws NullPointerException 
     */
    public boolean completeOrder(Order o) throws NullPointerException 
    {
        int cusID = o.getCustomerID();
        try {
            if(eb.changeStatus(o, "COMPLETE")) //Changing Status
            {
                //Sending SMS if status is changed
                Customer cus = (Customer) ab.getActorByID(cusID);
                return SmsSender.complete(cus.getPhoneNumber());
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Cannot get customer info in EmployeeService");
        }
        
        return false;        
    }
}
