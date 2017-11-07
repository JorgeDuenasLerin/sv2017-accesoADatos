-- Mario Belso

CREATE OR REPLACE FUNCTION obtenerLibrosPrestadosInicial(letra char) 
RETURNS SETOF libros AS 
$$
    SELECT cod, autor, titulo
    FROM libros, prestar
    WHERE LOWER(titulo) LIKE CONCAT(LOWER(letra),'%') 
    AND libros.cod = prestar.codLibro
    AND fechaDevolReal IS NULL;
$$
LANGUAGE sql;
