CREATE DATABASE 'eshop';

USE DATABASE 'eshop';

CREATE TABLE 'customers';
(
    'name' varchar(32) not null,
    'address' varchar(128) not null,
    'email' varchar(32) not null,
    PRIMARY KEY('name')
);

INSERT INTO 'customers' ('name', 'address', 'email')
VALUES ('fred', '201 Cobblestone Lane', 'fredflintstone@bedrock.com');
INSERT INTO 'customers' ('name', 'address', 'email')
VALUES ('sherlock', '221B Baker Street, London', 'sherlock@consultingdetective.org');
INSERT INTO 'customers' ('name', 'address', 'email')
VALUES ('spongebob', '124 Conch Street, Bikini Bottom', 'spongebob@yahoo.com');
INSERT INTO 'customers' ('name', 'address', 'email')
VALUES ('jessica', '698 Candlewood Land, Cabot Cove', 'fletcher@gmail.com');
INSERT INTO 'customers' ('name', 'address', 'email')
VALUES ('dursley', '4 Privet Drive, Little Whinging, Surrey', 'dursley@gmail.com');

CREATE TABLE 'orders'
(
    'order_id' varchar(8) not null,
    'delivery_id' varchar(8) not null,
    'status' varchar(32) not null,
    'order_date' varchar(32) not null
    PRIMARY KEY('order_id')
);

CREATE TABLE 'line_item'
(
    'item_id' int not null AUTO_INCREMENT,
    'item' varchar(256) not null,
    'quantity' int not null
    'order_id' varchar(8) not null,
    PRIMARY KEY('item_id'),
    KEY 'fk_order_id' ('order_id'),
    CONSTRAINT 'fk_order_id' FOREIGN KEY ('order_id') REFERENCES 'orders' ('order_id'),
);