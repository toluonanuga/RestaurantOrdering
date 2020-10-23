/*
 * Class Description: This class is the Administrator Model class. 
 */
package com.main.actor;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import services.AdminService;

/**
 * Class Description: This class is the Administrator model class.
 * @author cuong
 *
 */
public class Administrator extends Manager
{
        
        private AdminService as; 
        
	public Administrator(int id, String role, String lastName, String firstName, String street, String houseNumber,
			String unitNumber, String city, String province, String postalCode,String phoneNumber,
			String email, String password, boolean state) {
		super(id, role, lastName, firstName, street, houseNumber, unitNumber, city, province, postalCode, phoneNumber,
				email, password, state);
		// TODO Auto-generated constructor stub
	}
        
        public Administrator()
        {
                
        }
        
        
        public void editAccessibleList(String role, Employee emp) throws NullPointerException, SQLException 
        {
            as = new AdminService();
            as.editActor( emp.getId(), role, emp.getFirstName(), emp.getLastName(), emp.getStreet(),
                    emp.getHouseNumber(), emp.getUnitNumber(), emp.getCity(), emp.getPostalCode(), 
                    emp.getProvince(), emp.getPhoneNumber(), emp.getEmail(), 
                    emp.getPassword());
        }

    /**
     *
     * @return
     * @throws NullPointerException
     */
    public List<Manager> displayAccessibleList() throws NullPointerException 
        {
            as = new AdminService();
            List<Manager> empList = as.getAccessibleList();
            
            return empList;
        }

}
