-- Sergio Garcia Balsas

CREATE DOMAIN tipoPotencia AS NUMERIC (4,0)
CHECK (VALUE > 0);

CREATE TABLE coches 
(
    marca varchar(30),
    modelo varchar(50),
    peso numeric (6,2),
    potencia tipoPotencia,
    PRIMARY KEY(marca, modelo)
);
