/*Inserting data into the Product table*/
INSERT INTO SenlaCourses.product (maker, model, type) VALUES
('Apple', 'MacBook Air 11', 'Laptop'),
('Apple', 'MacBook Air 13', 'Laptop'),
('HP', 'ProBook 4320', 'Laptop'),
('HP', 'Pavilion', 'Laptop'),
('ASUS', 'VivoBook 15', 'Laptop'),
('ASUS', 'ZenBook UX', 'Laptop'),
('Xiaomi', 'Mi Book Air', 'Laptop'),
('Xiaomi', 'Mi Book Pro', 'Laptop'),
('Apple','IMac 27','PC'),
('Apple','IMac Pro','PC'),
('Apple','IMac 21','PC'),
('Lenovo','IdeaCentre 520','PC'),
('ASUS','Vivo AIO','PC'),
('ASUS','Zen AIO','PC'),
('HP','ProOne','PC'),
('MSI','Gaming 24','PC'),
('Brother','HL-1210','Printer'),
('Canon','PIXMA','Printer'),
('OKI','MP-98','Printer'),
('HP','Laserjet','Printer'),
('HP','Deskjet','Printer'),
('HP','Laserjet Color','Printer');

/*Inserting data into the Laptop table*/
INSERT INTO SenlaCourses.laptop (code, model, speed, ram, hd, screen, price) VALUES
(40801,'MacBook Air 11',1800,4096,500,11,800),
(40802,'MacBook Air 13',1800,8192,128,13,950),
(40803,'ProBook 4320',1600,3072,1000,17,400),
(40804,'Pavilion',2500,8192,1000,15,750),
(40805,'VivoBook 15',2500,4096,500,15,650),
(40806,'ZenBook UX',2500,8192,256,15,1240),
(40807,'Mi Book Pro',1600,8192,256,15,1150),
(40808,'Mi Book Air',2300,8192,256,13,275);

/*Inserting data into the PC table*/
INSERT INTO SenlaCourses.pc (code, model, speed, ram, hd, cd, price) VALUES
(30801,'IMac 27',3800,8192,2000,'48x',2700),
(30802,'IMac Pro',3200,8192,1000,'48x',6300),
(30803,'IMac 21',2600,8192,1000,'24x',1000),
(30804,'IdeaCentre 520',3400,4096,1000,'24x',750),
(30805,'Vivo AIO',2500,4096,1000,'8x',650),
(30806,'Zen AIO',2400,8192,2000,'12x',1200),
(30807,'ProOne',3900,4096,128,'12x',490),
(30808,'Gaming 24',2400,8192,256,'4x',375);

/*Inserting data into the Printer table*/
INSERT INTO SenlaCourses.printer (code, model, color, type, price) VALUES
(50801,'HL-1210','n','Laser',95),
(50802,'PIXMA','y','Jet',187),
(50803,'MP-98','n','Matrix',20),
(50804,'Laserjet','n','Laser',120),
(50805,'Deskjet','n','Jet',90),
(50806,'Laserjet Color','y','Laser',350);