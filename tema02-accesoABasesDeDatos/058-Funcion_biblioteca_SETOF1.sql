
-- Antonio Sevila
-- Funcion para devolver los libros que están prestados
CREATE OR REPLACE FUNCTION ObtenerLibrosPrestados ()
RETURNS SETOF libros AS '
    SELECT libros.*
    FROM libros, prestar
    WHERE fechadevolreal IS NULL AND codLibro=cod;
' LANGUAGE sql;