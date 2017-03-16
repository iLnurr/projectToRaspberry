DELETE FROM quotations;
DELETE FROM users;

INSERT INTO users (id, name, email, password)
VALUES (100, 'User-Anonymous', 'user@yandex.ru', '$2a$10$Sh0ZD2NFrzRRJJEKEWn8l.92ROEuzlVyzB9SV1AM8fdluPR0aC1ni');


INSERT INTO users (id, name, email, password)
VALUES (101, 'Admin', 'admin@gmail.com', '$2a$10$WejOLxVuXRpOgr4IlzQJ.eT4UcukNqHlAiOVZj1P/nmc8WbpMkiju');