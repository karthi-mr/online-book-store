CREATE TABLE IF NOT EXISTS categories (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    is_active BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL,
    last_modified_at TIMESTAMP
);

INSERT INTO categories (name, is_active, created_at)
VALUES
    ('Fiction', true, NOW()),
    ('Non-Fiction', true, NOW()),
    ('Self-Help', true, NOW()),
    ('Business & Economics', true, NOW()),
    ('Programming Languages', true, NOW()),
    ('Web Development', true, NOW()),
    ('Data Science', true, NOW()),
    ('Artificial Intelligence', true, NOW()),
    ('Competitive Exams', true, NOW()),
    ('Children’s Books', true, NOW()),
    ('Biography & Autobiography', true, NOW()),
    ('History', true, NOW()),
    ('Health & Fitness', true, NOW()),
    ('Religion & Spirituality', true, NOW());

