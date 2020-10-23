/*
 * Class Description: This class acts as the intermediary between the database and Favourites Servlet.
 */
package brokers;

import com.main.databaseConnection.ConnectionPool;
import com.main.menu.Item;
import com.main.menu.Reorder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class Description: This class acts as the intermediary between the database and Favourites Servlet.
 * @author Gabriel Ehrman
 * @version 1.1
 */
public class FavouritesBroker {

    /**
     * Gets all favourite items from the database using an actorID.
     * @param actorId
     * @return
     * @throws NullPointerException 
     */
    public ArrayList<Item> getItemsByActorId(int actorId) throws NullPointerException {
        ConnectionPool pool = ConnectionPool.createConnection();
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Item> itemList = new ArrayList<>();
        String query = "SELECT * FROM favourite WHERE actorID = ?";

        MenuBroker mb = new MenuBroker();
        int id;
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, actorId);
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt("itemID");
                itemList.add(mb.getItemById(id));
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
        return itemList;
    }

    /**
     * Gets all previously ordered Orders as Reorders from the database using an actorID.
     * @param actorID
     * @return 
     */
    public ArrayList<Reorder> getOrdersByActorId(int actorID) {
        ConnectionPool pool = ConnectionPool.createConnection();
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Item> itemList = new ArrayList<>();
        ArrayList<Reorder> orderList = new ArrayList<>();
        MenuBroker mb = new MenuBroker();
        String query = "SELECT orderID, total_price FROM orders where deliveryID = (SELECT deliveryID FROM delivery WHERE actorID = ?)";
        String subQuery = "SELECT itemID, quantity FROM order_items WHERE orderID = ?";
        PreparedStatement subPS = null;
        ResultSet subRS = null;
        Item i = null;
        Reorder reorder;
        try {
            ps = con.prepareStatement(query);
            subPS = con.prepareStatement(subQuery);
            ps.setInt(1, actorID);
            rs = ps.executeQuery();
            while (rs.next()) {
                reorder = new Reorder();
                reorder.setOrderID(rs.getInt("orderID"));
                reorder.setTotalPrice(rs.getFloat("total_price"));
                subPS.setInt(1, reorder.getOrderID());
                subRS = subPS.executeQuery();
                while (subRS.next()) {
                   i = mb.getItemById(subRS.getInt("itemID"));
                   i.setQuantity(subRS.getInt("quantity"));
                   itemList.add(i);
                }
                reorder.setItemList(itemList);
                orderList.add(reorder);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FavouritesBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return orderList;
    }
}
