CREATE DATABASE distribuidora;

\connect distribuidora;

BEGIN;

-- drop tables
DROP TABLE IF EXISTS pendencias CASCADE;
DROP TABLE IF EXISTS camisas CASCADE;
DROP TABLE IF EXISTS itens_pedido CASCADE;
DROP TABLE IF EXISTS pedidos CASCADE;

CREATE TABLE pedidos (
    rid serial primary key,
    loja varchar(20) not null
);

CREATE TABLE itens_pedido (
    tid serial primary key,
    rid integer references pedidos (rid) not null,
    camisa varchar(20) not null,
    tamanho varchar(1) check(tamanho in ('P', 'M', 'G')),
    quantidade integer not null,
    Unique (rid, camisa, tamanho)
);

CREATE TABLE pendencias (
    pid serial primary key,
    rid integer references pedidos (rid) not null,
    camisa varchar(20) not null,
    tamanho varchar(1) check(tamanho in ('P', 'M', 'G')),
    quantidade integer not null,
    Unique (rid, camisa, tamanho)
);

CREATE TABLE camisas (
    sid serial primary key,
    nome varchar(20) not null,
    tamanho varchar(1) check(tamanho in ('P', 'M', 'G')),
    quantidade integer not null,
    Unique (nome, tamanho)
);

INSERT INTO camisas (nome, tamanho, quantidade) VALUES ('Pink Floyd', 'P', 20);
INSERT INTO camisas (nome, tamanho, quantidade) VALUES ('Pink Floyd', 'M', 60);
INSERT INTO camisas (nome, tamanho, quantidade) VALUES ('Pink Floyd', 'G', 20);

INSERT INTO camisas (nome, tamanho, quantidade) VALUES ('Led Zeppelin', 'P', 25);
INSERT INTO camisas (nome, tamanho, quantidade) VALUES ('Led Zeppelin', 'M', 80);
INSERT INTO camisas (nome, tamanho, quantidade) VALUES ('Led Zeppelin', 'G', 25);

INSERT INTO camisas (nome, tamanho, quantidade) VALUES ('The Beatles', 'P', 15);
INSERT INTO camisas (nome, tamanho, quantidade) VALUES ('The Beatles', 'M', 45);
INSERT INTO camisas (nome, tamanho, quantidade) VALUES ('The Beatles', 'G', 40);

INSERT INTO camisas (nome, tamanho, quantidade) VALUES ('The Who', 'P', 30);
INSERT INTO camisas (nome, tamanho, quantidade) VALUES ('The Who', 'M', 100);
INSERT INTO camisas (nome, tamanho, quantidade) VALUES ('The Who', 'G', 30);

INSERT INTO camisas (nome, tamanho, quantidade) VALUES ('Iron Maiden', 'P', 20);
INSERT INTO camisas (nome, tamanho, quantidade) VALUES ('Iron Maiden', 'M', 30);
INSERT INTO camisas (nome, tamanho, quantidade) VALUES ('Iron Maiden', 'G', 60);

INSERT INTO camisas (nome, tamanho, quantidade) VALUES ('U2', 'P', 80);
INSERT INTO camisas (nome, tamanho, quantidade) VALUES ('U2', 'M', 150);
INSERT INTO camisas (nome, tamanho, quantidade) VALUES ('U2', 'G', 100);

INSERT INTO camisas (nome, tamanho, quantidade) VALUES ('Pearl Jam', 'P', 95);
INSERT INTO camisas (nome, tamanho, quantidade) VALUES ('Pearl Jam', 'M', 95);
INSERT INTO camisas (nome, tamanho, quantidade) VALUES ('Pearl Jam', 'G', 95);

INSERT INTO camisas (nome, tamanho, quantidade) VALUES ('Rush', 'M', 75);
INSERT INTO camisas (nome, tamanho, quantidade) VALUES ('Rush', 'G', 80);

COMMIT;
