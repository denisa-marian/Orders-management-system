CREATE DATABASE orders_management;
USE orders_management;

CREATE TABLE Client (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    address VARCHAR(200)
);

CREATE TABLE Product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    stock INT NOT NULL,
    price DOUBLE NOT NULL
);

CREATE TABLE OrderTable (
    id INT AUTO_INCREMENT PRIMARY KEY,
    client_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (client_id) REFERENCES Client(id),
    FOREIGN KEY (product_id) REFERENCES Product(id)
);

CREATE TABLE Log (
    id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT NOT NULL,
    client_name VARCHAR(100) NOT NULL,
    product_name VARCHAR(100) NOT NULL,
    quantity INT NOT NULL,
    total_price DOUBLE NOT NULL,
    bill_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO Client (name, email, address)
VALUES ('Ana Popescu', 'ana@gmail.com', 'Str. Florilor 12'),
       ('Ion Ionescu', 'ion@gmail.com', 'Bd. Republicii 45');

INSERT INTO Product (name, stock, price)
VALUES ('Laptop', 50, 2999.99),
       ('Mouse', 200, 49.99),
       ('Monitor', 75, 899.99);


