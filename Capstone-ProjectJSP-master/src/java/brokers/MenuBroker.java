/*
 * Class Description: This class acts as the intermediary between the database and Menu services.
 */
package brokers;

import com.main.databaseConnection.ConnectionPool;
import com.main.menu.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Class Description: This class acts as the intermediary between the database and Menu services.
 * 
 * @author Nguyen Khanh Duy Phan
 * @version 1.1
 */
public class MenuBroker {

    /**
     * Gets all menu Items from the database.
     * @return 
     */
    public List<Item> getAllItem() {
        ConnectionPool pool = ConnectionPool.createConnection();
        Connection con = pool.getConnection();
        String query = "SELECT * FROM menu";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Item i = null;
        List<Item> listItems = new ArrayList<>();
        try 
        {
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                i = new Item();
                i.setItemId(rs.getInt("itemID"));
                i.setItemName(rs.getString("item"));
                i.setDescription(rs.getString("description"));
                i.setPrice(rs.getDouble("price"));
                i.setCategory( rs.getString("category"));
                i.setImagePath(rs.getString("image"));
                if(rs.getInt("active") == 1)
                {
                     listItems.add(i);
                }
               
            }
            return listItems;
        }
        catch (SQLException e) {
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
        return null;
    }

    /**
     * Updates an Item in the database with the param Item.
     * 
     * @param item
     * @return boolean
     * @throws NullPointerException 
     */
    public boolean updateItem(Item item) throws NullPointerException {
        ConnectionPool pool = ConnectionPool.createConnection();
        Connection con = pool.getConnection();
        String query = "UPDATE menu SET item = ?, description = ?, price = ?, "
                + "category = ?, image = ? WHERE itemID = ? ";
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(query);
            ps.setString(1, item.getItemName());
            ps.setString(2, item.getDescription());
            ps.setDouble(3, item.getPrice());
            ps.setString(4, item.getCategory());
            ps.setString(5, item.getImagePath());
            ps.setInt(6, item.getItemId());
            int row = ps.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        } finally {

            pool.freeConnection(con);
            try {
                ps.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        return true;

    }

    /**
     * Sets an Item to an inactive state in the database.
     * No item can be should be fully deleted.
     * @param item
     * @return
     * @throws NullPointerException 
     */
    public boolean deleteItem(Item item) throws NullPointerException {
        ConnectionPool pool = ConnectionPool.createConnection();
        Connection con = pool.getConnection();
        String query = "UPDATE menu SET active = 0 WHERE itemID = ? ";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, item.getItemId());
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        } finally {

            pool.freeConnection(con);
            try {
                ps.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    /**
     * Get an Item from the database using an itemID.
     * 
     * @param itemId
     * @return
     * @throws NullPointerException 
     */
    public Item getItemById(int itemId) throws NullPointerException {
        ConnectionPool pool = ConnectionPool.createConnection();
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Item i = null;
        String query = "SELECT * FROM menu WHERE itemID = ?";

        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, itemId);
            rs = ps.executeQuery();
            while (rs.next()) {
                i = new Item();
                i.setItemId(rs.getInt("itemID"));
                i.setItemName(rs.getString("item"));
                i.setDescription(rs.getString("description"));
                i.setPrice(rs.getDouble("price"));
                i.setCategory( rs.getString("category"));
                i.setImagePath(rs.getString("image"));
            }
    

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {

            pool.freeConnection(con);
            try {
                rs.close();
                ps.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        return i;

    }
        /**
         * Gets an Item from the database by itemName.
         * 
         * @param itemName
         * @return
         * @throws NullPointerException 
         */
        public Item getItemByName(String itemName) throws NullPointerException {
        ConnectionPool pool = ConnectionPool.createConnection();
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Item i = null;
        String query = "SELECT * FROM menu WHERE item = ?";

        try {
            ps = con.prepareStatement(query);
            ps.setString(1, itemName);
            rs = ps.executeQuery();
            while (rs.next()) {
                i = new Item();
                i.setItemId(rs.getInt("itemID"));
                i.setItemName(rs.getString("item"));
                i.setDescription(rs.getString("description"));
                i.setPrice(rs.getDouble("price"));
                i.setCategory( rs.getString("category"));
                i.setImagePath(rs.getString("image"));
            }
    

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {

            pool.freeConnection(con);
            try {
                rs.close();
                ps.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        return i;
    }
    
    
    /**
     * Inserts a new Item into the database using the Item parameter.
     * @param item
     * @return boolean
     * @throws NullPointerException 
     */
    public boolean insertItem(Item item) throws NullPointerException {
        ConnectionPool pool = ConnectionPool.createConnection();
        Connection con = pool.getConnection();
        PreparedStatement ps = null;

        String query = "INSERT INTO menu (item, description, price,category, image, active) VALUES (?,?,?,?,?,?)";

        try {
            ps = con.prepareStatement(query);
            ps.setString(1, item.getItemName());
            ps.setString(2, item.getDescription());
            ps.setDouble(3, item.getPrice());
            ps.setString(4, item.getCategory());
            ps.setString(5, item.getImagePath());
            ps.setInt(6, 1);
            ps.executeUpdate();

        } catch (SQLException e) {
           
            e.printStackTrace();
            return false;
        } finally {
            pool.freeConnection(con);
            try {
                ps.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
        return true;

    }
}
