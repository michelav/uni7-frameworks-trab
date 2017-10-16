CREATE DATABASE producao_camisas;

\connect producao_camisas;

BEGIN;

-- drop tables
DROP TABLE IF EXISTS itens_producao CASCADE;
DROP TABLE IF EXISTS producao CASCADE;

CREATE TABLE producao (
    pid serial primary key,
    rid integer not null,
    situacao varchar(12) check(situacao in ('SOLICITADA', 'CANCELADA', 'PRODUZIDA'))
);

CREATE TABLE itens_producao (
    tid serial primary key,
    pid integer references producao (pid) not null,
    camisa varchar(20) not null,
    tamanho varchar(1) check(tamanho in ('P', 'M', 'G')),
    quantidade integer not null,
    Unique (pid, camisa, tamanho)
);

COMMIT;
