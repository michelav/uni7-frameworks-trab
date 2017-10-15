CREATE DATABASE distribuidora;

\connect supplier;

BEGIN;

-- drop tables
DROP TABLE IF EXISTS producao CASCADE;
DROP TABLE IF EXISTS estoque CASCADE;
DROP TABLE IF EXISTS pedidos CASCADE;

CREATE TYPE shirt_size as ENUM('S', 'M', 'L');

CREATE TABLE pedidos (
    rid serial primary key,
    loja varchar(20) not null
);

CREATE TABLE itens_pedido (
    rid integer references request (rid) not null,
    camisa varchar(20) not null,
    tamanho shirt_size not null,
    quantidade integer not null
);

CREATE TABLE producao (
    pid serial primary key,
    rid integer references request (rid) not null,
    camisa varchar(20) not null,
    tamanho shirt_size not null,
    quantidade integer not null
);

CREATE TABLE estoque (
    sid serial primary key,
    camisa varchar(20) not null,
    tamanho shirt_size not null,
    quantidade integer not null
);

COMMIT;
