DROP TABLE IF EXISTS orders;
CREATE TABLE orders
(
    id           UUID           NOT NULL PRIMARY KEY,
    name         VARCHAR(255)   NOT NULL,
    price        DECIMAL(10, 2) NOT NULL,
    address      TEXT           NOT NULL,
    order_date   TIMESTAMP      NOT NULL,
    order_status VARCHAR(20)    NOT NULL CHECK (order_status IN ('PENDING', 'PROCESSING', 'COMPLETE', 'CANCELLED')),
    user_id      UUID           NOT NULL,
    CONSTRAINT fk_orders_user FOREIGN KEY (user_id) REFERENCES users (id)
);
