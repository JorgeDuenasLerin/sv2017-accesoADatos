-- NOMBRE MARCA Y MODELO TODO JUNTO
CREATE TYPE tiponombre AS (marca varchar(30), modelo varchar(30));

-- PROCESADOR MARCA, VELOCIDAD DOMINIO QUE TENDRÁ CHECK > 0 Y UNIDAD ENUM (HZ, KHZ, MHZ, GHZ)
CREATE TYPE unidadVelocidad AS ENUM ('Hz', 'KHz', 'MHz', 'GHz');
CREATE DOMAIN dominioVelocidad AS NUMERIC (6,3)
    CHECK (VALUE > 0);
CREATE TYPE tipoprocesador AS (marca varchar(30), 
    velocidad dominioVelocidad, unidad_velocidad unidadVelocidad  );

-- ALMACENAMIENTO [] CAPACIDAD CON UN DOMINIO NO PUEDE SER NEGATIVO. UNIDAD SERÁ OTRO ENUM BYTE, MG, K
CREATE TYPE tipoUnidad AS ENUM ('byte', 'Kb', 'Mb', 'Gb', 'Tb');
CREATE DOMAIN dominioCapacidad AS NUMERIC (6,2)
    CHECK (VALUE > 0);

CREATE TYPE tipoalmacenamiento AS 
    (capacidad dominioCapacidad, unidad tipoUnidad);

-- TABLA DE ORDENADORES
CREATE TABLE ORDENADORES 
(
    Nombre tiponombre PRIMARY KEY,
    Procesador tipoprocesador,
    Almacenamiento tipoalmacenamiento[]
);

INSERT INTO ordenadores (Nombre, Procesador, Almacenamiento) VALUES
(
    ('IBM', '5150'),
    ('8086',4.77, 'MHz'),
    ARRAY [(360, 'Kb'), (360, 'Kb') ]::tipoalmacenamiento[]
);
