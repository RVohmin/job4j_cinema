CREATE TABLE halls
(
    id   SERIAL PRIMARY KEY,
    seat int not null
);
CREATE TABLE accounts
(
    id      SERIAL PRIMARY KEY,
    name    TEXT not null,
    phone   TEXT not null,
    seat_id int not null REFERENCES halls (id)
);
-- alter table halls add column account_id int references accounts (id);