create table coches
(
    matricula varchar(10) primary key,
    marca varchar(20), 
    modelo varchar(30),
    fechaMatriculacion date,
);

create table personas
(
    dni varchar(12) primary key,
    nombre varchar(20), 
    apellidos varchar(40),
    telefono varchar(20),
    email varchar(50)
);

CREATE TABLE revisar
(
    dni varchar(12), 
    matricula varchar(10), 
    fecharevActual date,
    fecharevLimite date,
    resultadoFavorable boolean,
    PRIMARY KEY(dni, matricula, fecharevActual),
    FOREIGN KEY (dni) REFERENCES personas(dni),
    FOREIGN KEY (matricula) REFERENCES coches(matricula)
);
