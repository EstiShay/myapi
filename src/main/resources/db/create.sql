SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS attorneys (
  id int PRIMARY KEY auto_increment,
  name VARCHAR,
  barid VARCHAR
);

CREATE TABLE IF NOT EXISTS clients (
  id int PRIMARY KEY auto_increment,
  name VARCHAR,
  contact VARCHAR,
  financialneedverified BOOLEAN
);