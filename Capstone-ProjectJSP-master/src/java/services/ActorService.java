/*
 * Class Description: This class acts as the intermediary between the database broker and Actor Servlets.
*/
package services;

import brokers.AdminBroker;
import com.main.actor.Actor;

/**
 * Class Description: This class acts as the intermediary between the database broker and Actor Servlets.
 * 
 * @author cuong
*/
public class ActorService {
    
    /**
     * Verifies that the Actor exists within the system using their email and password.
     * @param email
     * @param password
     * @return 
     */
    public boolean checkCredential(String email, String password)
    {
       AdminBroker ab = new AdminBroker();
       Actor actor = ab.getActorbyEmail(email);
       if ( actor == null) 
       {
           return false;
       }
       else 
       {
           if(password.equals(actor.getPassword()) && actor.isState() == true)
           {
               return true;
           }
           else
           {
               return false;
           }
       } 
    }
    
    /**
     * Verifies that the current Actor is a valid Manager within the system using their email and password.
     * @param email
     * @param password
     * @return 
     */
    public boolean checkCredentialManager(String email, String password)
    {
       AdminBroker ab = new AdminBroker();
       Actor actor = ab.getActorbyEmail(email);
       if ( actor == null) 
       {
           return false;
       }
       else 
       {
           if(password.equals(actor.getPassword()) && actor.isState() == true && actor.getRole().equalsIgnoreCase("M"))
           {
               return true;
           }
           else
           {
               return false;
           }
       } 
    }
    
    
    
}
