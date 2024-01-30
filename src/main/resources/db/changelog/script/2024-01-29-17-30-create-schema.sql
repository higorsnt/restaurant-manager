--liquibase formatted sql
--changeset higor:create-schema

CREATE TYPE category AS ENUM ('drink', 'appetizer', 'main_course', 'dessert');

CREATE TABLE product(
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    price NUMERIC NOT NULL,
    category CATEGORY NOT NULL
);

CREATE TABLE customer_order(
    id SERIAL PRIMARY KEY,
    price NUMERIC NOT NULL
);

CREATE TABLE product_order(
    customer_order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity NUMERIC NOT NULL,
    PRIMARY KEY (customer_order_id, product_id),
    CONSTRAINT customer_order_id_fk FOREIGN KEY (customer_order_id) REFERENCES customer_order(id),
    CONSTRAINT product_id_fk FOREIGN KEY (product_id) REFERENCES product(id)
);