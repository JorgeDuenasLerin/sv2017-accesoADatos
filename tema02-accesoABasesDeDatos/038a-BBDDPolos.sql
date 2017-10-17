-- Base de datos Polos

CREATE DATABASE polos;

CREATE TABLE polos (
    id int primary key,
    marca varchar(20),
    sabor varchar(20),
    precio numeric(4,2)
);

INSERT INTO polos VALUES(1, 'Kalise', 'Fresa', 1.95);
INSERT INTO polos VALUES(2, 'Kalise', 'Chocolate', 2.95);
INSERT INTO polos VALUES(3, 'Frigo', 'Frigodedo fresa', 1.95);
