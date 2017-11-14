
CREATE DATABASE vehiculosOO;

-- Para conectar en psql: \connect vehiculosOO

CREATE TABLE vehiculos 
(
    id VARCHAR(2) PRIMARY KEY,
    marca VARCHAR(50),
    modelo VARCHAR(50)
);

INSERT INTO vehiculos
    (id, marca, modelo)
VALUES
    ('O1','Orbea','Alma H50 MTB');

CREATE TABLE vehiculosMotor
(
    potencia NUMERIC(4)
) 
INHERITS(vehiculos);

INSERT INTO vehiculosMotor
    (id, marca, modelo, potencia)
VALUES
    ('O1','Opel','GrandLand X', 150);

SELECT * FROM vehiculosMotor;

--  id | marca |   modelo    | potencia
-- ----+-------+-------------+----------
--  O1 | Opel  | GrandLand X |      150
-- (1 fila)


SELECT * FROM vehiculos;

--  id | marca |    modelo
-- ----+-------+--------------
--  O1 | Orbea | Alma H50 MTB
--  O1 | Opel  | GrandLand X
-- (2 filas)

ALTER TABLE vehiculosMotor
ADD PRIMARY KEY(id);


INSERT INTO vehiculosMotor
    (id, marca, modelo, potencia)
VALUES
    ('O2','Opel','Mokka X', 110);
    
INSERT INTO vehiculos
    (id, marca, modelo)
VALUES
    ('O2','Orbea','Occam AM H50');

-- =====================================
-- Búsquedas en las tablas

-- Vehículos con una M en el modelo

SELECT * 
FROM vehiculos
WHERE LOWER(modelo) LIKE '%m%';

--  id | marca |    modelo
-- ----+-------+--------------
--  O1 | Orbea | Alma H50 MTB
--  O2 | Orbea | Occam AM H50
--  O2 | Opel  | Mokka X
-- (3 filas)

SELECT * 
FROM vehiculosMotor
WHERE LOWER(modelo) LIKE '%m%';

--  id | marca | modelo  | potencia
-- ----+-------+---------+----------
--  O2 | Opel  | Mokka X |      110
-- (1 fila)

SELECT * 
FROM ONLY vehiculos
WHERE LOWER(modelo) LIKE '%m%';

--  id | marca |    modelo
-- ----+-------+--------------
--  O1 | Orbea | Alma H50 MTB
--  O2 | Orbea | Occam AM H50
-- (2 filas)
