CREATE TABLE IF NOT EXISTS orders (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users(id),
    customer_name VARCHAR(255) NOT NULL,
    customer_email VARCHAR(255) NOT NULL,
    customer_mobile VARCHAR(15) NOT NULL,

    shipping_address TEXT NOT NULL,
    shipping_city VARCHAR(255) NOT NULL,
    shipping_state VARCHAR(255) NOT NULL,
    shipping_pincode VARCHAR(10) NOT NULL,
    shipping_country VARCHAR(100) NOT NULL,
    status VARCHAR(20) NOT NULL CHECK (
        status IN ('PLACED', 'CONFIRMED', 'PACKED', 'SHIPPED', 'DELIVERED', 'CANCELLED')
    ),

    payment_method VARCHAR(20) NOT NULL CHECK ( payment_method IN ('COD', 'ONLINE') ),
    payment_status VARCHAR(20) NOT NULL CHECK ( payment_status IN ('PENDING', 'PAID', 'FAILED', 'REFUNDED') ),

    subtotal_amount NUMERIC(10, 2) NOT NULL CHECK ( subtotal_amount >= 0 ),
    delivery_amount NUMERIC(10, 2) NOT NULL CHECK ( delivery_amount >= 0 ),
    total_amount NUMERIC(10, 2) NOT NULL CHECK ( total_amount >= 0 ),

    created_at TIMESTAMP NOT NULL,
    last_modified_at TIMESTAMP,

    created_by BIGINT REFERENCES users(id) NOT NULL,
    last_modified_by BIGINT REFERENCES users(id)
);


CREATE TABLE IF NOT EXISTS order_items(
    id BIGSERIAL PRIMARY KEY,
    order_id BIGINT REFERENCES orders(id),
    book_id BIGINT REFERENCES books(id),
    book_title VARCHAR(255) NOT NULL,
    unit_price NUMERIC(10, 2) NOT NULL CHECK ( unit_price >= 0 ),
    quantity INT NOT NULL CHECK ( quantity > 0 ),
    line_total NUMERIC(10, 2) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    created_by BIGINT REFERENCES users(id) NOT NULL
);
