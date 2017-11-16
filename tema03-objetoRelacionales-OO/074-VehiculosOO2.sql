CREATE DATABASE vehiculosDolor;

CREATE TABLE vehiculos(
    id VARCHAR(2) PRIMARY KEY,
    marca VARCHAR(50),
    modelo VARCHAR(50)
);

INSERT INTO vehiculos (id, marca, modelo)
VALUES('O1', 'Orbea', 'Alma H50 MTB');

CREATE TABLE vehiculosAMotor(
    potencia NUMERIC(4)
)INHERITS(vehiculos);

INSERT INTO vehiculosamotor(id, marca, modelo, potencia)
VALUES('O1', 'Opel', 'GrandLand X', 150);

INSERT INTO vehiculosamotor(id, marca, modelo, potencia)
VALUES('O1', 'Opel', 'GrandLand X', 150);

INSERT INTO vehiculos (id, marca, modelo)
VALUES('O2', 'Orbea', 'Occam AH H50');

INSERT INTO vehiculosamotor(id, marca, modelo, potencia)
VALUES('O2', 'Opel', 'Mokka X', 120);

SELECT * FROM vehiculos 
WHERE LOWER(modelo) like '%m%';

SELECT * FROM vehiculosamotor 
WHERE LOWER(modelo) like '%m%';

SELECT * FROM ONLY vehiculos
WHERE LOWER(vehiculos.modelo) like '%m%';

-- Esta es la parte nueva: un segundo nivel de herencia
-- (que no afecta en nada a la aplicación Java)

CREATE TABLE motos(
    cilindrada NUMERIC(4,0)
)INHERITS(vehiculosAMotor);

INSERT INTO motos(id, marca, modelo, cilindrada)
VALUES('S0', 'Suzuki', 'Me la invento', 230);
