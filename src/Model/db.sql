-- Use database

CREATE DATABASE IF NOT EXISTS maze302project;
USE maze302project;

-- Delete tables if already exist.

DROP TABLE IF EXISTS maze302project.user;
DROP TABLE IF EXISTS maze302project.user_prefs;


-- Create tables.

CREATE TABLE user (
    idx INT AUTO_INCREMENT NOT NULL UNIQUE,
    username VARCHAR(30) NOT NULL,
    password VARCHAR(30) NOT NULL,
    PRIMARY KEY(idx)
);


CREATE TABLE IF NOT EXISTS user_prefs (
    maze_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL UNIQUE,
    mazeName VARCHAR(5),
    dateCreated DATE,

    FOREIGN KEY (maze_id) REFERENCES user(idx)
);



-- Add initial entries.

INSERT INTO
  user (username, password)
VALUES
  ('person', 'password'),
  ('me', '123'),
  ('dex', 'password');

INSERT INTO user_prefs (mazeName, dateCreated)
VALUES
  ('my Maze', '2000-12-2'),
  ('maze2', '2022-02-22');



-- Add initial entries.

INSERT INTO
  user (username, password)
VALUES
  ('person', 'password'),
  ('me', '123'),
  ('dex', 'password');

INSERT INTO
  user_prefs (mazeName, dateCreated)
VALUES
  ('my Maze', 'DATE', 10000),
  ('maze2', 'LOCALDATE', 10000);

