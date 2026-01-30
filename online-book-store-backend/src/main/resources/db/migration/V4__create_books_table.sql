CREATE TABLE IF NOT EXISTS books (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    isbn VARCHAR(20) NOT NULL UNIQUE,
    description TEXT,
    price NUMERIC(10, 2) NOT NULL CHECK ( price > 0 ),
    stock_quantity INT NOT NULL CHECK ( stock_quantity > 0 ),
    image_url TEXT,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL,
    last_modified_at TIMESTAMP,
    category_id BIGINT REFERENCES categories(id)
);

INSERT INTO books
(title, author, isbn, description, price, stock_quantity, image_url, is_active, created_at, last_modified_at, category_id)
VALUES
-- Fiction (1)
('The Silent River', 'Ava Collins', '9781000000001', 'Mystery novel set in a small town.', 18.99, 40, NULL, TRUE, NOW(), NULL, 1),
('Midnight Letters', 'James Rowan', '9781000000002', 'Romantic fiction story.', 16.50, 35, NULL, TRUE, NOW(), NULL, 1),
('Lost Kingdom', 'Eleanor West', '9781000000003', 'Epic fantasy adventure.', 22.99, 50, NULL, TRUE, NOW(), NULL, 1),
('Shadow of Time', 'Mark Ellison', '9781000000004', 'Sci-fi time travel novel.', 21.00, 30, NULL, TRUE, NOW(), NULL, 1),
('Hidden Truths', 'Liam Foster', '9781000000005', 'Psychological thriller.', 19.99, 45, NULL, TRUE, NOW(), NULL, 1),
('Golden Horizon', 'Sophia Reed', '9781000000006', 'Adventure fiction.', 20.50, 38, NULL, TRUE, NOW(), NULL, 1),
('Broken Silence', 'Noah Carter', '9781000000007', 'Crime fiction.', 17.99, 42, NULL, TRUE, NOW(), NULL, 1),

-- Non-Fiction (2)
('Atomic Habits', 'James Clear', '9781000000011', 'Habit building strategies.', 20.00, 60, NULL, TRUE, NOW(), NULL, 2),
('Deep Work', 'Cal Newport', '9781000000012', 'Focused success guide.', 21.99, 50, NULL, TRUE, NOW(), NULL, 2),
('Think Again', 'Adam Grant', '9781000000013', 'Rethinking ideas.', 19.50, 45, NULL, TRUE, NOW(), NULL, 2),
('Outliers', 'Malcolm Gladwell', '9781000000014', 'Success explained.', 18.75, 40, NULL, TRUE, NOW(), NULL, 2),
('Educated', 'Tara Westover', '9781000000015', 'Memoir of resilience.', 22.00, 30, NULL, TRUE, NOW(), NULL, 2),
('Factfulness', 'Hans Rosling', '9781000000016', 'Understanding the world.', 24.99, 35, NULL, TRUE, NOW(), NULL, 2),
('The Almanack of Naval', 'Eric Jorgenson', '9781000000017', 'Life and wealth wisdom.', 23.50, 28, NULL, TRUE, NOW(), NULL, 2),

-- Self-Help (3)
('The Power of Now', 'Eckhart Tolle', '9781000000021', 'Living in the present.', 17.99, 55, NULL, TRUE, NOW(), NULL, 3),
('Mindset', 'Carol Dweck', '9781000000022', 'Growth mindset.', 19.99, 48, NULL, TRUE, NOW(), NULL, 3),
('You Are a Badass', 'Jen Sincero', '9781000000023', 'Motivation guide.', 16.99, 40, NULL, TRUE, NOW(), NULL, 3),
('The Subtle Art', 'Mark Manson', '9781000000024', 'Life philosophy.', 18.50, 44, NULL, TRUE, NOW(), NULL, 3),
('Atomic Focus', 'Chris Bailey', '9781000000025', 'Productivity mastery.', 21.00, 36, NULL, TRUE, NOW(), NULL, 3),
('Emotional Intelligence', 'Daniel Goleman', '9781000000026', 'EQ skills.', 22.99, 39, NULL, TRUE, NOW(), NULL, 3),

-- Business & Economics (4)
('Rich Dad Poor Dad', 'Robert Kiyosaki', '9781000000031', 'Personal finance basics.', 18.99, 70, NULL, TRUE, NOW(), NULL, 4),
('The Lean Startup', 'Eric Ries', '9781000000032', 'Startup methodology.', 24.00, 40, NULL, TRUE, NOW(), NULL, 4),
('Zero to One', 'Peter Thiel', '9781000000033', 'Startup innovation.', 22.50, 38, NULL, TRUE, NOW(), NULL, 4),
('Good to Great', 'Jim Collins', '9781000000034', 'Business growth.', 25.99, 32, NULL, TRUE, NOW(), NULL, 4),
('Blue Ocean Strategy', 'W Chan Kim', '9781000000035', 'Market creation.', 27.00, 29, NULL, TRUE, NOW(), NULL, 4),
('Principles', 'Ray Dalio', '9781000000036', 'Life & work rules.', 26.50, 31, NULL, TRUE, NOW(), NULL, 4),

-- Programming Languages (5)
('Effective Java', 'Joshua Bloch', '9781000000041', 'Java best practices.', 45.00, 25, NULL, TRUE, NOW(), NULL, 5),
('Java: The Complete Ref', 'Herbert Schildt', '9781000000042', 'Complete Java guide.', 49.99, 22, NULL, TRUE, NOW(), NULL, 5),
('Python Crash Course', 'Eric Matthes', '9781000000043', 'Python fundamentals.', 39.99, 30, NULL, TRUE, NOW(), NULL, 5),
('C Programming Language', 'Dennis Ritchie', '9781000000044', 'C language classic.', 35.50, 28, NULL, TRUE, NOW(), NULL, 5),
('Modern C++', 'Scott Meyers', '9781000000045', 'Advanced C++.', 48.00, 20, NULL, TRUE, NOW(), NULL, 5),
('Rust in Action', 'Tim McNamara', '9781000000046', 'Systems programming.', 42.99, 24, NULL, TRUE, NOW(), NULL, 5),

-- Web Development (6)
('HTML & CSS', 'Jon Duckett', '9781000000051', 'Web fundamentals.', 29.99, 45, NULL, TRUE, NOW(), NULL, 6),
('JavaScript: The Good Parts', 'Douglas Crockford', '9781000000052', 'JS core concepts.', 34.50, 30, NULL, TRUE, NOW(), NULL, 6),
('Spring Boot in Action', 'Craig Walls', '9781000000053', 'Spring Boot guide.', 44.99, 28, NULL, TRUE, NOW(), NULL, 6),
('React Explained', 'Zac Gordon', '9781000000054', 'React development.', 38.00, 33, NULL, TRUE, NOW(), NULL, 6),
('Node.js Design Patterns', 'Mario Casciaro', '9781000000055', 'Node architecture.', 46.99, 26, NULL, TRUE, NOW(), NULL, 6),
('RESTful Web Services', 'Leonard Richardson', '9781000000056', 'REST APIs.', 41.50, 27, NULL, TRUE, NOW(), NULL, 6),

-- Data Science (7)
('Data Science from Scratch', 'Joel Grus', '9781000000061', 'Data science basics.', 41.00, 25, NULL, TRUE, NOW(), NULL, 7),
('Hands-On ML', 'Aurelien Geron', '9781000000062', 'Machine learning.', 52.99, 20, NULL, TRUE, NOW(), NULL, 7),
('Practical Statistics', 'Peter Bruce', '9781000000063', 'Stats for DS.', 36.50, 27, NULL, TRUE, NOW(), NULL, 7),
('Python for Data Analysis', 'Wes McKinney', '9781000000064', 'Pandas & NumPy.', 44.99, 22, NULL, TRUE, NOW(), NULL, 7),
('Storytelling with Data', 'Cole Nussbaumer', '9781000000065', 'Data visualization.', 39.99, 24, NULL, TRUE, NOW(), NULL, 7),

-- Artificial Intelligence (8)
('Artificial Intelligence Basics', 'Tom Taulli', '9781000000071', 'AI explained.', 40.00, 22, NULL, TRUE, NOW(), NULL, 8),
('Deep Learning', 'Ian Goodfellow', '9781000000072', 'Neural networks.', 59.99, 18, NULL, TRUE, NOW(), NULL, 8),
('AI Superpowers', 'Kai-Fu Lee', '9781000000073', 'Global AI race.', 24.99, 34, NULL, TRUE, NOW(), NULL, 8),
('Machine Learning Ops', 'Mark Treveil', '9781000000074', 'MLOps guide.', 47.50, 20, NULL, TRUE, NOW(), NULL, 8),

-- Competitive Exams (9)
('Cracking Coding Interview', 'Gayle Laakmann', '9781000000081', 'Interview prep.', 42.00, 60, NULL, TRUE, NOW(), NULL, 9),
('GATE CS Guide', 'Amit Khurana', '9781000000082', 'GATE exam prep.', 35.00, 55, NULL, TRUE, NOW(), NULL, 9),
('Quantitative Aptitude', 'R.S. Aggarwal', '9781000000083', 'Aptitude practice.', 28.99, 65, NULL, TRUE, NOW(), NULL, 9),

-- Children’s Books (10)
('Magic Tree', 'Lucy Brown', '9781000000091', 'Fantasy for kids.', 12.99, 80, NULL, TRUE, NOW(), NULL, 10),
('Learning ABC', 'Emma Wilson', '9781000000092', 'Alphabet book.', 9.99, 90, NULL, TRUE, NOW(), NULL, 10),
('Bedtime Stories', 'Oliver Stone', '9781000000093', 'Stories for kids.', 14.50, 70, NULL, TRUE, NOW(), NULL, 10),

-- Biography (11)
('Steve Jobs', 'Walter Isaacson', '9781000000101', 'Apple founder life.', 26.99, 40, NULL, TRUE, NOW(), NULL, 11),
('Becoming', 'Michelle Obama', '9781000000102', 'Inspirational memoir.', 24.99, 38, NULL, TRUE, NOW(), NULL, 11),

-- History (12)
('Sapiens', 'Yuval Noah Harari', '9781000000111', 'Human history.', 28.99, 45, NULL, TRUE, NOW(), NULL, 12),
('World War II', 'Antony Beevor', '9781000000112', 'War history.', 30.00, 34, NULL, TRUE, NOW(), NULL, 12),

-- Health & Fitness (13)
('Eat to Beat Disease', 'William Li', '9781000000121', 'Nutrition science.', 21.99, 50, NULL, TRUE, NOW(), NULL, 13),
('The Fitness Mindset', 'Brian Keane', '9781000000122', 'Mental fitness.', 19.99, 44, NULL, TRUE, NOW(), NULL, 13),

-- Religion & Spirituality (14)
('Bhagavad Gita', 'Vyasa', '9781000000131', 'Spiritual scripture.', 15.00, 70, NULL, TRUE, NOW(), NULL, 14),
('Power of Faith', 'John Maxwell', '9781000000132', 'Spiritual growth.', 17.50, 42, NULL, TRUE, NOW(), NULL, 14);
