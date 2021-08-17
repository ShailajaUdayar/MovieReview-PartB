DROP schema movieDB IF EXISTS;
CREATE SCHEMA movieDB AUTHORIZATION SA;
DROP TABLE IF EXISTS Movie;
CREATE TABLE Movie (
                                id int(11) IDENTITY(1, 1) PRIMARY KEY,
                               title VARCHAR(250) NOT NULL,
                               genre VARCHAR(250) NOT NULL,
                               actor VARCHAR(250) NOT NULL,
                               year INTEGER NOT NULL,
                               rating DOUBLE NOT NULL
);