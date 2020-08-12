CREATE TABLE halls
(
    id   SERIAL PRIMARY KEY,
    seat TEXT
);
CREATE TABLE accounts
(
    id      SERIAL PRIMARY KEY,
    name    TEXT,
    phone   TEXT,
    seat_id int REFERENCES halls (id)
);
-- alter table halls add column account_id int references accounts (id);