DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS flavors;
DROP TABLE IF EXISTS sizes;
DROP TABLE IF EXISTS customers;

CREATE TABLE customers (
  customer_pk int unsigned NOT NULL AUTO_INCREMENT,
  customer_id varchar(40) NOT NULL,
  first_name varchar(45) NOT NULL, 
  last_name varchar(45) NOT NULL,
  PRIMARY KEY (customer_pk)
);

CREATE TABLE sizes (
  size_pk int unsigned NOT NULL AUTO_INCREMENT,
  size_id enum('SMALL', 'MEDIUM', 'LARGE') NOT NULL,
  base_price decimal(9, 2) NOT NULL,
  PRIMARY KEY (size_pk)
);

CREATE TABLE flavors (
  flavor_pk int unsigned NOT NULL AUTO_INCREMENT,
  flavor_id varchar(30) NOT NULL,
  flavor varchar(60) NOT NULL,
  price decimal(9, 2) NOT NULL,
  PRIMARY KEY (flavor_pk),
  UNIQUE KEY (flavor_id)
);

CREATE TABLE orders (
  order_pk int unsigned NOT NULL AUTO_INCREMENT,
  customer_fk int unsigned NOT NULL,
  flavor_fk int unsigned NOT NULL,
  size_fk int unsigned NOT NULL,
  price decimal(9, 2) NOT NULL,
  PRIMARY KEY (order_pk),
  FOREIGN KEY (customer_fk) REFERENCES customers (customer_pk) ON DELETE CASCADE,
  FOREIGN KEY (flavor_fk) REFERENCES flavors (flavor_pk) ON DELETE CASCADE,
  FOREIGN KEY (size_fk) REFERENCES sizes (size_pk) ON DELETE CASCADE
  );