/*
 * Class Description: This class acts as the intermediary between the database broker and Manager Servlets.
 */
package services;

import brokers.*;
import com.main.menu.Order;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class Description: This class acts as the intermediary between the database broker and Manager Servlets.
 *
 * @author 763199 - Nguyen Khanh Duy Phan
 */
public class ManagerService {
    
    EmployeeBroker eb = new EmployeeBroker();
    AdminBroker ab = new AdminBroker();
    ActorBroker acb = new ActorBroker();
    ManagerBroker mb = new ManagerBroker();
    
    /**
     * Verifies that the Order exists based on an orderID.
     * @param orderID
     * @return
     * @throws NullPointerException 
     */
    public Order searchOrder(int orderID) throws NullPointerException 
    {
        Order foundOrder = eb.getOrderById(orderID);
        return foundOrder;
    }
    
    /**
     * Gets an Order based on a verified orderID.
     * @param orderID
     * @return
     * @throws NullPointerException 
     */
    public Order Order(int orderID) throws NullPointerException 
    {
        Order foundOrder = eb.getOrderById(orderID);
        return foundOrder;
    }
    
    /**
     * Get all Orders that match a given phone number.
     * @param phoneNumber
     * @return
     * @throws NullPointerException 
     */
    public List<Order> searchOrdersByPhone(int phoneNumber) throws NullPointerException 
    {
        List<Order> orderList = eb.getAll();
        List<Order> foundList = new ArrayList<>();
        String p = Integer.toString(phoneNumber);
        for(int i = 0; i < orderList.size(); i++) 
        {
            if(orderList.get(i).getDeliveryInfo().getRecieverPhonenumber().equals(p)) 
            {
                foundList.add(orderList.get(i));
            }
        }
        return foundList;
    }
    
    /**
     * Get all Orders that match a verified Customer ID.
     * @param customerID
     * @return
     * @throws NullPointerException 
     */
    public List<Order> searchOrdersByCustomerID(int customerID) throws NullPointerException 
    {
        List<Order> orderList = eb.getAll();
        List<Order> foundList = new ArrayList<>();
        for(int i = 0; i < orderList.size(); i++) 
        {
            
            if(customerID == orderList.get(i).getCustomerID()) 
            {
                foundList.add(orderList.get(i));
            }
        }
        return foundList;
    }
    
    /**
     * Begin voiding an Order, after verifying credentials, then notify the Customer with a text message with a reason.
     * 
     * @param order
     * @param phone
     * @param managerEmail
     * @param managerPassword
     * @param reason
     * @return 
     */
    public boolean voidOrder(Order order, String phone, String managerEmail, String managerPassword, String reason)
    {
        try {
            //Check Credential
            if(!acb.checkCredential(managerEmail, managerPassword)) 
            {
                return false;
            }
            if(mb.voidOrderStatus(order)) 
            {
                return SmsSender.voidSMS(phone, reason);
            }
            
            return false;
            
        } catch (NullPointerException ex) {
            Logger.getLogger(ManagerService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ManagerService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
            
}
