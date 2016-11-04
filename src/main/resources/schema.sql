CREATE TABLE users
(
  id         INTEGER PRIMARY KEY,
  name       VARCHAR NOT NULL,
  email      VARCHAR NOT NULL,
  password   VARCHAR NOT NULL
);

CREATE TABLE quotations (
  id          INTEGER PRIMARY KEY,
  user_id     INTEGER NOT NULL,
  date_time    TIMESTAMP NOT NULL,
  description TEXT NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);