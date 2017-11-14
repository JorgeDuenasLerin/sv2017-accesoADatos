
-- Crea una tabla de "profesores" que contenga:
-- 
--  * Nombre (texto)
--  * Fecha de ingreso (dia, mes y año)
--  * Tipo (Interino / Funcionario)
--  * Cantidad de asignaturas que imparte (1 cifra, mayor que 0 y menor que 9)
--  * Nombres de las asignaturas (varias, texto)
    
--------------------------------------------

CREATE DATABASE profesores;

-- Dominio para la cantidad de asignaturas
CREATE DOMAIN dominioCantAsign AS NUMERIC(1)
    CHECK (VALUE > 0 AND VALUE < 9);

CREATE DOMAIN dominioCantAsign2 AS NUMERIC(1)
    CHECK (VALUE BETWEEN 1 AND 8);

-- Enumeración para el tipo de profesor
CREATE TYPE tipoTipo AS ENUM ('Interino', 'Funcionario');

-- Dato compuesto para fecha

CREATE TYPE tipoFecha AS (dia NUMERIC(2), 
    mes NUMERIC(2), anyo NUMERIC(4)); 


-- Tabla completa para profesores

CREATE TABLE profesores 
(
    id VARCHAR(12) PRIMARY KEY,
    nombre VARCHAR(40),
    fechaIngreso tipoFecha,
    tipo tipoTipo,
    cantidadAsignaturas dominioCantAsign,
    asignaturas VARCHAR(50)[]
);

-- Datos de ejemplo

INSERT INTO profesores
(id, nombre, fechaIngreso, tipo, cantidadAsignaturas, asignaturas)
VALUES
('jj','José Alberto',(22,10,2010),
    'Interino', 2,
    ARRAY['Sistemas informáticos', 'Contabilidad'] );

INSERT INTO profesores
(id, nombre, fechaIngreso.dia, fechaIngreso.mes, fechaIngreso.anyo,
    tipo, cantidadAsignaturas, asignaturas)
VALUES
('jjt','Juan Alberto',
    21,10,2011,
    'Funcionario', 2,
    '{"Sistemas extracontables", "Informaticidad"}' );
