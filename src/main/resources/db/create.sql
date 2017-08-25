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

CREATE TABLE IF NOT EXISTS legalcases (
  id int PRIMARY KEY auto_increment,
  clientid INTEGER,
  createdat TIMESTAMP,
  caseopen BOOLEAN
);

CREATE TABLE IF NOT EXISTS casescustody (
  id int PRIMARY KEY auto_increment,
  clientid INTEGER,
  createdat TIMESTAMP,
  caseopen BOOLEAN,
  opposingparty VARCHAR,
  custodysituation VARCHAR,
  numchildren INTEGER,
  nexthearing DATE
);

CREATE TABLE IF NOT EXISTS caseshousing (
  id int PRIMARY KEY auto_increment,
  clientid INTEGER,
  createdat TIMESTAMP,
  caseopen BOOLEAN,
  typeofdispute VARCHAR,
  opposingparty VARCHAR,
  clienttenant BOOLEAN,
  caseiseviction BOOLEAN
);