/*Creating schema*/
CREATE SCHEMA IF NOT EXISTS hotel COLLATE = utf8_general_ci;
USE hotel;
 
/*Creating Rooms table*/  
CREATE TABLE IF NOT EXISTS hotel.room (
  id TINYINT(3) NOT NULL AUTO_INCREMENT,
  room_number TINYINT(3) NOT NULL,
  capacity TINYINT(1) NOT NULL,
  daily_price DOUBLE NOT NULL,
  stars ENUM('Standard','Juniour_Suite','Lux','President_Lux') NOT NULL,
  room_status ENUM('Empty','Occupied','Serviced') NOT NULL DEFAULT 'Empty',
  PRIMARY KEY (id)); 

/*Creating Visitors table*/
CREATE TABLE IF NOT EXISTS hotel.visitor (
  id TINYINT(3) NOT NULL AUTO_INCREMENT,
  visitor_name VARCHAR(45) NOT NULL UNIQUE,
  PRIMARY KEY (id)); 

/*Creating Services table*/
CREATE TABLE IF NOT EXISTS hotel.service (
  id TINYINT(3) NOT NULL AUTO_INCREMENT,
  service_name VARCHAR(45) NOT NULL,
  daily_price DOUBLE NOT NULL,
  PRIMARY KEY (id));

/*Creating History table*/
CREATE TABLE IF NOT EXISTS hotel.history (
  id TINYINT(3) NOT NULL AUTO_INCREMENT,
  room_id TINYINT(3) NOT NULL,
  visitor_id TINYINT(3) NOT NULL,
  visitor_service_id TINYINT(3) NULL,
  check_in DATE NOT NULL,
  check_out DATE NOT NULL,
  history_status ENUM('CheckIn','CheckOut') NOT NULL DEFAULT 'CheckOut',
  PRIMARY KEY (id),
  FOREIGN KEY (room_id) REFERENCES room (id),
  FOREIGN KEY (visitor_id) REFERENCES visitor (id));

/*Creating visitor_service table*/
CREATE TABLE IF NOT EXISTS visitor_service (
id TINYINT(3) NOT NULL AUTO_INCREMENT,
history_id TINYINT(3) NOT NULL,
service_id TINYINT(3) NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (service_id) REFERENCES service(id),
FOREIGN KEY (history_id) REFERENCES history(id));

ALTER TABLE history ADD FOREIGN KEY (visitor_service_id) REFERENCES visitor_service (history_id);
