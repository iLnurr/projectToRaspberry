DROP TABLE IF EXISTS quotations;
DROP TABLE IF EXISTS users;

CREATE TABLE users
(
  user_id integer NOT NULL,
  email character varying(255) NOT NULL,
  name character varying(255) NOT NULL,
  password character varying(255) NOT NULL,
  CONSTRAINT users_pkey PRIMARY KEY (user_id),
  CONSTRAINT uk_6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email)
);

CREATE TABLE quotations
(
  id integer NOT NULL,
  date_time timestamp without time zone NOT NULL,
  description character varying(255) NOT NULL,
  user_id integer NOT NULL,
  CONSTRAINT quotations_pkey PRIMARY KEY (id),
  CONSTRAINT fkj3s7ongcvjncktb1xnyvrns07 FOREIGN KEY (user_id)
  REFERENCES users (user_id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT quotas_unique_user_datetime_idx UNIQUE (user_id, date_time)
);


-- CREATE TABLE users
-- (
--   id         INTEGER PRIMARY KEY,
--   name       VARCHAR NOT NULL,
--   email      VARCHAR NOT NULL,
--   password   VARCHAR NOT NULL
-- );
-- CREATE UNIQUE INDEX users_unique_email_idx ON users (email);


--
-- CREATE TABLE quotations (
--   id          INTEGER PRIMARY KEY,
--   user_id     INTEGER NOT NULL,
--   date_time    TIMESTAMP NOT NULL,
--   description TEXT NOT NULL,
--   FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
-- );
-- CREATE UNIQUE INDEX quotas_unique_user_datetime_idx ON Quotations(user_id, date_time)