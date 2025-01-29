--ROP TABLE IF EXISTS warehouse_product;

CREATE TABLE IF NOT EXISTS warehouse_product (
    product_id UUID PRIMARY KEY,
    weight DOUBLE PRECISION NOT NULL,
    width DOUBLE PRECISION NOT NULL,
    height DOUBLE PRECISION NOT NULL,
    depth DOUBLE PRECISION NOT NULL,
    fragile BOOLEAN,
    quantity INT NOT NULL
);

--CREATE TABLE IF NOT EXISTS bookings (
--    order_id UUID PRIMARY KEY,
--    fragile BOOLEAN,
--    delivery_volume DOUBLE PRECISION NOT NULL,
--    delivery_weight DOUBLE PRECISION NOT NULL,
--    delivery_id VARCHAR NOT NULL
--);
--
--CREATE TABLE IF NOT EXISTS booking_products (
--    order_id UUID REFERENCES bookings(order_id) ON DELETE CASCADE,
--    product_id UUID REFERENCES warehouse_product(product_id) ON DELETE CASCADE,
--    quantity INTEGER
--);