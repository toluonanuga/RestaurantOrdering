/*
 * Class Description: This class acts as the intermediary between the database and Actor services.
 */
package brokers;

import com.main.actor.Actor;
import com.main.actor.Customer;
import com.main.databaseConnection.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;

/**
 * Class Description: This class acts as the intermediary between the database and Actor services.
 * @author Cuong Nguyen, Chris Boot, Nguyen Khanh Duy Phan, Shawn Kaldenbach
 * @version 1.1
 */
public class ActorBroker {

    /**
     * Goes into the database and verifies that the current Actor exists in the
     * database. If they exist, return true. Otherwise return false.
     *
     * @param email 
     * @param password
     * 
     * @return true if the credential exist no, otherwise.
     *
     * @throws NullPointerException
     * @throws SQLException
     */
    public boolean checkCredential(String email, String password) throws NullPointerException, SQLException {
        ConnectionPool.createConnection();
        Connection con = ConnectionPool.getConnection();

        PreparedStatement st = con.prepareStatement("TODO");

        try {
            ResultSet rs = st.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ActorBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
    
    
    public ActorBroker() {
    }

    /**
     * Returns list of all actors.
     *
     * @author Chris
     * @throws SQLException
     */
    public List<Actor> getAll() throws SQLException {
        List<Actor> actorList = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.createConnection();
        Connection con = pool.getConnection();
        
        PreparedStatement st = con.prepareStatement("SELECT * FROM actor");
        ResultSet rs = st.executeQuery();

        while (rs.next()) {

            Actor temp = new Customer();
            int tempID = Integer.parseInt((rs.getString("actorID")));
            temp.setId(tempID);
            temp.setEmail(rs.getString("email_login"));
            temp.setPassword(rs.getString("password"));
            temp.setLastName(rs.getString("l_name"));
            temp.setFirstName(rs.getString("f_name"));
            temp.setHouseNumber(rs.getString("house_number"));
            temp.setUnitNumber(rs.getString("unit_number"));
            temp.setPostalCode(rs.getString("postal_code"));
            temp.setCity(rs.getString("city"));
            temp.setProvince(rs.getString("province"));
            temp.setRole(rs.getString("role"));
            temp.setPhoneNumber(rs.getString("phone_number"));
            temp.setStreet(rs.getString("street"));
            int i = Integer.parseInt(rs.getString("active"));
            if (i == 1) {
                temp.setState(true);
            } else {
                temp.setState(false);
            }

            actorList.add(temp);
        }
        pool.freeConnection(con);
        st.close();
        return actorList;
    }

    /**
     * Gets the ID of the actor based on their email.
     * 
     * 
     * @param actorEmail
     * 
     * @return ID
     */
    public int getActorID(String actorEmail) 
    {
        ConnectionPool pool = ConnectionPool.createConnection();
        Connection con = pool.getConnection();
        PreparedStatement st = null;
        try {
            st = con.prepareStatement("SELECT actorID FROM actor WHERE email_login = ?");
            st.setString(1, actorEmail);
            ResultSet rs = st.executeQuery();
            rs.next();
            return rs.getInt("actorID");
        } catch (SQLException ex) {
            Logger.getLogger(ActorBroker.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            pool.freeConnection(con);
            try {
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(AdminBroker.class.getName()).log(Level.SEVERE, null, ex);
            }
        return 0;
        }
    }
}