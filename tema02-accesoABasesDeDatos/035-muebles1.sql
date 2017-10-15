-- Creación de la base de datos

create database ikea;

-- Creación de la tabla

create table muebles(
    id int primary key,
    nombre varchar(30),
    precio numeric(6,2),
    material varchar(15)
);
