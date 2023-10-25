
-- insert role
INSERT INTO role (id, name) VALUES (100, 'ROLE_ADMIN')
    ON DUPLICATE KEY UPDATE name = VALUES(name);

INSERT INTO role (id, name) VALUES (101, 'ROLE_USER')
    ON DUPLICATE KEY UPDATE name = VALUES(name);

-- insert user
INSERT INTO users (id, email, name, password) VALUES (1,'admin@example.com', 'admin', '$2a$10$o/YZLgYkQK531bOQ3ay/LOQfwxmH6Q7.73yh9SiOB712lAsHTJuBe')
    ON DUPLICATE KEY UPDATE email = VALUES(email), name = VALUES(name), password = VALUES(password);


-- insert role to user
INSERT INTO user_role (user, role) VALUES (1, 100)
    ON DUPLICATE KEY UPDATE role = VALUES(role);

-- Insert categories
INSERT INTO categories (category_id, title) VALUES (1, 'Technology')
    ON DUPLICATE KEY UPDATE title = VALUES(title);
INSERT INTO categories (category_id, title) VALUES (2, 'Travel')
    ON DUPLICATE KEY UPDATE title = VALUES(title);
INSERT INTO categories (category_id, title) VALUES (3, 'Food')
    ON DUPLICATE KEY UPDATE title = VALUES(title);
INSERT INTO categories (category_id, title) VALUES (4, 'Lifestyle')
    ON DUPLICATE KEY UPDATE title = VALUES(title);
INSERT INTO categories (category_id, title) VALUES (5, 'Fashion')
    ON DUPLICATE KEY UPDATE title = VALUES(title);
