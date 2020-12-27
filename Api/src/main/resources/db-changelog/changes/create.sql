
CREATE TABLE media (
  id int(11) NOT NULL AUTO_INCREMENT,
  file_name varchar(250) ,
  file_content mediumblob ,
  PRIMARY KEY (id)
);
CREATE TABLE category (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(50) ,
  description varchar(50) ,
  media_id int(11) ,
  PRIMARY KEY (id),
  CONSTRAINT fk_media_id FOREIGN KEY (media_id) REFERENCES media (id)
);

CREATE TABLE product (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(50) ,
  description varchar(50) ,
  sales_price float ,
  purchase_price float ,
  media_id int(11),
  PRIMARY KEY (id),
  CONSTRAINT fk_media_product FOREIGN KEY (media_id) REFERENCES media (id)
);
CREATE TABLE category_product (
  category_id int(11) NOT NULL,
  product_id int(11) NOT NULL,
  PRIMARY KEY (category_id,product_id),
  CONSTRAINT fk_category_product FOREIGN KEY (product_id) REFERENCES product (id),
  CONSTRAINT fk_product_category FOREIGN KEY (category_id) REFERENCES category (id)
);
CREATE TABLE place (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(250) ,
  table_count int ,
  media_id int,
  PRIMARY KEY (id)
);
CREATE TABLE roles (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(250) ,
  PRIMARY KEY (id)
);
CREATE TABLE user (
  id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(50) ,
  password varchar(250) ,
  email varchar(250) ,
  enabled bool,
  PRIMARY KEY (id)
);
CREATE TABLE waiter (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(50) ,
  phone varchar(50) ,
  email varchar(250) ,
  age int,
  media_id int ,
  PRIMARY KEY (id),
  CONSTRAINT fk_media_waiter FOREIGN KEY (media_id) REFERENCES media (id)
);
CREATE TABLE users_role (
  user_id int(11) NOT NULL,
  role_id int(11) NOT NULL,
  PRIMARY KEY (user_id,role_id),
  CONSTRAINT fk_user_role FOREIGN KEY (user_id) REFERENCES user (id),
  CONSTRAINT fk_role_user FOREIGN KEY (role_id) REFERENCES roles (id)
);
CREATE TABLE cart (
  id int(11) NOT NULL AUTO_INCREMENT,
  product_id varchar(50) ,
  total_price varchar(50) ,
  piece varchar(250) ,
  place_id int ,
  price int ,
  table_id int ,
  waiter_id int ,
  customer_id int,
  create_date timestamp ,
  PRIMARY KEY (id)
);
CREATE TABLE customer (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(50) ,
  surname varchar(50) ,
  address varchar(250) ,
  phone varchar(50),
  media_id int,
  deleted bool DEFAULT FALSE,
  PRIMARY KEY (id)
);

