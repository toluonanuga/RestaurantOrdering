/*
 * Class Description: This class acts as the intermediary between the database broker and Admin Servlets.
 */
package services;

import brokers.AdminBroker;
import com.main.actor.*;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class Description: This class acts as the intermediary between the database broker and Admin Servlets.
 *
 * @author 763199 - Nguyen Khanh Duy Phan
 */
public class AdminService {
    List<Manager> accessibleList = null;
    List<Employee> employeeList = null;
    List<Customer> customerList = null;
    List<Actor> checkList = null;
    AdminBroker ab = new AdminBroker();
    
    /**
     * Makes sure that the current Actor has access to a specific area.
     * @param id
     * @return boolean
     * @throws NullPointerException 
     */
    public boolean validateAccess(int id) throws NullPointerException
    {
        try 
        {
            checkList = ab.getAll();
            for(int i = 0; i < checkList.size(); i++) 
            {
                if(checkList.get(i).getId() == id) 
                {
                    return true;
                }
            }
        }
        catch(NumberFormatException e) 
        {
            System.out.println("Invalid Number Format");
        } 
        catch (SQLException ex) {
            Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    /**
     * Gets a list of all Managers and makes sure they're valid.
     * @return 
     */
    public List<Manager> getAccessibleList() 
    {
        try {
            accessibleList = ab.getAllManager();
        } catch (SQLException ex) {
            Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return accessibleList;
    }
    
    /**
     * Begins adding a new Actor based on the params and makes sure that the Actor was added successfully.
     * 
     * @param role
     * @param firstname
     * @param lastname
     * @param street
     * @param houseNumber
     * @param unitNumber
     * @param city
     * @param postalCode
     * @param province
     * @param phoneNumber
     * @param email
     * @param password
     * @throws NullPointerException 
     */
    public void createActor(String role, String  firstname, String lastname, String street, String houseNumber, 
            String unitNumber, String city, String postalCode, String province, String  phoneNumber, String email, String password) throws NullPointerException
    {
        Actor newActor = new Actor(0, role, lastname, firstname, street, houseNumber,
			unitNumber, city, province, postalCode, phoneNumber,
			email, password, true);
        int rows = ab.insertActor(newActor);
        if(rows == 1) 
        {
            System.out.println("1 row inserted");
        }
        else
        {
            System.out.println("Cannot insert actor");
        }
    }
    
    /**
     * Begins updating an Actor with an Actor param and verifying that the information is fine and correctly updated.
     * @param actor
     * @return boolean
     */
    public boolean editActor(Actor actor)
    {
        int rows = 0;
        try
        {
            rows = ab.updateActor(actor);
            
        }catch (SQLException sqlException)
        {
            System.out.println("Cannot update actor");
            return false;
        }
        
        if(rows == 1) 
        {
            System.out.println("1 row updated");
            return true;
        }
        else
        {
            System.out.println("Cannot update actor");
            return false;
        }
    }
    
    /**
     * Begins updating an Actor with all parameters and that it has been verified and correctly updated.
     * 
     * @param actorID
     * @param role
     * @param firstname
     * @param lastname
     * @param street
     * @param houseNumber
     * @param unitNumber
     * @param city
     * @param postalCode
     * @param province
     * @param phoneNumber
     * @param email
     * @param password
     * @throws NullPointerException
     * @throws SQLException 
     */
    public void editActor(int actorID, String role, String  firstname, String lastname, String street, 
            String houseNumber, String unitNumber, String city, String postalCode, String province, String phoneNumber, 
            String email, String password) throws NullPointerException, SQLException
    {
        Actor editA = ab.getActorByID(actorID);
        editA.setRole(role);
        editA.setFirstName(firstname);
        editA.setLastName(lastname);
        editA.setStreet(street);
        editA.setHouseNumber(houseNumber);
        editA.setUnitNumber(unitNumber);
        editA.setCity(city);
        editA.setPostalCode(postalCode);
        editA.setProvince(province);
        editA.setPhoneNumber(phoneNumber);
        editA.setEmail(email);
        editA.setPassword(password);
        
        int rows = ab.updateActor(editA);
        if(rows == 1) 
        {
            System.out.println("1 row updated");
        }
        else
        {
            System.out.println("Cannot update actor");
        }
    }
    
    /**
     * Begins the Actor deletion process and verifies that the process was correctly done.
     * @param actorID
     * @return boolean
     */
    public boolean deleteActor(int actorID) 
    {
        return ab.deleteActor(actorID);
    }
   
    /**
     * Gets an Actor based on an ActorID and role and verifies that they exist.
     * 
     * @param actorID
     * @param role
     * @return
     * @throws SQLException
     * @throws NullPointerException 
     */
    public Actor displayActor(int actorID, String role) throws SQLException, NullPointerException 
    {
        Actor getA = ab.getActorByID(actorID);
        return getA;
    }
    
    /**
     * Gets an Actor based on a first name, last name, and role and verifies that they exist.
     * 
     * @param firstName
     * @param lastName
     * @param role
     * @return
     * @throws NullPointerException
     * @throws SQLException 
     */
    public List<Actor> displayActor(String firstName, String lastName, String role) throws NullPointerException, SQLException 
    {
        List<Actor> actorList = ab.getActorsByName(firstName, lastName, role);
        return actorList;
    }
    
    /**
     * Gets all Actors.
     * @return
     * @throws SQLException 
     */
    public List<Actor> getAllActor() throws SQLException
    {
        return ab.getAll();
    }
}
