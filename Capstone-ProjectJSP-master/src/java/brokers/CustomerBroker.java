/*
 * Class Description: This class acts as the intermediary between the database and Customer-specific services.
 */
package brokers;

import com.main.actor.Customer;
import com.main.menu.Item;
import com.main.databaseConnection.ConnectionPool;
import com.main.delivery.Delivery;
import com.main.menu.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Class Description: This class acts as the intermediary between the database and Customer services.
 * @author cuong
 * @version 1.1
 */
public class CustomerBroker {
    /**
     *  This method use to get all order of one specific customer by his/her id 
     * @param customerId
     * @return 
     */
    public List<Order> getAllOrderByCustomerID(int customerId)
    {
        ConnectionPool pool = ConnectionPool.createConnection();
        Connection con = ConnectionPool.getConnection();
        List<Order> orderList = new ArrayList<>();
        String query = "Select od.orderID, od.order_time, od.due_date, od.total_price, d.deliveryID, " +
                "od.status, od.payment_type, od.delivery_method, d.house_number, d.unit_number, " +
                "d.street, d.postal_code, d.city, d.province, d.note, d.contact_email, d.actorID, d.phone_number, a.f_name, a.l_name " +
                " FROM orders od JOIN delivery d ON od.deliveryID = d.deliveryID " +
                "JOIN actor a ON d.actorID = a.actorID "+
                "WHERE a.actorID = ?";
        String selectOrderItemQuery = "SELECT oi.itemID, oi.quantity, m.item, m.description, m.category, m.image "
                                       +"FROM order_items oi JOIN menu m ON oi.itemID = m.itemID WHERE oi.orderID = ?";
        PreparedStatement ps = null;
        PreparedStatement psItem = null;
        ResultSet rsItem = null;
        
        ResultSet rs = null;
        
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, customerId);
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                Order order = new Order();
                List<Item> orderItemList = new ArrayList<>();
                Delivery d = new Delivery();
                order.setOrderID(rs.getInt("od.orderID"));
                order.setOrderDate(rs.getDate("od.order_time"));
                order.setOrderDate(rs.getDate("od.due_date"));
                order.setTotalPrice(rs.getDouble("od.total_price"));
                order.setOrderStatus(rs.getString("od.status"));
                order.setPaymentMethod(rs.getString("od.payment_type"));
                order.setCustomerID(rs.getInt("d.actorID"));
                //atrive delivery info
                d.setDeliveryMethod(rs.getString("od.delivery_method"));
                d.setBuilding(rs.getString("d.house_number"));
                d.setUnit(rs.getString("d.unit_number"));
                d.setDeliveryStreet(rs.getString("d.street"));
                d.setDeliveryPostalCode(rs.getString("d.postal_code"));
                d.setDeliveryCity(rs.getString("d.city"));
                d.setDeliveryProvince(rs.getString("d.province"));
                d.setDeliveryNote(rs.getString("d.note"));
                d.setRecieverEmail(rs.getString("d.contact_email"));
                d.setDeliveryID(rs.getInt("d.deliveryID"));
                d.setRecieverPhonenumber(rs.getString("d.phone_number"));
                d.setRecieverFirstname(rs.getString("a.f_name"));
                d.setRecieverLastname(rs.getString("a.l_name"));
                //set delivery info to order
                order.setDeliveryInfo(d);
                //add Item to list
                psItem = con.prepareStatement(selectOrderItemQuery);
                psItem.setInt(1, order.getOrderID());
                rsItem = psItem.executeQuery();
                while(rsItem.next())
                {
                    Item item = new Item();
                    item.setItemId(rsItem.getInt("oi.itemID"));
                    item.setQuantity(rsItem.getInt("oi.quantity"));
                    item.setItemName(rsItem.getString("m.item"));
                    item.setDescription(rsItem.getString("m.description"));
                    item.setCategory(rsItem.getString("m.category"));
                    item.setImagePath(rsItem.getString("m.image"));
                    orderItemList.add(item);
                }
                order.setItemList(orderItemList);
               
                //add order to the list
                orderList.add(order);
                
                
            }  
        } catch (SQLException ex) {
            Logger.getLogger(CustomerBroker.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            pool.freeConnection(con);
            try {
                rs.close();
                ps.close();
                rsItem.close();
                psItem.close();
            } catch (SQLException ex) {
                Logger.getLogger(CustomerBroker.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return orderList;
    }
    /**
     * This method is used to insert an order to the database
     * it follow in three table (delivery, orders, order_items)
     * 
     * @param order
     * @return boolean
     */
    public boolean insertOrder(Order order)
    {
        ConnectionPool pool = ConnectionPool.createConnection();
        Connection con = ConnectionPool.getConnection();
        String query1 = "INSERT INTO delivery (deliveryID,actorID, house_number, unit_number, street, postal_code, city, province, note, contact_email, phone_number)"
                       + " VALUES(NULL,?,?,?,?,?,?,?,?,?,?)";
        String query2 = "INSERT INTO orders (deliveryID, order_time, due_date, total_price, status, payment_type, delivery_method) "
                        + " VALUES(LAST_INSERT_ID(),?,?,?,?,?,?)";
        String query3 = "INSERT INTO order_items(orderID, itemID, quantity, note) "
                       + "VALUES(LAST_INSERT_ID(), ?, ?, 'note collumn is not used')";
  
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        PreparedStatement ps3 = null;
        
        try {
            ps1 = con.prepareStatement(query1);
            ps1.setInt(1, order.getCustomerID());
            ps1.setString(2, order.getDeliveryInfo().getBuilding());
            ps1.setString(3, order.getDeliveryInfo().getUnit());
            ps1.setString(4, order.getDeliveryInfo().getDeliveryStreet());
            ps1.setString(5, order.getDeliveryInfo().getDeliveryPostalCode());
            ps1.setString(6, order.getDeliveryInfo().getDeliveryCity());
            ps1.setString(7, order.getDeliveryInfo().getDeliveryProvince());
            ps1.setString(8, order.getDeliveryInfo().getDeliveryNote());
            ps1.setString(9, order.getDeliveryInfo().getRecieverEmail());
            ps1.setString(10, order.getDeliveryInfo().getRecieverPhonenumber());
            ps1.executeUpdate();
            
            ps2 = con.prepareStatement(query2);
  
            ps2.setDate(1, order.getOrderDate());
            ps2.setDate(2, order.getDueDate());
            ps2.setDouble(3, order.getTotalPrice());
            ps2.setString(4, order.getOrderStatus());
            ps2.setString(5, order.getPaymentMethod());
            ps2.setString(6, order.getDeliveryInfo().getDeliveryMethod());
            ps2.executeUpdate();
            
            for(int i = 0; i < order.getItemList().size(); i++)
            {  
                
                ps3 = con.prepareStatement(query3);
                ps3.setInt(1, order.getItemList().get(i).getItemId());
                ps3.setInt(2, order.getItemList().get(i).getQuantity());
                ps3.executeUpdate();
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CustomerBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally{
            pool.freeConnection(con);
            try {
                ps1.close();
                ps2.close();
                ps3.close();
            } catch (SQLException ex) {
                Logger.getLogger(CustomerBroker.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        return true;
    }
    
    /**
     * Gets a Delivery object by a Customer's ID from the database.
     * @param customerId
     * @return 
     */
    public Delivery getDeliveryByID(int customerId)
    {
        ConnectionPool pool = ConnectionPool.createConnection();
        Connection con = ConnectionPool.getConnection();
        Delivery deliveryInfo = new Delivery();
        String query = "SELECT * " 
                       +"FROM delivery WHERE actorID = ? ";
        PreparedStatement ps = null ;
        ResultSet rs = null; 
        
        try { 
              
            ps = con.prepareStatement(query);
            ps.setInt(1, customerId);
            rs = ps.executeQuery(); 
            while(rs.next())
            {
                deliveryInfo.setBuilding(rs.getString("house_number"));
                deliveryInfo.setDeliveryCity("Calgary");
                deliveryInfo.setDeliveryNote(rs.getString("note"));
                deliveryInfo.setDeliveryPostalCode(rs.getString("postal_code"));
                deliveryInfo.setDeliveryProvince("AB");
                deliveryInfo.setDeliveryStreet(rs.getString("street"));
                deliveryInfo.setRecieverEmail(rs.getString("contact_email"));
                deliveryInfo.setRecieverPhonenumber(rs.getString("phone_number"));
                deliveryInfo.setUnit(rs.getString("unit_number"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CustomerBroker.class.getName()).log(Level.SEVERE, null, ex);
        }finally{   
            pool.freeConnection(con);
            try {
               ps.close();
               rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(CustomerBroker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return deliveryInfo;
    }
    
    /**
     * Sets a Customers state in the database to inactive using their email
     * @param email
     * @return boolean
     */
    public boolean deleteCustomerByEmail(String email)
    {
        ConnectionPool pool = ConnectionPool.createConnection();
        Connection con = ConnectionPool.getConnection();
        String query = "UPDATE actor SET "
                + "active = 0 "
                + "WHERE email_login = ?";
                
        PreparedStatement ps = null;
        
        try { 
              
            ps = con.prepareStatement(query);
            ps.setString(1, email);
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(CustomerBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally{   
            pool.freeConnection(con);
            try {
               ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(CustomerBroker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return true;
    }
    
    /**
     * Updates a Customer's account information and Delivery information in the database with
     * incoming params.
     * @param customerInfo
     * @param deliveryInfo
     * @return 
     */
    public boolean updateCustomerInfo(Customer customerInfo, Delivery deliveryInfo)
    {
        ConnectionPool pool = ConnectionPool.createConnection();
        Connection con = ConnectionPool.getConnection();
        String query1 = "UPDATE delivery SET "
                + "house_number = ?, "
                + "unit_number = ?, "
                + "street = ?, "
                + "postal_code = ?, "
                + "note = ?, "
                + "contact_email = ?, "
                + "phone_number = ? "
                + "WHERE actorID = ?";
        
        String query2 = "UPDATE actor SET "
                + "password = ?, "
                + "phone_number = ?, "
                + "f_name = ?, "
                + "l_name = ?, "
                + "house_number = ?, "
                + "unit_number = ?, "
                + "street = ?, "
                + "postal_code = ? "
                + "WHERE actorID = ?";
        
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        try {
            ps1 = con.prepareStatement(query1);
            ps1.setString(1, deliveryInfo.getBuilding());
            ps1.setString(2, deliveryInfo.getUnit());
            ps1.setString(3, deliveryInfo.getDeliveryStreet());
            ps1.setString(4, deliveryInfo.getDeliveryPostalCode());
            ps1.setString(5, deliveryInfo.getDeliveryNote());
            ps1.setString(6, deliveryInfo.getRecieverEmail());
            ps1.setString(7, deliveryInfo.getRecieverPhonenumber());
            ps1.setInt(8, customerInfo.getId());
            ps1.executeUpdate();
            
            
            ps2 = con.prepareStatement(query2);
  
            ps2.setString(1, customerInfo.getPassword());
            ps2.setString(2, customerInfo.getPhoneNumber());
            ps2.setString(3, customerInfo.getFirstName());
            ps2.setString(4, customerInfo.getLastName());
            ps2.setString(5, customerInfo.getHouseNumber());
            ps2.setString(6, customerInfo.getUnitNumber());
            ps2.setString(7, customerInfo.getStreet());
            ps2.setString(8, customerInfo.getPostalCode());
            ps2.setInt(9, customerInfo.getId());
            ps2.executeUpdate();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CustomerBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally{
            pool.freeConnection(con);
            try {
                ps1.close();
                ps2.close();
            } catch (SQLException ex) {
                Logger.getLogger(CustomerBroker.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return true;
    }
    
    /**
     * Creates a new Delivery row in the database for a Customer based on their ID.
     * 
     * @param actorId
     * @param house_number
     * @param unit_number
     * @param street
     * @param postalCode
     * @param city
     * @param province
     * @param note
     * @param contact_email
     * @param phoneNumber
     * @return 
     */
    public boolean addDeliveryInfo(int actorId, String house_number, String unit_number, String street, 
            String postalCode, String city, String province, String note,String contact_email,String phoneNumber)
    {
        ConnectionPool pool = ConnectionPool.createConnection();
        Connection con = ConnectionPool.getConnection();
        String query1 = "INSERT INTO delivery (deliveryID,actorID, house_number, unit_number, street, postal_code, city, province, note, contact_email, phone_number)"
                       + " VALUES(NULL,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps1 = null;
        try {
            ps1 = con.prepareStatement(query1);
            ps1.setInt(1, actorId);
            ps1.setString(2, house_number);
            ps1.setString(3, unit_number);
            ps1.setString(4, street);
            ps1.setString(5, postalCode);
            ps1.setString(6, city);
            ps1.setString(7, province);
            ps1.setString(8, note);
            ps1.setString(9, contact_email);
            ps1.setString(10, phoneNumber);
            ps1.executeUpdate();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CustomerBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        
        return true;
    }
    
    /**
     * Inserts a Customer's Favourite Item into the database using their ID.
     * 
     * @param customerId
     * @param item
     * @return boolean
     */
    public boolean insertFavItem(int customerId, Item item)
    {
        ConnectionPool pool = ConnectionPool.createConnection();
        Connection con = ConnectionPool.getConnection();
        String query1 = "INSERT INTO favourite VALUES(?, ?)";
        PreparedStatement ps = null;
        
        try {
            ps = con.prepareStatement(query1);
            ps.setInt(1, customerId);
            ps.setInt(2, item.getItemId());
            ps.executeUpdate();
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CustomerBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally{
            pool.freeConnection(con);
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(CustomerBroker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return true;
        
    }
    
    /**
     * Removes a Customer's Favourite item from the database using their ID.
     * @param customerId
     * @param item
     * @return boolean
     */
    public boolean removeFavItem(int customerId, Item item)
    {
        ConnectionPool pool = ConnectionPool.createConnection();
        Connection con = ConnectionPool.getConnection();
        String query = "DELETE FROM favourite WHERE actorID = ? and itemID = ?";
         PreparedStatement ps = null;
         
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, customerId);
            ps.setInt(2, item.getItemId());
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(CustomerBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally{
             pool.freeConnection(con);
             try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(CustomerBroker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         
        
        return true;
    }
    
    /**
     * Gets all Favourite items from the database for a Customer using their ID.
     * 
     * @param customerId
     * @return 
     */
    public List<Item> getFavoriteItems(int customerId)
    {
        ConnectionPool pool = ConnectionPool.createConnection();
        Connection con = ConnectionPool.getConnection();
        List<Item> favoriteItemList = new ArrayList<>();
        String query = "SELECT f.itemID, m.item, m.cate, m.description, m.image " 
                       +"FROM favourite f JOIN menu m ON f.itemID = m.itemID  WHERE f.actorID = ? ";
        PreparedStatement ps = null ;
        ResultSet rs = null; 
        
        try { 
              
            ps = con.prepareStatement(query);
            ps.setInt(1, customerId);
            rs = ps.executeQuery(); 
            while(rs.next())
            {
                Item favItem = new Item();
                favItem.setItemId(rs.getInt("f.itemID"));
                favItem.setItemName(rs.getString("m.item"));
                favItem.setCategory(rs.getString("m.cate"));
                favItem.setDescription(rs.getString("m.description"));
                favItem.setImagePath(rs.getString("m.image"));
                favoriteItemList.add(favItem);
            }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CustomerBroker.class.getName()).log(Level.SEVERE, null, ex);
        }finally{   
            pool.freeConnection(con);
            try {
               ps.close();
               rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(CustomerBroker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return favoriteItemList;
    }
    
        
    
}
