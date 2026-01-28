INSERT INTO users (firstname, lastname, email, mobile, password, role, created_at, last_modified_at)
VALUES ('admin', 'admin', 'admin@mail.com', '1234567890',
        '$2a$12$Ov0/R5z5HfsGfXfBa7ej/.LYgKEWsd4ClQNs6z9HtFnXQx7f..5SK', 'ADMIN', NOW(),
        NULL),
    ('john', 'doe', 'john@mail.com', '9876543210',
        '$2a$12$Ov0/R5z5HfsGfXfBa7ej/.LYgKEWsd4ClQNs6z9HtFnXQx7f..5SK', 'CUSTOMER', NOW(),
        NULL);