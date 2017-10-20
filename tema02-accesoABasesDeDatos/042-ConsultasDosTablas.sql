--Sentencias SQL dos tablas: portatiles-marcas.

--Crea una tabla "marcas", con campos id (alfabético) y nombre.
--Crea una tabla "portatiles", con campos id (autonumérico), marca 
--(que hará referencia al "id" de "marcas", modelo, procesador, memoria).
--Añade tres portátiles y tres marcas, que no estén totalmente relacionados 
--(por ejemplo, alguna marca de la que no tengamos ningún portátil).

create database portatiles;

create table marcas
(
    id char(3) primary key,
    marca varchar(15)
);

create table portatiles
(
    id serial primary key,
    marca char(3), 
    modelo varchar(20),
    procesador varchar(25),
    ramgb numeric(7,3),
    FOREIGN KEY (marca) REFERENCES marcas(id)
);

insert into marcas (id, marca) values('a', 'apple');
insert into marcas (id, marca) values('d', 'dell');
insert into marcas (id, marca) values('m', 'msi');
insert into marcas (id, marca) values('h', 'hp');

insert into portatiles(marca, modelo, procesador, ramgb)
values('a', 'Macbook Pro 2015', 'Intel i7 4820hq', '8');
insert into portatiles(marca, modelo, procesador, ramgb)
values('a', 'Macbook Air 2017', 'Intel i3 3200hq', '4');
insert into portatiles(marca, modelo, procesador, ramgb)
values('d', 'XPS', 'Intel i7 6700hq', '12');
insert into portatiles(marca, modelo, procesador, ramgb)
values('m', 'Sample model 56', 'Intel i7 7700hq', '16');



-- Muestra los datos de todos los portátiles (nombre de la marca, 
-- modelo, procesador, memoria).

SELECT id, marca, modelo, procesador, ramgb
FROM portatiles;

SELECT portatiles.id, marcas.marca, modelo, procesador, ramgb
FROM portatiles, marcas
WHERE marcas.id = portatiles.marca;

-- Muestra todas las marcas, junto con los modelos de esa marca (si 
-- existen; también deben aparecer las marcas de las que no tengamos 
-- ningún modelo de portátil).

SELECT marcas.marca, modelo 
FROM marcas
LEFT JOIN portatiles 
ON marcas.id = portatiles.marca

-- Muestra todos los modelos, junto el nombre de su marca (si existe; 
-- si alguna marca no existiera en la tabla "marcas" -no esperable, al ser 
-- clave ajena-), aun así debería aparecer el modelo.

SELECT modelo, marcas.marca
FROM portatiles
LEFT JOIN marcas 
ON marcas.id = portatiles.marca;


SELECT modelo, marcas.marca
FROM marcas
RIGHT JOIN portatiles
ON marcas.id = portatiles.marca;

-- Muestra las marcas de las que no tenemos ningún modelo.

select marca from marcas
where marcas.id 
NOT IN (select marca from portatiles );

-- Muestra los ordenadores de la marca más popular (aquella de 
-- la que tenemos información sobre más portátiles).

-- Paso 1: marcas y cantidad

SELECT marca, count(marca) 
FROM portatiles
GROUP BY marca;

-- Paso 2: máx de marcas y cantidad

SELECT marca, count(marca) cantidad
FROM portatiles
GROUP BY marca
ORDER BY cantidad DESC
LIMIT 1;

SELECT marca
FROM portatiles
GROUP BY marca
ORDER BY count(marca) DESC
LIMIT 1

-- Paso 3: 

SELECT * FROM portatiles WHERE portatiles.marca = 
(
SELECT marca
FROM portatiles
GROUP BY marca
ORDER BY count(marca) DESC
LIMIT 1
)
