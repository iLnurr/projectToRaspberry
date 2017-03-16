alter table quotations
  drop
  foreign key FKj3s7ongcvjncktb1xnyvrns07;

DROP TABLE IF EXISTS quotations;
DROP TABLE IF EXISTS users;

CREATE TABLE users
(
  user_id         INTEGER PRIMARY KEY,
  name       VARCHAR(255) NOT NULL,
  email      VARCHAR(255) NOT NULL,
  password   VARCHAR(255) NOT NULL
);

CREATE TABLE quotations (
  id          INTEGER PRIMARY KEY,
  date_time    TIMESTAMP NOT NULL,
  description VARCHAR(8026) NOT NULL,
  user_id     INTEGER NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);