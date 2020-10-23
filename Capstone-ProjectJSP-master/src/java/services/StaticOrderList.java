/*
 * Class Description: This class acts as a global service that holds and modifies all current Orders that need to be processed.
 */
package services;

import brokers.EmployeeBroker;
import com.main.menu.Order;
import java.util.*;

/**
 * Class Description: This class acts as a global service that holds and modifies all current Orders that need to be processed.
 * 
 * @author Nguyen Khanh Duy Phan
 */
public class StaticOrderList {
    
    public static List<Order> orderList = new ArrayList<>();
    
    /**
     * Adds a verified Order into the orderList.
     * @param o 
     */
    public static void receiveOrder(Order o) 
    {
        orderList.add(o);
    }
    
    /**
     * Removes a verified Order from the orderList.
     * @param o 
     */
    public static void removeOrder(Order o)
    {
        orderList.remove(o);
    }
    
    /**
     * Replaces the current orderList with all Orders not yet completed.
     */
    public static void updateList()
    {
        List<Order> newList = new ArrayList<>();
        
        EmployeeBroker eb = new EmployeeBroker();
        newList = eb.getAllReceivedAndConfirmed();
        //newList.add(eb.getOrderById(1));
        
        orderList = newList;
    }
    
    /**
     * Gets the current orderList.
     * @return 
     */
    public static List<Order> getOrderList()
    {
        return orderList;
    }
    
    /**
     * Displays all current Orders in the orderList to console.
     */
    public static void displayOrder() 
    {
        for(int i = 0; i < orderList.size(); i++) 
        {
            System.out.println(orderList.get(i).getOrderID() + " : " + orderList.get(i).getOrderStatus());
        }
    }
}
