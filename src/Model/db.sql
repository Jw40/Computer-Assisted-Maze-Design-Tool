-- Use database

CREATE DATABASE IF NOT EXISTS maze302project;
USE maze302project;

-- Delete tables if already exist.

DROP TABLE IF EXISTS maze302project.user;
DROP TABLE IF EXISTS maze302project.user_prefs;

-- Create tables.

CREATE TABLE IF NOT EXISTS user (
    idx INT PRIMARY KEY AUTO_INCREMENT NOT NULL UNIQUE,
    username VARCHAR(30) NOT NULL,
    password VARCHAR(30) NOT NULL
);


CREATE TABLE IF NOT EXISTS user_prefs (
    idx INT PRIMARY KEY NOT NULL UNIQUE,
    maze_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL UNIQUE,
    mazeName VARCHAR(5), // Maze Size
    dateCreated DATE,
    FOREIGN KEY (idx) REFERENCES user(idx)
);



-- Add initial entries.

INSERT INTO
  users (username, password)
VALUES
  ('person', 'password'),
  ('me', '123'),
  ('dex', 'password');

INSERT INTO
  user_prefs (mazeName, dateCreated)
VALUES
  ('my Maze', 'DATE', 10000),
  ('maze2', 'LOCALDATE', 10000);

