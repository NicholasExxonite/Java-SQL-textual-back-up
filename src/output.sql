CREATE TABLE model (
  manu VARCHAR(15),
  range VARCHAR(15),
  model VARCHAR(30),
  model_id INTEGER,
 PRIMARY KEY (model_id)
);

CREATE TABLE features (
  description VARCHAR(40),
  model_id INTEGER,
 PRIMARY KEY (description, model_id),
 FOREIGN KEY (model_id) REFERENCES model(model_id)
);

CREATE TABLE car (
  reg_num VARCHAR(10),
  model_id INTEGER,
  car_id INTEGER,
  mileage INTEGER,
 PRIMARY KEY (car_id),
 FOREIGN KEY (model_id) REFERENCES model(model_id)
);

CREATE TABLE employees (
  manager_id INTEGER,
  name VARCHAR(40),
  dept VARCHAR(20),
  emp_id INTEGER,
 PRIMARY KEY (emp_id),
 FOREIGN KEY (manager_id) REFERENCES employees(emp_id)
);

CREATE TABLE owner (
  owner_id INTEGER,
  name VARCHAR(15),
  car_id INTEGER,
 PRIMARY KEY (owner_id, car_id),
 FOREIGN KEY (owner_id) REFERENCES employees(emp_id),
 FOREIGN KEY (car_id) REFERENCES car(car_id)
);

INSERT INTO model VALUES ("Honda", "Jazz", "S", 1);
INSERT INTO model VALUES ("Honda", "Jazz", "S A/C", 2);
INSERT INTO model VALUES ("Honda", "Jazz", "S-T", 3);
INSERT INTO model VALUES ("Honda", "Jazz", "S-T A/C", 4);
INSERT INTO model VALUES ("Volkswagon", "Up!", "Take up!", 5);
INSERT INTO model VALUES ("Volkswagon", "Up!", "Move up!", 6);
INSERT INTO model VALUES ("Volkswagon", "Up!", "High up!", 7);
INSERT INTO model VALUES ("Honda", "Civic", "1.4 i-VTEC (Petrol) Manual", 8);
INSERT INTO model VALUES ("Honda", "Civic", "1.6 i_DTEC (Diesel) Manual", 9);
INSERT INTO model VALUES ("Honda", "Civic", "1.8 i-VTEC (Petrol) Automatic", 10);
INSERT INTO model VALUES ("Honda", "Civic", "2.2 i-DTEC (Diesel) Manual", 11);


INSERT INTO features VALUES ("Active front headrests", 1);
INSERT INTO features VALUES ("Anti-Lock Breaking System", 1);
INSERT INTO features VALUES ("Active front headrests", 2);
INSERT INTO features VALUES ("Anti-Lock Breaking System", 2);
INSERT INTO features VALUES ("A/C - manual air conditioning", 2);
INSERT INTO features VALUES ("Active front headrests", 3);
INSERT INTO features VALUES ("Anti-Lock Breaking System", 3);
INSERT INTO features VALUES ("Hands-free phone system", 3);
INSERT INTO features VALUES ("Airbags", 5);
INSERT INTO features VALUES ("Rear headrest, adjustable x 2", 5);
INSERT INTO features VALUES ("Airbags", 6);
INSERT INTO features VALUES ("Rear headrest, adjustable x 2", 6);
INSERT INTO features VALUES ("A/C - manual air conditioning", 6);
INSERT INTO features VALUES ("Central Locking", 6);
INSERT INTO features VALUES ("Airbags", 7);
INSERT INTO features VALUES ("Rear headrest, adjustable x 2", 7);
INSERT INTO features VALUES ("A/C - manual air conditioning", 7);
INSERT INTO features VALUES ("Central Locking", 7);
INSERT INTO features VALUES ("Touch Screen Navigation", 7);


INSERT INTO car VALUES ("aa111gfd", 1, 1, 28000);
INSERT INTO car VALUES ("bb222jhg", 1, 2, 5000);
INSERT INTO car VALUES ("zz999qwe", 5, 3, 30000);
INSERT INTO car VALUES ("sw76qpr", 6, 4, 10000);
INSERT INTO car VALUES ("kj87wer", 2, 5, 50007);


INSERT INTO employees VALUES (0, "Sherridan", "Design", 1);
INSERT INTO employees VALUES (0, "Ivanova", "Production", 2);
INSERT INTO employees VALUES (1, "Garibaldi", "Design", 3);
INSERT INTO employees VALUES (20, "Mollari", "Production", 4);


INSERT INTO owner VALUES (1, "Picard", 1);
INSERT INTO owner VALUES (1, "Picard", 2);
INSERT INTO owner VALUES (2, "Worf", 3);
INSERT INTO owner VALUES (3, "Troi", 4);
INSERT INTO owner VALUES (4, "Riker", 5);


