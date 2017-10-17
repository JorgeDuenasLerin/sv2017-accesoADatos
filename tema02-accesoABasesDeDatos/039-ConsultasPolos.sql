-- Consultas sobre la base de datos "polos"

-- POLOS ORDENADOS POR MARCA ASC Y PRECIO DESCENDENTE.
SELECT marca, sabor, precio 
FROM polos 
ORDER BY marca ASC, precio DESC;

-- POLOS DE CHOCOLATE DE MARCAS QUE EMPIECEN POR F CON PRECIOS ENTRE 1 Y 3 EUROS INCLUIDOS
SELECT marca, sabor, precio 
FROM polos 
WHERE LOWER(sabor) LIKE '%chocolate%' 
  AND precio >= 1 AND precio <= 3 
  AND LOWER(MARCA) LIKE 'f%';
  
-- (o, de forma alternativa)
SELECT marca, sabor, precio 
FROM polos 
WHERE LOWER(sabor) LIKE '%chocolate%' 
  AND precio BETWEEN 1 AND 3 
  AND LOWER(MARCA) LIKE 'f%';

--POLOS DE LOS QUE NO SE HAN INDICADO SABOR.
SELECT id, marca
FROM polos
WHERE sabor IS NULL OR sabor = '';

--MARCA CON 2 O MAS POLOS.
SELECT marca, COUNT(marca)
FROM polos GROUP BY marca 
HAVING COUNT(marca) >= 2;
