INSERT INTO actor (email_login, password, phone_number, f_name, l_name, house_number, unit_number, street,
                    postal_code, city, province, role,  active)
VALUES ('admin@admin.com', 
	'password', 
        '4035872525', 
        'adminF', 
        'adminL', 
        '1962', 
        '58', 
        'Southland Dr.', 
        'T2V4S9', 
        'Calgary', 
        'AB', 
        'A', 
        '1');

        INSERT INTO actor (email_login, password, f_name, l_name, phone_number, role,  active)
VALUES ('guest@guest.com', 
        'password',
        'Guest', 
        'Guest', 
        '9999999999', 
        'C', 
        '1');

        INSERT INTO actor (email_login, password, phone_number, f_name, l_name, house_number, unit_number, street,
                    postal_code, city, province, role,  active)
VALUES ('manager@manager.com', 
		'password', 
        '4035872525', 
        'managerF', 
        'managerL', 
        '1962', 
        '58', 
        'Southland Dr.', 
        'T2V4S9', 
        'Calgary', 
        'AB', 
        'M', 
        '1');

        INSERT INTO actor (email_login, password, phone_number, f_name, l_name, house_number, unit_number, street,
                    postal_code, city, province, role,  active)
VALUES ('Employee@Employee.com', 
		'password', 
        '4035872525', 
        'EmployeeF', 
        'EmployeeL', 
        '1962', 
        '58', 
        'Southland Dr.', 
        'T2V4S9', 
        'Calgary', 
        'AB', 
        'E', 
        '1');

        
        INSERT INTO actor (email_login, password, phone_number, f_name, l_name, house_number, unit_number, street,
                    postal_code, city, province, role,  active)
VALUES ('Customer1@Customer.com', 
		'password', 
        '4035872525', 
        'Cust1F', 
        'Cust1L', 
        '1962', 
        '58', 
        'Southland Dr.', 
        'T2V4S9', 
        'Calgary', 
        'AB', 
        'C', 
        '1');


        INSERT INTO actor (email_login, password, phone_number, f_name, l_name, house_number, unit_number, street,
                    postal_code, city, province, role,  active)
VALUES ('Customer2@Customer.com', 
		'password', 
        '4035872525', 
        'Cust2F', 
        'Cust2L', 
        '1962', 
        '58', 
        'Southland Dr.', 
        'T2V4S9', 
        'Calgary', 
        'AB', 
        'C', 
        '1');


        INSERT INTO favourite (actorID, itemID)
VALUES (6, 
		28);
        INSERT INTO favourite (actorID, itemID)
VALUES (6, 
		16);
        INSERT INTO favourite (actorID, itemID)
VALUES (6, 
		7);




        INSERT INTO delivery (actorID, house_number, unit_number, street,
                    postal_code, city, province, note, contact_email, phone_number)
VALUES (5, 
        '1962', 
        '58', 
        'Southland Dr.', 
        'T2V4S9', 
        'Calgary', 
        'AB', 
        'Dingos ate my babies while ordering online.  Would not recommend', 
        'CustomerFake@Customer.com', 
        '4032358264');

        INSERT INTO delivery (actorID, house_number, unit_number, street,
                    postal_code, city, province, note, contact_email, phone_number)
VALUES (6, 
        '2020', 
        '52', 
        'Heritage Rd.', 
        'T2V4S9', 
        'Calgary', 
        'AB', 
        'Allergic to habaneros.  Please do not include.', 
        'CustomerFake5@Customer.com', 
        '4032358262');


        INSERT INTO orders (deliveryID, order_time, due_date, total_price,
                    status, payment_type, delivery_method)
VALUES (2, 
        Now(), 
        Now(), 
        21.89, 
        'RECEIVED', 
        'CASH', 
        'PICKUP');
        
        INSERT INTO order_items (orderID, itemID, quantity, note)
VALUES (1, 
        1, 
        1, 
        'do not think we need notes here');
        
        INSERT INTO order_items (orderID, itemID, quantity, note)
VALUES (1, 
        3, 
        1, 
        'do not think we need notes here');
        
        INSERT INTO order_items (orderID, itemID, quantity, note)
VALUES (1, 
        7, 
        1, 
        'do not think we need notes here');

