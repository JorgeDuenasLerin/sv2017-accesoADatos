-- Repaso de SQL 1
-- SERGIO GARCIA BALSAS

INSERT INTO muebles VALUES (200111413, 'Mesa LACK' , 10 , 'Conglomerado');
INSERT INTO muebles VALUES (702611150, 'Silla MARKUS' , 159 , 'Varios');
INSERT INTO muebles VALUES (791229203, 'Sofá EKTORP' , 249 , 'Tela beige');

-- Ver todos los datos
SELECT * 
FROM muebles;

-- Nombre de los artículos 
-- de más de 100 euros ordenado 
-- (por nombre, ascendente)

SELECT nombre, precio 
FROM muebles 
WHERE precio > 100 
ORDER BY nombre;

-- Sillas de menos de 200 euros 
-- ordenadas por precio descendente
SELECT nombre, precio 
FROM muebles 
WHERE LOWER(nombre) LIKE ('%silla%') 
    AND precio < 200 
ORDER BY precio DESC;

-- Cambiar a 269 el precio de los 
-- muebles Ektorp
UPDATE muebles 
SET precio = 269 
WHERE LOWER(nombre) LIKE ('%ektorp%');

-- Añadir el campo "unidades" a la tabla (podrá tener valores de 0 a 99.999)
ALTER TABLE muebles 
ADD unidades NUMERIC(5);
