CREATE TABLE purchase_product (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    purchase_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity BIGINT NOT NULL,
    CONSTRAINT fk_purchase_product_purchase FOREIGN KEY (purchase_id) REFERENCES purchase(id),
    CONSTRAINT fk_purchase_product_product FOREIGN KEY (product_id) REFERENCES product(id)
);