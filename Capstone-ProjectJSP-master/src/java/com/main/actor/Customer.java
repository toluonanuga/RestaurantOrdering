/*
 * Class Description: This class is the Customer model class. 
 */
package com.main.actor;

import java.sql.Date;
import java.util.List;

import com.main.menu.Item;
import com.main.menu.Order;



/**
 * Class Description: This class is the Customer model class.
 * @author cuong
 *
 */
public class Customer extends Actor
{
	



	
	public Customer(int id, String role, String lastName, String firstName, String street, String houseNumber,
			String unitNumber, String city, String province, String postalCode, String country, String phoneNumber,
			String email, String password, boolean state) {
		super(id, role, lastName, firstName, street, houseNumber, unitNumber, city, province, postalCode, phoneNumber,
				email, password, state);
	}
        
        
        public Customer()
        {
            
        }
	

	
}
