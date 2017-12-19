CREATE TABLE retos (
  id int(11) PRIMARY KEY,
  nombre varchar(50) NOT NULL,
  descripcion text NOT NULL,
  dificultad int(2) NOT NULL
);

INSERT INTO retos (id, nombre, descripcion, dificultad) VALUES
(1, 'El día de Navidad', 'Se debe comprobar si el día es 25 y el mes es 12', 2),
(2, 'La lotería de la peña Atlética', 'Comprobar los años pares', 2);
