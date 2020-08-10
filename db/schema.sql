CREATE TABLE Post
(
    id   SERIAL PRIMARY KEY,
    name TEXT
);
CREATE TABLE candidate
(
    id       SERIAL PRIMARY KEY,
    name     TEXT,
    photo_id int REFERENCES photo (id),
    city_id  int REFERENCES city (id)
);
CREATE TABLE photo
(
    id   SERIAL PRIMARY KEY,
    name TEXT
);
CREATE TABLE users
(
    id       SERIAL PRIMARY KEY,
    name     TEXT,
    email    TEXT,
    password TEXT
);
CREATE TABLE city
(
    id   SERIAL PRIMARY KEY,
    city TEXT
);