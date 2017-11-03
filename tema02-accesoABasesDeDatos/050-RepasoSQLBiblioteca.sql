-- Repaso de SQL, biblioteca

-- 1. Libros que no se han prestado nunca.

SELECT titulo
FROM libros
WHERE cod NOT IN 
    (SELECT DISTINCT codLibro FROM prestar);
    
-- 2. Libros cuyo título contiene "Quijote" y que no se han prestado nunca.

SELECT titulo
FROM libros
WHERE LOWER(titulo) LIKE '%quijote%'
AND cod NOT IN 
    (SELECT DISTINCT codLibro FROM prestar);

-- 3. El libro que más veces se ha prestado.

-- 3.1. Previo: Libros prestados, decreciente

SELECT codLibro, COUNT(*) cantidad
FROM prestar
GROUP BY codLibro
ORDER BY cantidad DESC

-- 3.2. Ahora ya sí, la consulta completa

SELECT titulo, autor
FROM libros
WHERE cod =
(
    SELECT codLibro
    FROM prestar
    GROUP BY codLibro
    ORDER BY COUNT(*) DESC
    LIMIT 1
)


-- 4. El libro que se ha prestado a más usuarios distintos.

-- 4.1. Libros y personas a las que se ha prestado, incluyendo repetidos

SELECT codLibro, codUsuario
FROM prestar;

-- 4.2. Libros y personas a las que se ha prestado, sin repetidos

SELECT DISTINCT codLibro, codUsuario
FROM prestar;

--4.3. Y ahora ya podemos agrupar

SELECT codLibro, COUNT(DISTINCT codUsuario) AS cantidad
FROM prestar
GROUP BY codLibro
ORDER BY cantidad DESC
LIMIT 1;

-- 4.4. Y completar la consulta...

SELECT cod, titulo, autor FROM libros
WHERE cod =
    (
    SELECT codLibro
    FROM prestar
    GROUP BY codLibro
    ORDER BY COUNT(DISTINCT codUsuario) DESC
    LIMIT 1
    );

-- 5. Usuarios que han devuelto tarde algún libro.

SELECT nombre
FROM usuarios, prestar
WHERE usuarios.cod = codUsuario
AND fechaDevolReal > fechaDevolPrevista;

-- 6. Usuario que más libros ha devuelto tarde.

SELECT nombre, COUNT(*) AS cantidad
FROM usuarios, prestar
WHERE usuarios.cod = codUsuario
AND fechaDevolReal > fechaDevolPrevista
GROUP BY nombre
ORDER BY cantidad DESC
LIMIT 1;

-- 7. Usuario que más libros se ha llevado en préstamo.
--    (tanto los que aún tiene como los que ha leído y devuelto)
    
SELECT nombre, COUNT(*) AS cantidad
FROM usuarios, prestar
WHERE usuarios.cod = codUsuario
GROUP BY nombre
ORDER BY cantidad DESC
LIMIT 1;

-- 8. Nombres de usuario que estén repetidos.

SELECT nombre, COUNT(*)
FROM usuarios
GROUP BY nombre
HAVING COUNT(*) > 1;

-- 9. Usuarios que han tomado prestado algún libro 
--    cuyo título contiene la palabra "bomba".

SELECT nombre, titulo
FROM usuarios, libros, prestar
WHERE prestar.codLibro = libros.cod
AND usuarios.cod = prestar.codUsuario
AND LOWER(titulo) LIKE '%bomba%';

-- 10. Usuarios que han leído el libro que más veces se ha prestado.

SELECT DISTINCT nombre 
FROM usuarios, prestar 
WHERE usuarios.cod = codUsuario AND codLibro = 
    (SELECT codLibro
    FROM prestar
    GROUP BY codLibro
    ORDER BY COUNT(*) DESC
    LIMIT 1);
