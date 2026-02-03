CREATE TABLE IF NOT EXISTS carts (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL UNIQUE REFERENCES users(id) ON DELETE CASCADE,
    created_at TIMESTAMP NOT NULL,
    last_modified_at TIMESTAMP,
    status VARCHAR(20) NOT NULL CHECK ( status in ('ACTIVE', 'CHECKED_OUT', 'ABANDONED') )
);

CREATE TABLE IF NOT EXISTS cart_items (
    id BIGSERIAL PRIMARY KEY,
    cart_id BIGINT NOT NULL REFERENCES carts(id),
    book_id BIGINT NOT NULL REFERENCES books(id),
    quantity INT NOT NULL CHECK ( quantity > 0 ),
    created_at TIMESTAMP NOT NULL,
    last_modified_at TIMESTAMP,

    CONSTRAINT uq_cart_book UNIQUE (cart_id, book_id)
);


INSERT INTO carts (user_id, created_at, status)
VALUES
    (2, NOW(), 'ACTIVE'),
    (3, NOW(), 'ACTIVE'),
    (4, NOW(), 'ACTIVE');
