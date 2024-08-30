DROP TABLE IF EXISTS product CASCADE;
DROP TABLE IF EXISTS product_category CASCADE;
DROP TABLE IF EXISTS country CASCADE;
DROP TABLE IF EXISTS state CASCADE;
DROP TABLE IF EXISTS order_item CASCADE;
DROP TABLE IF EXISTS orders CASCADE;
DROP TABLE IF EXISTS customer CASCADE;
DROP TABLE IF EXISTS address CASCADE;

-- Create the product_category table first
CREATE TABLE IF NOT EXISTS product_category (
     id UUID PRIMARY KEY,
     category_name VARCHAR(255) DEFAULT NULL
    );

-- Create the product table after product_category is created
CREATE TABLE IF NOT EXISTS product (
    id UUID PRIMARY KEY,
    sku VARCHAR(255) DEFAULT NULL,
    name VARCHAR(255) DEFAULT NULL,
    description VARCHAR(255) DEFAULT NULL,
    image_url VARCHAR(255) DEFAULT NULL,
    active BOOLEAN DEFAULT TRUE,
    units_in_stock INTEGER DEFAULT NULL,
    units_sold INTEGER DEFAULT NULL,
    unit_price DECIMAL(13, 2) DEFAULT NULL,
    category_id UUID NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT NULL,
    CONSTRAINT FK_product_category_id FOREIGN KEY (category_id) REFERENCES product_category (id)
    );

CREATE TABLE country (
                           id UUID PRIMARY KEY,
                           code varchar(2) DEFAULT NULL,
                           name varchar(255) DEFAULT NULL
);


-- Table structure for table `state`
CREATE TABLE state (
                       id UUID PRIMARY KEY,
                       name VARCHAR(255),
                       country_id UUID,
                       CONSTRAINT FK_country_id FOREIGN KEY (country_id) REFERENCES country (id)
);

-- Table structure for table `customer`
CREATE TABLE customer (
                          id UUID PRIMARY KEY,
                          first_name VARCHAR(255),
                          last_name VARCHAR(255),
                          email VARCHAR(255)
);

-- Table structure for table `address`
CREATE TABLE address (
                         id UUID PRIMARY KEY,
                         city VARCHAR(255),
                         street VARCHAR(255),
                         zip_code VARCHAR(255),
                         customer_shipping_address_id UUID,
                         customer_billing_address_id UUID,
                         state_id UUID,
                         CONSTRAINT FK_customer_shipping_address_id FOREIGN KEY (customer_shipping_address_id) REFERENCES customer (id),
                         CONSTRAINT FK_customer_billing_address_id FOREIGN KEY (customer_billing_address_id) REFERENCES customer (id),
                         CONSTRAINT FK_state_id FOREIGN KEY (state_id) REFERENCES state (id)
);

-- Table structure for table `orders`
CREATE TABLE orders (
                        id UUID PRIMARY KEY,
                        order_tracking_number VARCHAR(255),
                        total_price DECIMAL(19,2),
                        total_quantity INT,
                        customer_id UUID,
                        status VARCHAR(128),
                        created_at TIMESTAMP,
                        updated_at TIMESTAMP,
                        CONSTRAINT FK_customer_id FOREIGN KEY (customer_id) REFERENCES customer (id)
);

-- Table structure for table `order_items`
CREATE TABLE order_item (
                            id UUID PRIMARY KEY,
                            quantity INT,
                            order_id UUID,
                            product_id UUID,
                            CONSTRAINT FK_order_id FOREIGN KEY (order_id) REFERENCES orders (id),
                            CONSTRAINT FK_product_id FOREIGN KEY (product_id) REFERENCES product (id)
);


-- Insert data into product_category
INSERT INTO product_category(id, category_name) VALUES
    ('e302d1b4-8609-417d-9f5a-ceb1fb1a9331', 'Books');

-- Insert data into product after product_category is populated
INSERT INTO product (id, sku, name, description, image_url, active, units_in_stock, units_sold, unit_price, category_id, created_at, updated_at)
VALUES ('7a759e5a-1f49-4379-8064-e697e073e7ee', 'BOOK-TECH-1001', 'Become a Guru in JavaScript', 'Learn JavaScript at your own pace. The author explains how the technology works in easy-to-understand language. This book includes working examples that you can apply to your own projects. Purchase the book and get started today!', 'assets/images/products/books/book-1001.png', TRUE, 100, 0, 20.99, 'e302d1b4-8609-417d-9f5a-ceb1fb1a9331', '2024-08-26 20:34:10.62257', NULL);

INSERT INTO product (id, sku, name, description, image_url, active, units_in_stock, units_sold, unit_price, category_id, created_at, updated_at)
VALUES ('1a3d726c-4e38-4f85-9856-5a481eac4589', 'BOOK-TECH-1009', 'Become a Guru in React.js', 'Learn React.js at your own pace. The author explains how the technology works in easy-to-understand language. This book includes working examples that you can apply to your own projects. Purchase the book and get started today!', 'assets/images/products/books/book-1009.png', TRUE, 100, 0, 23.99, 'e302d1b4-8609-417d-9f5a-ceb1fb1a9331', '2024-08-26 20:34:10.62257', NULL);

INSERT INTO product (id, sku, name, description, image_url, active, units_in_stock, units_sold, unit_price, category_id, created_at, updated_at)
VALUES ('dc8999c3-c41c-4146-92c5-bf38d9014193', 'BOOK-TECH-1014', 'Introduction to SQL', 'Learn SQL at your own pace. The author explains how the technology works in easy-to-understand language. This book includes working examples that you can apply to your own projects. Purchase the book and get started today!', 'assets/images/products/books/book-1014.png', TRUE, 100, 0, 22.99, 'e302d1b4-8609-417d-9f5a-ceb1fb1a9331', '2024-08-26 20:34:10.62257', NULL);

INSERT INTO product (id, sku, name, description, image_url, active, units_in_stock, units_sold, unit_price, category_id, created_at, updated_at)
VALUES ('db054956-8376-47d5-9e38-0422493c6c3a', 'BOOK-TECH-1015' , 'The Expert Guide to JavaScript', 'Learn JavaScript at your own pace. The author explains how the technology works in easy-to-understand language. This book includes working examples that you can apply to your own projects. Purchase the book and get started today!', 'assets/images/products/books/book-1015.png', TRUE, 100, 0, 22.99, 'e302d1b4-8609-417d-9f5a-ceb1fb1a9331', '2024-08-26 20:34:10.62257', NULL);

INSERT INTO product (id, sku, name, description, image_url, active, units_in_stock, units_sold, unit_price, category_id, created_at, updated_at)
VALUES ('8725ec9a-efe8-4f14-8aab-8e40d79ea976', 'BOOK-TECH-1019', 'Crash Course in JavaScript', 'Learn JavaScript at your own pace. The author explains how the technology works in easy-to-understand language. This book includes working examples that you can apply to your own projects. Purchase the book and get started today!', 'assets/images/products/books/book-1019.png', TRUE, 100, 0, 13.99, 'e302d1b4-8609-417d-9f5a-ceb1fb1a9331', '2024-08-26 20:34:10.62257', NULL);

INSERT INTO product (id, sku, name, description, image_url, active, units_in_stock, units_sold, unit_price, category_id, created_at, updated_at)
VALUES ('f0016ed4-9664-49a2-8578-b1df2f092b16', 'BOOK-TECH-1021', 'Become a Guru in Java', 'Learn Java at your own pace. The author explains how the technology works in easy-to-understand language. This book includes working examples that you can apply to your own projects. Purchase the book and get started today!', 'assets/images/products/books/book-1021.png', TRUE, 100, 0, 18.99, 'e302d1b4-8609-417d-9f5a-ceb1fb1a9331', '2024-08-26 20:34:10.62257', NULL);
