CREATE TYPE tipoRueda AS 
(ancho NUMERIC(3,0), llanta NUMERIC(2,0));

ALTER TABLE vehiculos
ADD rueda tipoRueda;
