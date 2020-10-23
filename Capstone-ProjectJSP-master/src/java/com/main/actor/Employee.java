/*
 * Class Description: This class is the Employee model class. 
 */
package com.main.actor;

import java.sql.Date;

/**
 * Class Description: This class is the Employee model class.
 * @author cuong
 *
 */
public class Employee extends Actor
{

	public Employee(int id, String role, String lastName, String firstName, String street, String houseNumber,
			String unitNumber, String city, String province, String postalCode, String phoneNumber,
			String email, String password, boolean state) {
		super(id, role, lastName, firstName, street, houseNumber, unitNumber, city, province, postalCode, phoneNumber,
				email, password, state);
	}
        
        public Employee()
        {
            
        }



}
