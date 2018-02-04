/*task01*/
SELECT model, speed, hd FROM pc WHERE price < 500;
/*task02*/
SELECT DISTINCT maker FROM product WHERE type = 'Printer';
/*task03*/
SELECT model, ram, screen FROM laptop WHERE price > 1000;
/*task04*/
SELECT * FROM printer WHERE color = 'y';
/*task05*/
SELECT model, speed, hd FROM pc WHERE cd IN (12, 24) AND price < 600;
/*task06*/
SELECT maker, speed FROM product p, laptop l WHERE p.model = l.model AND l.hd >= 10;
/*task07*/
SELECT p.model, price FROM product p, laptop l WHERE
p.model = l.model AND p.maker = 'HP'
UNION 
SELECT p.model, price FROM product p, pc c WHERE
p.model = c.model AND p.maker = 'HP'
UNION 
SELECT p.model, price FROM product p, printer r WHERE
p.model = r.model AND p.maker = 'HP';
/*task08*/
SELECT DISTINCT product.maker FROM product WHERE product.type IN ('PC')
AND product.maker NOT IN (SELECT maker FROM product WHERE product.type='Laptop');
/*task09*/
SELECT DISTINCT maker FROM product, pc WHERE product.model = pc.model AND pc.speed >= 450;
/*task10*/
SELECT model, price FROM printer r WHERE price = (SELECT min(price) FROM printer);
/*task11*/
SELECT round(avg(speed)) AS avg_pc_speed FROM pc;
/*task12*/
SELECT round(avg(speed)) AS avg_laptop_speed FROM laptop WHERE price >1000;
/*task13*/
SELECT round(avg(speed)) AS avg_pc_speed FROM product, pc WHERE product.model = pc.model AND maker = 'Apple';
/*task14*/
SELECT speed, round(avg(price)) AS avg_pc_price FROM pc GROUP BY speed;
/*task15*/
SELECT hd FROM pc GROUP BY hd HAVING count(*) >= 2;
/*task16*/
SELECT max(code), min(code), speed, ram FROM pc GROUP BY speed, ram HAVING count(*) >= 2;
/*task17*/
SELECT DISTINCT type, laptop.model, speed FROM product, laptop WHERE type = 'Laptop' AND 
speed < (SELECT min(speed) FROM pc);
/*task18*/
SELECT maker, min(price) AS min_price FROM product, printer WHERE product.model = printer.model AND printer.color = 'y' GROUP BY maker;
/*task19*/
SELECT maker, round(avg(screen)) AS avg_screen FROM product p, laptop l WHERE p.model = l.model GROUP BY maker;
/*task20*/
SELECT maker, count(model) count_models FROM product WHERE product.type = 'PC' GROUP BY maker HAVING count(*) >= 3;
/*task21*/
SELECT maker, max(price) AS max_price FROM product, pc WHERE product.model = pc.model GROUP BY maker;
/*task22*/
SELECT speed, round(avg(price)) AS avg_price FROM pc GROUP BY speed HAVING speed > 600;
/*task23*/
SELECT DISTINCT maker FROM product WHERE type = 'Laptop' AND maker IN
  (SELECT maker FROM product WHERE type = 'PC' AND maker IN
  (SELECT maker FROM pc, laptop WHERE pc.speed >=750 and laptop.speed >=750));
/*task24*/
SELECT DISTINCT code FROM laptop WHERE laptop.price = (SELECT max(price) FROM laptop)
  UNION
  SELECT code FROM pc WHERE pc.price = (SELECT max(price) FROM pc)
  UNION
  SELECT code FROM printer WHERE printer.price = (SELECT max(price) FROM printer);
/*task25*/
SELECT DISTINCT maker FROM product WHERE type = 'Printer' AND maker IN
(SELECT maker FROM product JOIN pc ON product.model = pc.model WHERE type = 'PC' AND
ram = (SELECT min(ram) FROM pc) AND pc.speed = (SELECT max(speed) FROM pc));