/*
 * Class Description: This class is the Order model class.
 */
package com.main.menu;

import brokers.AdminBroker;
import com.main.delivery.Delivery;
import java.sql.Date;
import java.sql.SQLException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class Description: This class is the Order model class.
 * @author cuong
 *
 */
public class Order 
{
    private int orderID;
    private int customerID;   
    private List<Item> itemList;
    private double totalPrice;
    private Delivery deliveryInfo;
    private String orderStatus; 
    private String paymentMethod;
    private Date orderDate;
    private Date dueDate;
    private String customerEmail;

    public Order() 
    {
        
    }
    public String getCustomerEmail() 
    {
        return this.customerEmail;
    }
    public void setOrderID(int orderID) 
    {
      
        this.orderID = orderID;
    }
    
    public int getOrderID() 
    {
        return this.orderID;
    }
    
    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Delivery getDeliveryInfo() {
        return deliveryInfo;
    }

    public void setDeliveryInfo(Delivery deliveryInfo) {
        this.deliveryInfo = deliveryInfo;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }


    public boolean addItem(Item item) throws NullPointerException
    {
        if(item != null ) {
            itemList.add(item);
            return true;
        }
        return false;
    }
    
    public boolean removeItem(Item item) throws NullPointerException
    {
        if(item != null ) {
            itemList.remove(item);
            return true;
        }
        return false;
    }
    
    public void setCustomerID(int customerID) 
    {
        AdminBroker ab = new AdminBroker();
        try {
            this.customerEmail = ab.getActorByID(customerID).getEmail();
        } catch (SQLException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.customerID = customerID; 
        
    }
    public int getCustomerID()
    {
        return this.customerID;
    }
    
}
