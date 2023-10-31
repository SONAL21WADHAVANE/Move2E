
create database Move2E;
show databases;
use Move2E;
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY, 
    username VARCHAR(255) NOT NULL,    
    email VARCHAR(255) NOT NULL,       
    password VARCHAR(255) NOT NULL,    
    mobile VARCHAR(15) NOT NULL,       
    location VARCHAR(255) NOT NULL     
};
select * from users;

USE Move2E;

-- Create the shop_table table
CREATE TABLE  shop_info (
    id INT AUTO_INCREMENT PRIMARY KEY,
    shopOwner VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    pass VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL,
    id1 VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(255) NOT NULL
);

CREATE TABLE products (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL,
    product_price DECIMAL(10, 2) NOT NULL,
    product_image LongBLOB,
    id INT,
    FOREIGN KEY (id) REFERENCES shop_info(id)
);

select * from shop_info;
select * from products;






