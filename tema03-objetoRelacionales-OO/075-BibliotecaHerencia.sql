-- Para mejorar nuestra biblioteca, queremos una tabla Documentos 
-- de la que hereden Libros y Revistas.
-- 
-- De cada documento guardaremos:
-- 
--     Título
--     Autor
--     Otros autores
--     Ubicación
--     Cantidad de páginas (>=0, máximo de 5 cifras)
--     Año de publicación ( <= 3000)
-- 
-- De cada libro, además:
-- 
--     Tapa (blanda / dura)
-- 
-- Y de cada revista:
-- 
--     Número
--     Mes de publicación (1 a 12)
    
-- Mario Belso

CREATE DOMAIN domPaginas AS NUMERIC(5,0)
    CHECK (VALUE BETWEEN 0 AND 99999);

CREATE DOMAIN domAnyoPublicacion AS NUMERIC(4)
    CHECK (VALUE <= 3000);

CREATE TABLE documentos(
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(60),
    autor VARCHAR(70),
    otrosAutores VARCHAR(70)[],
    ubicacion VARCHAR(25),
    paginas domPaginas,
    anyoPublicacion domAnyoPublicacion
);


CREATE TYPE tipoTapa AS 
ENUM ('Blanda', 'Dura');

CREATE TABLE libros(
    tapa tipoTapa,
    PRIMARY KEY(id)
)INHERITS (documentos);

CREATE DOMAIN domMes AS NUMERIC(2,0)
    CHECK (VALUE BETWEEN 1 AND 12);

CREATE TABLE revistas(
    numero NUMERIC(4),
    mes domMes,
    PRIMARY KEY(id)
)INHERITS(documentos);

INSERT INTO libros(titulo, autor, tapa, ubicacion)
VALUES ('La celestina', 'Fernando de Rojas', 'Blanda', 'AB01');

INSERT INTO revistas(titulo, autor, numero, ubicacion)
VALUES ('Pc World España', '(varios)', 58, 'CA03');

-- Para conectar desde "psql":
-- \c bibliotecaOO

SELECT id, titulo, autor, ubicacion FROM documentos;

--  id |     titulo      |       autor       | ubicacion
-- ----+-----------------+-------------------+-----------
--   1 | La celestina    | Fernando de Rojas | AB01
--   2 | Pc World Espa±a | (varios)          | CA03
