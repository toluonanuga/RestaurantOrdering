/*
 * Class Description: This class acts as the intermediary between the database and Employee services.
 */
package brokers;

import com.main.databaseConnection.ConnectionPool;
import com.main.delivery.*;
import com.main.menu.*;
import java.sql.*;
import java.util.*;

/**
 * Class Description: This class acts as the intermediary between the database and Employee services.
 * @author 763199 - Nguyen Khanh Duy Phan
 * @version 1.1
 */
public class EmployeeBroker {

    /**
     * Gets ALL orders from the database.
     * @return
     * @throws NullPointerException 
     */
    public List<Order> getAll() throws NullPointerException {
        ConnectionPool pool = ConnectionPool.createConnection();
        Connection con = pool.getConnection();
        String query1 = "SELECT o.orderID, o.order_time, o.due_date, o.total_price, o.status, o.payment_type, o.delivery_method,"
                + "d.deliveryID, d.actorID, d.house_number, d.unit_number, d.street, d.postal_code, d.city, d.province, d.note, d.contact_email, d.phone_number "
                + "FROM orders o JOIN delivery d ON o.deliveryID = d.deliveryID";
        String query2 = "SELECT oi.itemID, oi.quantity, oi.note,"
                + "m.item, m.description, m.price, m.category, m.image, m.active "
                + "FROM orders o JOIN order_items oi ON o.orderID = oi.orderID "
                + "JOIN menu m ON oi.itemID = m.itemID";
        PreparedStatement ps1 = null, ps2 = null;
        ResultSet rs1 = null, rs2 = null;
        Order o = null;
        Delivery d = null;
        Item i = null;
        List<Item> itemsList = new ArrayList<>(); 
        List<Order> ordersList = new ArrayList<>();
        try {
            ps1 = con.prepareStatement(query1);
            rs1 = ps1.executeQuery();
            while (rs1.next()) {
                o = new Order();
                d = new Delivery();
                //Order info
                o.setOrderID(rs1.getInt("o.orderID"));
                o.setOrderDate(rs1.getDate("o.order_time"));
                o.setDueDate(rs1.getDate("o.due_date"));
                o.setTotalPrice(rs1.getFloat("o.total_price"));
                
                Double totalPrice = o.getTotalPrice() * 100;
                totalPrice = Math.ceil(totalPrice) / 100;
                o.setTotalPrice(totalPrice);
                
                o.setOrderStatus(rs1.getString("o.status"));
                o.setPaymentMethod(rs1.getString("o.payment_type"));
                o.setCustomerID(rs1.getInt("d.actorID"));
                //Delivery Info
                d.setDeliveryID(rs1.getInt("d.deliveryID"));
                d.setBuilding(rs1.getString("d.house_number"));
                d.setUnit(rs1.getString("d.unit_number"));
                d.setDeliveryStreet(rs1.getString("d.street"));
                d.setDeliveryPostalCode(rs1.getString("d.postal_code"));
                d.setDeliveryCity(rs1.getString("d.city"));
                d.setDeliveryProvince(rs1.getString("d.province"));
                d.setDeliveryNote(rs1.getString("d.note"));
                d.setRecieverEmail(rs1.getString("d.contact_email"));
                d.setRecieverPhonenumber(rs1.getString("d.phone_number"));
                //Items info
                ps2 = con.prepareStatement(query2);
                rs2 = ps2.executeQuery();
                while(rs2.next()) 
                {
                    i = new Item();
                    //item info
                    i.setItemId(rs2.getInt("oi.itemID"));
                    i.setQuantity(rs2.getInt("oi.quantity"));
                    i.setItemName(rs2.getString("m.item"));
                    i.setDescription(rs2.getString("d.description"));
                    i.setPrice(rs2.getFloat("m.price"));
                    i.setCategory(rs2.getString("m.category"));
                    i.setImagePath(rs2.getString("m.image"));
                    //Adding item to list
                    itemsList.add(i);
                }
                o.setItemList(itemsList);
                //Adding an order to list
                ordersList.add(o);
            }
            return ordersList;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {

            pool.freeConnection(con);
            try {
                rs2.close();
                ps2.close();
                rs1.close();
                ps1.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        return null;
    }

    /**
     * Get a specific Order from the database using the orderID.
     * @param orderID
     * @return
     * @throws NullPointerException 
     */
    public Order getOrderById(int orderID) throws NullPointerException {
        ConnectionPool pool = ConnectionPool.createConnection();
        Connection con = pool.getConnection();
        String query1 = "SELECT o.orderID, o.order_time, o.due_date, o.total_price, o.status, o.payment_type, o.delivery_method,"
                + "d.deliveryID, d.actorID, d.house_number, d.unit_number, d.street, d.postal_code, d.city, d.province, d.note, d.contact_email, d.phone_number"
                + "FROM orders o JOIN delivery d ON o.deliveryID = d.deliveryID "
                + "WHERE o.orderID = ?";
        String query2 = "SELECT oi.itemID, oi.quantity, oi.note,"
                + "m.item, m.description, m.price, m.category, m.image, m.active "
                + "FROM orders o JOIN order_items oi ON o.orderID = oi.orderID "
                + "JOIN menu m ON oi.itemID = m.itemID "
                + "WHERE o.orderID = ?";
        PreparedStatement ps1 = null, ps2 = null;
        ResultSet rs1 = null, rs2 = null;
        Order o = null;
        Delivery d = null;
        Item i = null;
        List<Item> itemsList = new ArrayList<>(); 
        try {
            ps1 = con.prepareStatement(query1);
            ps1.setInt(1, orderID);
            rs1 = ps1.executeQuery();
            while (rs1.next()) {
                o = new Order();
                d = new Delivery();
                //Order info
                o.setOrderID(rs1.getInt("o.orderID"));
                o.setOrderDate(rs1.getDate("o.order_time"));
                o.setDueDate(rs1.getDate("o.due_date"));
                o.setTotalPrice(rs1.getFloat("o.total_price"));
                o.setOrderStatus(rs1.getString("o.status"));
                o.setPaymentMethod(rs1.getString("o.payment_type"));
                o.setCustomerID(rs1.getInt("d.actorID"));
                //Delivery Info
                d.setDeliveryID(rs1.getInt("d.deliveryID"));
                d.setBuilding(rs1.getString("d.house_number"));
                d.setUnit(rs1.getString("d.unit_number"));
                d.setDeliveryStreet(rs1.getString("d.street"));
                d.setDeliveryPostalCode(rs1.getString("d.postal_code"));
                d.setDeliveryCity(rs1.getString("d.city"));
                d.setDeliveryProvince(rs1.getString("d.province"));
                d.setDeliveryNote(rs1.getString("d.note"));
                d.setRecieverEmail(rs1.getString("d.contact_email"));
                d.setRecieverPhonenumber(rs1.getString("d.phone_number"));
                //Items info
                ps2 = con.prepareStatement(query2);
                ps2.setInt(1, orderID);
                rs2 = ps2.executeQuery();
                while(rs2.next()) 
                {
                    i = new Item();
                    //item info
                    i.setItemId(rs2.getInt("oi.itemID"));
                    i.setQuantity(rs2.getInt("oi.quantity"));
                    i.setItemName(rs2.getString("m.item"));
                    i.setDescription(rs2.getString("d.description"));
                    i.setPrice(rs2.getFloat("m.price"));
                    i.setCategory(rs2.getString("m.category"));
                    i.setImagePath(rs2.getString("m.image"));
                    //Adding item to list
                    itemsList.add(i);
                }
                o.setItemList(itemsList);
            }
            return o;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {

            pool.freeConnection(con);
            try {
                rs2.close();
                ps2.close();
                rs1.close();
                ps1.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        return null;
    }
    
    /**
     * Get only Orders that have their status as RECEIVED and CONFIRMED from the database.
     * 
     * @return
     * @throws NullPointerException 
     */
    public List<Order> getAllReceivedAndConfirmed() throws NullPointerException
    {
        ConnectionPool pool = ConnectionPool.createConnection();
        Connection con = pool.getConnection();
        String query1 = "SELECT o.orderID, o.order_time, o.due_date, o.total_price, o.status, o.payment_type, o.delivery_method, "
                + "d.deliveryID, d.actorID, d.house_number, d.unit_number, d.street, d.postal_code, d.city, d.province, d.note, d.contact_email, d.phone_number "
                + "FROM orders o JOIN delivery d ON o.deliveryID = d.deliveryID "
                + "WHERE o.status = ? OR o.status = ?";
        String query2 = "SELECT oi.itemID, oi.quantity, oi.note,"
                + "m.item, m.description, m.price, m.category, m.image, m.active "
                + "FROM orders o JOIN order_items oi ON o.orderID = oi.orderID "
                + "JOIN menu m ON oi.itemID = m.itemID";
        PreparedStatement ps1 = null, ps2 = null;
        ResultSet rs1 = null, rs2 = null;
        Order o = null;
        Delivery d = null;
        Item i = null;
        List<Item> itemsList = new ArrayList<>(); 
        List<Order> ordersList = new ArrayList<>();
        try {
            ps1 = con.prepareStatement(query1);
            ps1.setString(1, "RECEIVED");
            ps1.setString(2, "CONFIRMED");
            rs1 = ps1.executeQuery();
            while (rs1.next()) {
                o = new Order();
                d = new Delivery();
                //Order info
                o.setOrderID(rs1.getInt("o.orderID"));
                o.setOrderDate(rs1.getDate("o.order_time"));
                o.setDueDate(rs1.getDate("o.due_date"));
                o.setTotalPrice(rs1.getFloat("o.total_price"));
                
                
                Double totalPrice = o.getTotalPrice() * 100;
                totalPrice = Math.ceil(totalPrice) / 100;
                o.setTotalPrice(totalPrice);
                
                o.setOrderStatus(rs1.getString("o.status"));
                o.setPaymentMethod(rs1.getString("o.payment_type"));
                o.setCustomerID(rs1.getInt("d.actorID"));
                //Delivery Info
                d.setDeliveryID(rs1.getInt("d.deliveryID"));
                d.setBuilding(rs1.getString("d.house_number"));
                d.setUnit(rs1.getString("d.unit_number"));
                d.setDeliveryStreet(rs1.getString("d.street"));
                d.setDeliveryPostalCode(rs1.getString("d.postal_code"));
                d.setDeliveryCity(rs1.getString("d.city"));
                d.setDeliveryProvince(rs1.getString("d.province"));
                d.setDeliveryNote(rs1.getString("d.note"));
                d.setRecieverEmail(rs1.getString("d.contact_email"));
                d.setRecieverPhonenumber(rs1.getString("d.phone_number"));
                o.setDeliveryInfo(d);
                //Items info
                ps2 = con.prepareStatement(query2);
                rs2 = ps2.executeQuery();
                while(rs2.next()) 
                {
                    i = new Item();
                    //item info
                    i.setItemId(rs2.getInt("oi.itemID"));
                    i.setQuantity(rs2.getInt("oi.quantity"));
                    i.setItemName(rs2.getString("m.item"));
                    i.setDescription(rs2.getString("m.description"));
                    i.setPrice(rs2.getFloat("m.price"));
                    i.setCategory(rs2.getString("m.category"));
                    i.setImagePath(rs2.getString("m.image"));
                    //Adding item to list
                    itemsList.add(i);
                }
                o.setItemList(itemsList);
                //Adding an order to list
                ordersList.add(o);
            }
            return ordersList;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {

            pool.freeConnection(con);
            try {
                rs2.close();
                ps2.close();
                rs1.close();
                ps1.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        return null;
    }
    

    /**
     * Changes status for a particular Order in the database with the status parameter.
     * @param order
     * @param status
     * @return boolean
     * @throws NullPointerException 
     */
    public boolean changeStatus(Order order, String status) throws NullPointerException {
        ConnectionPool pool = ConnectionPool.createConnection();
        Connection con = pool.getConnection();
        String query = "UPDATE orders SET status = ? WHERE orderID = ?";
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(query);
            ps.setString(1, status);
            ps.setInt(2, order.getOrderID());
            int row = ps.executeUpdate();
            System.out.println("row number " + row);
            
            if(row != 1) 
            {
                return false;
            }
            
            return true; //if row == 1;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {

            pool.freeConnection(con);
            try {
                ps.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        
        return false;
    }
}
