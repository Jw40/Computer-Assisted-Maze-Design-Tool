-- Use database

CREATE DATABASE IF NOT EXISTS maze302project;
USE maze302project;

-- Delete tables if already exist.

DROP TABLE IF EXISTS maze302project.user_data;
-- Create tables.

CREATE TABLE user_data (
    mazeData VARCHAR(50) NOT NULL,
    authorName VARCHAR(30) NOT NULL,
    mazeName VARCHAR(30) NOT NULL,
    creationDate DATE NOT NULL,
    PRIMARY KEY(authorName)
);

-- Add test entries.

INSERT INTO
  user_data (mazeData, authorName, mazeName, creationDate)
VALUES
  ('maze_data_foo', 'york', 'the maze', '2000-12-16'),
  ('maze_data_foo', 'dex', 'ur maze', '1999-01-01'),
  ('maze_data_foo', 'james', 'my maze', '2022-02-22');

-- Test Queries
SELECT * FROM user_data;
