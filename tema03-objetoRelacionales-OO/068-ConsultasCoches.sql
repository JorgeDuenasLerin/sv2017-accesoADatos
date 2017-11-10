-- ==============================================

-- Dominio
 
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

-- Tipo compuesto

CREATE TYPE tipoRueda AS 
(ancho NUMERIC(3,0), llanta NUMERIC(2,0));

ALTER TABLE coches
ADD rueda tipoRueda;

-- Tipo enumerado

CREATE TYPE tipoVehiculo
AS ENUM ( 'Urbano', 'Deportivo', 'Familiar', 
    'SUV', 'Todoterreno');

ALTER TABLE coches ADD tipo tipoVehiculo;

--Crear Vector

ALTER TABLE coches ADD extras VARCHAR(50)[];

-- ==============================================

-- Añade los siguientes datos de coches:

-- Dodge Challenger SXT, 1.739 kg, 305 CV
INSERT INTO coches (marca, modelo, peso, potencia)
VALUES ('Dodge', 'Challenger SXT', 1739, 305 );

-- Seat Panda 35CV 1988, 728 kg, -1 CV
-- No se guarda: no cumple la restricción del dominio
-- "ERROR:  el valor para el dominio tipopotencia viola la 
-- restricción «check» «tipopotencia_check»"
INSERT INTO coches (marca, modelo, peso, potencia)
VALUES ('Seat',  'Panda 35CV 1988', 728, -1); 

-- Dato compuesto para las ruedas, con paréntesis:
-- BMW M4 Coupé 2017, ? Kg, 431 CV, 255 / 18
INSERT INTO coches (marca, modelo, potencia, rueda)
VALUES ('BMW','M4 2017', 431, (255,18) );

-- Dato compuesto para las ruedas, desglosado:
INSERT INTO coches (marca, modelo, potencia, rueda.ancho, rueda.llanta)
VALUES ('BMW', 'M2 Coupé 2017', 370, 245, 19);

-- Dato enumerado para el tipo de coche:
-- DMC DeLorean 1982, 1230 Kg, 130 CV, ? , Deportivo
INSERT INTO coches(marca, modelo, peso, potencia, tipo) 
VALUES ('DMC','Delorean 1982',1230,130,'Deportivo');

-- Vector para extras:
-- Tata Safari 2002, ? Kg, ? CV, ?, Todoterreno, 
--   Extras: Radiocasette + Elevalunas eléctricos
INSERT INTO coches(marca, modelo, tipo, extras) 
VALUES ('Tata','Safari 2002','Todoterreno', 
    ARRAY['Radiocasette','Elevalunas electrico']);
-- Otro array, con la otra sintaxis
INSERT INTO coches(marca, modelo, tipo, extras) 
VALUES ('Ibiza','Special 1985','Urbano', 
    '{Rueda de repuesto,Espejo lateral derecho}');

-- ==============================================
-- información columnas de una tabla dada
select * from information_schema.columns
where table_name = 'coches';

-- Coches con mas de 200 cv
select * from coches
where potencia > 200;

-- Potencia negativa (no hay error, pero tampoco respuesta)
select * from coches
where potencia < 0;

-- Coches con ruedas de mas de 190 mm ancho
-- (nombre de la columna entre paréntesis, por ser dato compuesto)
select * 
from coches
where (rueda).ancho > 190;

-- Coches de tipo 'Deportivo'
select * 
from coches
where tipo = 'Deportivo';

-- Coches de tipo Nascar
-- "ERROR:  la sintaxis de entrada no es válida para el enum tipovehiculo: «Nascar»"
select * 
from coches
where tipo = 'Nascar';

-- Coches cuyo primer extra sea radiocasette
select * from coches
where extras[1] = 'Radiocasette';

-- = en cualquier parte
select * 
from coches
where 'Radiocasette' = ANY(extras)
