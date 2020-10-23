/*
 * Class Description: This class acts as the intermediary for the database and admin-only services.
 */
package brokers;

import com.main.actor.Actor;
import com.main.actor.Administrator;
import com.main.actor.Customer;
import com.main.actor.Employee;
import com.main.actor.Manager;
import com.main.databaseConnection.ConnectionPool;
import com.main.delivery.Delivery;
import com.main.menu.Item;
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
 * Class Description: This class acts as the intermediary for the database and admin-only services.
 * @author cuong
 * @version 1.1
 */
public class AdminBroker {

    /**
     * this method use to get Actor by email
     *
     * @param email
     * @return
     */
    public Actor getActorbyEmail(String email) {
        ConnectionPool pool = ConnectionPool.createConnection();
        Connection con = pool.getConnection();
        Actor actor = null;
        String actorRole = null;
        String query = "SELECT * FROM actor WHERE email_login = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();

            while (rs.next()) {
                actorRole = rs.getString("role");

                if (actorRole.equalsIgnoreCase("e") == true) {
                    actor = new Employee();

                } else if (actorRole.equalsIgnoreCase("c") == true || actorRole.equalsIgnoreCase("g")) {
                    actor = new Customer();

                } else if (actorRole.equalsIgnoreCase("m") == true) {
                    actor = new Manager();
                } else if (actorRole.equalsIgnoreCase("a")) {
                    actor = new Administrator();
                }
                actor.setId(rs.getInt("actorID"));
                actor.setEmail(rs.getString("email_login"));
                actor.setPassword(rs.getString("password"));
                actor.setFirstName(rs.getString("f_name"));
                actor.setLastName(rs.getString("l_name"));
                actor.setHouseNumber(rs.getString("house_number"));
                actor.setUnitNumber(rs.getString("unit_number"));
                actor.setPostalCode(rs.getString("postal_code"));
                actor.setCity(rs.getString("city"));
                actor.setProvince(rs.getString("province"));
                actor.setRole(rs.getString("role"));
                actor.setStreet(rs.getString("street"));
                actor.setPhoneNumber(rs.getString("phone_number"));
                if(rs.getInt("active") ==1 )
                {
                    actor.setState(true);
                }
                else
                {
                    actor.setState(false);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(AdminBroker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.freeConnection(con);
            try {
                rs.close();
                ps.close();

            } catch (SQLException ex) {
                Logger.getLogger(AdminBroker.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return actor;
    }

    /**
     * This method gets all Actors in the database.
     * @return @throws SQLException
     */
    public List<Actor> getAll() throws SQLException {
        List<Actor> actorList = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.createConnection();
        Connection con = pool.getConnection();

        PreparedStatement st = con.prepareStatement("SELECT * FROM actor");
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            String actorRole = rs.getString("role");
            Actor actor = null;
            if (actorRole.equalsIgnoreCase("e") == true) {
                    actor = new Employee();

                } else if (actorRole.equalsIgnoreCase("c") == true || actorRole.equalsIgnoreCase("g")) {
                    actor = new Customer();

                } else if (actorRole.equalsIgnoreCase("m") == true) {
                    actor = new Manager();
                } else if (actorRole.equalsIgnoreCase("a")) {
                    actor = new Administrator();
                }
            
            actor.setId(rs.getInt("actorID"));
            actor.setEmail(rs.getString("email_login"));
            actor.setPassword(rs.getString("password"));
            actor.setLastName(rs.getString("l_name"));
            actor.setFirstName(rs.getString("f_name"));
            actor.setHouseNumber(rs.getString("house_number"));
            actor.setUnitNumber(rs.getString("unit_number"));
            actor.setPostalCode(rs.getString("postal_code"));
            actor.setCity(rs.getString("city"));
            actor.setProvince(rs.getString("province"));
            actor.setRole(rs.getString("role"));
            actor.setStreet(rs.getString("street"));
            actor.setPhoneNumber(rs.getString("phone_number"));
            int i = Integer.parseInt(rs.getString("active"));
            if (i == 1) {
                actor.setState(true);
            } else {
                actor.setState(false);
            }

            actorList.add(actor);
        }


        return actorList;
    }

    /**
     * 
     * This method gets all Managers from the database
     * 
     * @author Nguyen Khanh Duy (Andrew) Phan
     * @return managerList
     * @throws SQLException
     */
    public List<Manager> getAllManager() throws SQLException {
        List<Manager> managerList = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.createConnection();
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement("SELECT * FROM actor WHERE role= m");
            rs = ps.executeQuery();

            while (rs.next()) {
                Manager manager = new Manager();
                int managerID = Integer.parseInt(rs.getString("actorID"));
                manager.setId(managerID);
                manager.setRole(rs.getString("role"));
                manager.setLastName(rs.getString("l_name"));
                manager.setFirstName(rs.getString("f_name"));
                manager.setHouseNumber(rs.getString("house_number"));
                manager.setUnitNumber(rs.getString("unit_number"));
                manager.setPostalCode(rs.getString("postal_code"));
                manager.setCity(rs.getString("city"));
                manager.setProvince(rs.getString("province"));
                manager.setPhoneNumber(rs.getString("phone_number"));
                manager.setStreet(rs.getString("street"));
                int i = Integer.parseInt(rs.getString("active"));
                if (i == 1) {
                    manager.setState(true);
                } else {
                    manager.setState(false);
                }

                managerList.add(manager);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {

            pool.freeConnection(con);
            try {
                ps.close();
                rs.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return managerList;
    }

    /**
     * 
     * Method that inserts a new Actor into the database
     * 
     * @author Nguyen Khanh Duy (Andrew) Phan
     * @return int rows inserted
     * @throws NullPointerException
     */
    public int insertActor(Actor newActor) throws NullPointerException {
        if (newActor == null) {
            return 0;
        }
        int rows = 0;
        ConnectionPool pool = ConnectionPool.createConnection();
        Connection con = ConnectionPool.getConnection();
        String query = "INSERT INTO actor(email_login, password, phone_number, f_name, l_name," //4
                + "house_number, unit_number, street, postal_code, city, province, role, active)"//8
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, newActor.getEmail());
            ps.setString(2, newActor.getPassword());
            ps.setString(3, newActor.getPhoneNumber());
            ps.setString(4, newActor.getFirstName());
            ps.setString(5, newActor.getLastName());
            ps.setString(6, newActor.getHouseNumber());
            ps.setString(7, newActor.getUnitNumber());
            ps.setString(8, newActor.getStreet());
            ps.setString(9, newActor.getPostalCode());
            ps.setString(10, newActor.getCity());
            ps.setString(11, newActor.getProvince());
            ps.setString(12, newActor.getRole());
            ps.setInt(13, 1);
            rows = ps.executeUpdate();
            return rows;
        } catch (SQLException ex) {
            Logger.getLogger(AdminBroker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            pool.freeConnection(con);
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(AdminBroker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

    /**
     * Gets an Actor from their ID from the database.
     * 
     * @author Nguyen Khanh Duy (Andrew) Phan
     * @return Actor
     * @throws SQLException
     * @throws NullPointerException
     */
    public Actor getActorByID(int actorID) throws SQLException, NullPointerException {
        ConnectionPool pool = ConnectionPool.createConnection();
        Connection con = pool.getConnection();
        Actor actor = null;
        String actorRole = null;
        String query = "SELECT * FROM actor WHERE actorID = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, actorID);
            rs = ps.executeQuery();

            while (rs.next()) {
                actorRole = rs.getString("role");

                if (actorRole.equalsIgnoreCase("e") == true) {
                    actor = new Employee();

                } else if (actorRole.equalsIgnoreCase("c") == true || actorRole.equalsIgnoreCase("g")) {
                    actor = new Customer();

                } else if (actorRole.equalsIgnoreCase("m") == true) {
                    actor = new Manager();
                } else if (actorRole.equalsIgnoreCase("a")) {
                    actor = new Administrator();
                }
                actor.setId(rs.getInt("actorID"));
                actor.setEmail(rs.getString("email_login"));
                actor.setPassword(rs.getString("password"));
                actor.setFirstName(rs.getString("f_name"));
                actor.setLastName(rs.getString("l_name"));
                actor.setHouseNumber(rs.getString("house_number"));
                actor.setUnitNumber(rs.getString("unit_number"));
                actor.setPostalCode(rs.getString("postal_code"));
                actor.setCity(rs.getString("city"));
                actor.setProvince(rs.getString("province"));
                actor.setRole(rs.getString("role"));
                actor.setPhoneNumber(rs.getString("phone_number"));
                actor.setStreet(rs.getString("street"));
                actor.setState(rs.getBoolean("active"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(AdminBroker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.freeConnection(con);
            try {
                rs.close();
                ps.close();

            } catch (SQLException ex) {
                Logger.getLogger(AdminBroker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return actor;
    }


    /**
     * Updates a particular Actor in the database.
     * 
     * @param a
     * @return rows updated
     * @throws NullPointerException
     * @throws SQLException 
     */
    public int updateActor(Actor a) throws NullPointerException, SQLException {
        ConnectionPool pool = ConnectionPool.createConnection();
        Connection con = pool.getConnection();
        String query = "UPDATE actor "
                + "SET email_login = ?, password = ?, f_name = ?, l_name = ?,"
                + "house_number = ?, unit_number = ?, postal_code = ?, city = ?,"
                + "province = ?, role = ?, phone_number = ?, street = ?"
                + "WHERE actorID = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        int rows = 0;

        try {
            ps = con.prepareStatement(query);

            ps.setString(1, a.getEmail());
            ps.setString(2, a.getPassword());
            ps.setString(3, a.getFirstName());
            ps.setString(4, a.getLastName());
            ps.setString(5, a.getHouseNumber());
            ps.setString(6, a.getUnitNumber());
            ps.setString(7, a.getPostalCode());
            ps.setString(8, a.getCity());
            ps.setString(9, a.getProvince());
            ps.setString(10, a.getRole());
             ps.setString(11, a.getPhoneNumber());
             ps.setString(12, a.getStreet());
            ps.setInt(13, a.getId());

            rows = ps.executeUpdate();
            return rows;
        } catch (SQLException ex) {
            Logger.getLogger(AdminBroker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.freeConnection(con);
            try {
                rs.close();
                ps.close();

            } catch (SQLException ex) {
                Logger.getLogger(AdminBroker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }


    /**
     * Deletes all traces of an Actor from the database
     * 
     * @param actorID
     * @return 
     */
    public boolean deleteActor(int actorID)  {
        ConnectionPool pool = ConnectionPool.createConnection();
        Connection con = pool.getConnection();
        String query1 = "DELETE FROM favourite WHERE actorID = ?";
        String query2 = "DELETE order_items  FROM actor " +
                        "JOIN delivery using (actorID)  " +
                        "JOIN orders USING (deliveryID) " +
                        "JOIN order_items USING (orderID) WHERE actorID = ?";
        String query3 = "DELETE orders  FROM actor " +
                        "JOIN delivery using (actorID) " +
                        "JOIN orders USING (deliveryID) WHERE actorID = ?";
        String query4 = "DELETE delivery  FROM actor " +
                        "JOIN delivery using (actorID) " +
                        "WHERE actorID = ?";
        String query5 = "DELETE  FROM actor  " +
                        "WHERE actorID = ?";
        
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        PreparedStatement ps3 = null;
        PreparedStatement ps4 = null;
        PreparedStatement ps5 = null;
        
        try {
            ps1 = con.prepareStatement(query1);
            ps1.setInt(1, actorID);
            ps1.executeUpdate();
            
            ps2 = con.prepareStatement(query2);
            ps2.setInt(1, actorID);
            ps2.executeUpdate();
            
            ps3 = con.prepareStatement(query3);
            ps3.setInt(1, actorID);
            ps3.executeUpdate();
            
            ps4 = con.prepareStatement(query4);
            ps4.setInt(1, actorID);
            ps4.executeUpdate();
            
            ps5 = con.prepareStatement(query5);
            ps5.setInt(1, actorID);
            ps5.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally{
            pool.freeConnection(con);
            try {
                ps1.close();
                ps2.close();
                ps3.close();
                ps4.close();
                ps5.close();
            } catch (SQLException ex) {
                Logger.getLogger(AdminBroker.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return true;
    }

    /**
     * Gets all actors by first name, last name, and role.
     * 
     * @param firstname
     * @param lastname
     * @param role
     * @author Nguyen Khanh Duy (Andrew) Phan
     * @return actorList
     * @throws SQLException
     * @throws NullPointerException
     */
    public List<Actor> getActorsByName(String firstname, String lastname, String role) throws SQLException, NullPointerException {
        List<Actor> actorList = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.createConnection();
        Connection con = pool.getConnection();
        String query = "SELECT * FROM actor WHERE f_name = ?, l_name = ?, role = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Actor actor = new Actor();
            if (role.equalsIgnoreCase("e") == true) {
                    actor = new Employee();

                } else if (role.equalsIgnoreCase("c") == true || role.equals("g")) {
                    actor = new Customer();

                } else if (role.equalsIgnoreCase("m") == true) {
                    actor = new Manager();
                } else if (role.equalsIgnoreCase("a")) {
                    actor = new Administrator();
                }
                actor.setId(rs.getInt("actorID"));
                actor.setEmail(rs.getString("email_login"));
                actor.setPassword(rs.getString("password"));
                actor.setLastName(rs.getString("l_name"));
                actor.setFirstName(rs.getString("f_name"));
                actor.setHouseNumber(rs.getString("house_number"));
                actor.setUnitNumber(rs.getString("unit_number"));
                actor.setPostalCode(rs.getString("postal_code"));
                actor.setCity(rs.getString("city"));
                actor.setProvince(rs.getString("province"));
                actor.setPhoneNumber(rs.getString("phone_number"));
                actor.setRole(rs.getString("role"));

                actor.setState(rs.getBoolean("active"));

                actorList.add(actor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminBroker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.freeConnection(con);
            try {
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(AdminBroker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return actorList;
    }
    
    /**
     * Gets ALL past orders from the database
     * 
     * @return 
     */
    public List<Order> getAllPastOrder()
    {
        ConnectionPool pool = ConnectionPool.createConnection();
        Connection con = ConnectionPool.getConnection();
        List<Order> orderList = new ArrayList<>();
        String query = "Select od.orderID, od.order_time, od.due_date, od.total_price, d.deliveryID, " +
                "od.status, od.payment_type, od.delivery_method, d.house_number, d.unit_number, " +
                "d.street, d.postal_code, d.city, d.province, d.note, d.contact_email, d.actorID, d.phone_number, a.f_name, a.l_name " +
                " FROM orders od JOIN delivery d ON od.deliveryID = d.deliveryID " +
                "JOIN actor a ON d.actorID = a.actorID "+
                "WHERE 1 = 1";
        String selectOrderItemQuery = "SELECT oi.itemID, oi.quantity, m.item, m.description, m.category, m.image, m.price "
                                       +"FROM order_items oi JOIN menu m ON oi.itemID = m.itemID WHERE oi.orderID = ?";
        PreparedStatement ps = null;
        PreparedStatement psItem = null;
        ResultSet rsItem = null;
        
        ResultSet rs = null;
        
        try {
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                Order order = new Order();
                Delivery d = new Delivery();
                order.setOrderID(rs.getInt("od.orderID"));
                order.setOrderDate(rs.getDate("od.order_time"));
                order.setDueDate(rs.getDate("od.due_date"));
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
                //add order to the list
                orderList.add(order);
                
                
            }  
            for(int k = 0; k < orderList.size(); k++)
            {
                List<Item> orderItemList = new ArrayList<>();
                psItem = con.prepareStatement(selectOrderItemQuery);
                psItem.setInt(1, orderList.get(k).getOrderID());
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
                    item.setPrice(rsItem.getDouble("m.price"));
                    orderItemList.add(item);
                }
                orderList.get(k).setItemList(orderItemList);
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
}
