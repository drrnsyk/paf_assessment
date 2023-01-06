CREATE DATABASE eshop;

CREATE TABLE customers
(
    name varchar(32) not null,
    address varchar(128) not null,
    email varchar(32) not null,
    primary key(name)
);

INSERT INTO customers (name, address, email)
VALUES ('fred', '201 Cobblestone Lane', 'fredflintstone@bedrock.com');
INSERT INTO customers (name, address, email)
VALUES ('sherlock', '221B Baker Street, London', 'sherlock@consultingdetective.org');
INSERT INTO customers (name, address, email)
VALUES ('spongebob', '124 Conch Street, Bikini Bottom', 'spongebob@yahoo.com');
INSERT INTO customers (name, address, email)
VALUES ('jessica', '698 Candlewood Land, Cabot Cove', 'fletcher@gmail.com');
INSERT INTO customers (name, address, email)
VALUES ('dursley', '4 Privet Drive, Little Whinging, Surrey', 'dursley@gmail.com');