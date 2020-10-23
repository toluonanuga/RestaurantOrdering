<<<<<<< HEAD
DROP SCHEMA tandoorigrilldb;
=======
<<<<<<< HEAD
--DROP SCHEMA IF NOT EXISTs tandoorigrilldb;
>>>>>>> origin/ToluBranch
=======
<<<<<<< HEAD
DROP SCHEMA tandoorigrilldb;
=======
--DROP SCHEMA IF NOT EXISTs tandoorigrilldb;
>>>>>>> origin/ToluBranch
>>>>>>> 26c4b8a89cfebe053227d635128c2b9dbbfc6a0a
>>>>>>> origin/chrisfix
CREATE SCHEMA tandoorigrilldb ;
USE tandoorigrilldb;

CREATE TABLE IF NOT EXISTS delivery (
  deliveryID  INT(6) NOT NULL AUTO_INCREMENT,
  actorID INT(6) NOT NULL,
  house_number VARCHAR(20) NOT NULL,
  unit_number VARCHAR(25),
  street VARCHAR(50) NOT NULL,
  postal_code VARCHAR(6) NOT NULL,
  city VARCHAR(50) NOT NULL,
<<<<<<< HEAD
<<<<<<< HEAD
=======
=======
<<<<<<< HEAD
>>>>>>> 26c4b8a89cfebe053227d635128c2b9dbbfc6a0a
>>>>>>> origin/chrisfix
  province CHAR(20) NOT NULL,
  note VARCHAR(300),
  contact_email VARCHAR(50) NOT NULL,
  phone_number VARCHAR(10) NOT NULL,
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
>>>>>>> origin/chrisfix
=======
  province CHAR(2) NOT NULL,
  country VARCHAR(10) NOT NULL,
  note VARCHAR(300),
  contact_email VARCHAR(50) NOT NULL,
>>>>>>> origin/ToluBranch
<<<<<<< HEAD
=======
>>>>>>> 26c4b8a89cfebe053227d635128c2b9dbbfc6a0a
>>>>>>> origin/chrisfix
  PRIMARY KEY (deliveryID));

CREATE TABLE IF NOT EXISTS actor (
  actorID INT(6) NOT NULL AUTO_INCREMENT,
  email_login VARCHAR(50) NOT NULL ,
  password VARCHAR(25) ,
<<<<<<< HEAD
  phone_number VARCHAR(10) NOT NULL,
=======
<<<<<<< HEAD
>>>>>>> origin/ToluBranch
=======
<<<<<<< HEAD
  phone_number VARCHAR(10) NOT NULL,
=======
>>>>>>> origin/ToluBranch
>>>>>>> 26c4b8a89cfebe053227d635128c2b9dbbfc6a0a
>>>>>>> origin/chrisfix
  f_name VARCHAR(25) NOT NULL ,
  l_name VARCHAR(25) NOT NULL,
  house_number VARCHAR(20),
  unit_number VARCHAR(25),
<<<<<<< HEAD
<<<<<<< HEAD
=======
=======
<<<<<<< HEAD
>>>>>>> 26c4b8a89cfebe053227d635128c2b9dbbfc6a0a
>>>>>>> origin/chrisfix
  street VARCHAR(50),
  postal_code VARCHAR(6),
  city VARCHAR(25),
  province CHAR(2),
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
>>>>>>> origin/chrisfix
=======
  postal_code VARCHAR(6),
  city VARCHAR(25),
  province CHAR(2),
  country VARCHAR(10),
>>>>>>> origin/ToluBranch
<<<<<<< HEAD
=======
>>>>>>> 26c4b8a89cfebe053227d635128c2b9dbbfc6a0a
>>>>>>> origin/chrisfix
  role CHAR(1) NOT NULL,
  active TINYINT(1) NOT NULL,
  PRIMARY KEY (actorID));

CREATE TABLE IF NOT EXISTS favourite (
  actorID INT(6),
  itemID INT(6));

CREATE TABLE IF NOT EXISTS report (
  report_made DATETIME,
  total_income FLOAT NOT NULL,
  count_paypal INT(6));

CREATE TABLE IF NOT EXISTS menu (
  itemID INT(6) NOT NULL AUTO_INCREMENT,
  item VARCHAR(50),
  description VARCHAR(500),
  price FLOAT,
<<<<<<< HEAD
  category VARCHAR(40), 
  image VARCHAR(1000),
  active TINYINT(1) NOT NULL,
=======
<<<<<<< HEAD
  cate varchar(10), 
  image varchar(1000),
>>>>>>> origin/ToluBranch
=======
<<<<<<< HEAD
  category VARCHAR(40), 
  image VARCHAR(1000),
  active TINYINT(1) NOT NULL,
=======
  cate varchar(10), 
  image varchar(1000),
>>>>>>> origin/ToluBranch
>>>>>>> 26c4b8a89cfebe053227d635128c2b9dbbfc6a0a
>>>>>>> origin/chrisfix
  PRIMARY KEY (itemID));

CREATE TABLE IF NOT EXISTS order_items (
  orderID INT(6) NOT NULL,
  itemID INT(6) NOT NULL,
  quantity INT(2),
  note VARCHAR(300),
  PRIMARY KEY (orderID, itemID));

CREATE TABLE IF NOT EXISTS orders (
  orderID INT(6) NOT NULL AUTO_INCREMENT,
  deliveryID INT(6),
  order_time DATETIME NOT NULL,
  due_date DATETIME NOT NULL,
  total_price FLOAT NOT NULL,
  status VARCHAR(20) NOT NULL,
  payment_type VARCHAR(20) NOT NULL,
  delivery_method VARCHAR(20) NOT NULL,
<<<<<<< HEAD
<<<<<<< HEAD
=======
  PRIMARY KEY (orderID));
=======
<<<<<<< HEAD
>>>>>>> origin/chrisfix
  PRIMARY KEY (orderID));
=======
  PRIMARY KEY (orderID)) ;
>>>>>>> origin/ToluBranch
<<<<<<< HEAD
=======
>>>>>>> 26c4b8a89cfebe053227d635128c2b9dbbfc6a0a
>>>>>>> origin/chrisfix

ALTER TABLE delivery ADD FOREIGN KEY (actorID) REFERENCES actor(actorID);

ALTER TABLE favourite ADD FOREIGN KEY (actorID) REFERENCES actor(actorID);
ALTER TABLE favourite ADD FOREIGN KEY (itemID) REFERENCES menu(itemID);

ALTER TABLE order_items ADD FOREIGN KEY (orderID) REFERENCES orders(orderID);
ALTER TABLE order_items ADD FOREIGN KEY (itemID) REFERENCES menu(itemID);

ALTER TABLE orders ADD FOREIGN KEY (deliveryID) REFERENCES delivery(deliveryID);


<<<<<<< HEAD
--Edit by Gabriel Ehrman - Date: Feb 25, 2020
=======
<<<<<<< HEAD
>>>>>>> origin/ToluBranch
=======
<<<<<<< HEAD
--Edit by Gabriel Ehrman - Date: Feb 25, 2020
=======
>>>>>>> origin/ToluBranch
>>>>>>> 26c4b8a89cfebe053227d635128c2b9dbbfc6a0a
>>>>>>> origin/chrisfix
