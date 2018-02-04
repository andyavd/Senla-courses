/*Creating schema*/
CREATE SCHEMA IF NOT EXISTS SenlaCourses COLLATE = utf8_general_ci;
USE SenlaCourses;

/*Creating Product table*/
CREATE TABLE IF NOT EXISTS SenlaCourses.product (
  maker VARCHAR(10) NOT NULL,
  model VARCHAR(50) NOT NULL,
  type VARCHAR(50) NOT NULL,
  PRIMARY KEY (model));

/*Creating Laptop table*/  
  CREATE TABLE IF NOT EXISTS SenlaCourses.laptop (
  code INT NOT NULL,
  model VARCHAR(50) NOT NULL,
  speed SMALLINT(4) NOT NULL,
  ram SMALLINT(4) NOT NULL,
  hd REAL NOT NULL,
  price INT NULL,
  screen TINYINT(2) NOT NULL,
  PRIMARY KEY (code),
  FOREIGN KEY (model) REFERENCES product (model));
  
  /*Creating PC table*/
  CREATE TABLE IF NOT EXISTS SenlaCourses.pc (
  code INT NOT NULL,
  model VARCHAR(50) NOT NULL,
  speed SMALLINT(4) NOT NULL,
  ram SMALLINT(4) NOT NULL,
  hd REAL NOT NULL,
  cd VARCHAR(10) NOT NULL,
  price INT NULL,
  PRIMARY KEY (code),
  FOREIGN KEY (model) REFERENCES product (model));
  
  /*Creating Printer table*/
  CREATE TABLE IF NOT EXISTS SenlaCourses.printer (
  code INT NOT NULL,
  model VARCHAR(50) NOT NULL,
  color CHAR(1) NOT NULL,
  type VARCHAR(10) NOT NULL,
  price INT NULL,
  PRIMARY KEY (code),
  FOREIGN KEY (model) REFERENCES product (model));
  
  