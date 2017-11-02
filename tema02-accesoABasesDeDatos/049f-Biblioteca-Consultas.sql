-- Creación de tablas

CREATE TABLE IF NOT EXISTS libros (
    cod VARCHAR(10) PRIMARY KEY,
    autor VARCHAR(40),
    titulo VARCHAR(40)
);

CREATE TABLE IF NOT EXISTS usuarios (
    cod VARCHAR(10) PRIMARY KEY,
    nombre VARCHAR(40)
);

CREATE TABLE IF NOT EXISTS prestar (
    codUsuario VARCHAR(10),
    codLibro VARCHAR(10),
    fechaPrest DATE,
    fechaDevolPrevista DATE,
    fechaDevolReal DATE,
    PRIMARY KEY (codUsuario, codLibro, fechaPrest),
    FOREIGN KEY (codUsuario) REFERENCES usuarios(cod),
    FOREIGN KEY (codLibro) REFERENCES libros(cod)
);

-- Datos de usuarios de ejemplo

INSERT INTO usuarios (cod, nombre) VALUES
('u001', 'Michael Jordan'),
('u002', 'Magic Johnson'),
('u003', 'Isaiah Thomas'),
('u004', 'Kobe Bryant');

-- Datos de libros de ejemplo

INSERT INTO libros (cod, autor, titulo) VALUES
('l001', 'Stephen King', 'La zona muerta'),
('l002', 'Edgar Allan Poe', 'Selected tales'),
('l003', 'Robert Harris', 'Enigma'),
('l004', 'Ian Fleming', 'Doctor No'),
('l005', 'J.R.R. Tolkien', 'El hobbit');

-- Datos de préstamos de ejemplo

INSERT INTO prestar 
(codUsuario, codLibro, fechaPrest, fechaDevolPrevista, fechaDevolReal) 
VALUES
('u001','l002', '2017-09-12', '2017-09-27', '2017-09-18'),
('u001','l002', '2017-10-27', '2017-11-11', NULL),
('u003', 'l003', '2017-08-11', '2017-08-26', NULL),
('u004', 'l001', '2017-10-30', '2017-11-14', NULL);

-- Ver datos introducidos (1): préstamos devueltos

SELECT nombre, titulo, autor, fechaPrest, fechaDevolReal
FROM usuarios, libros, prestar
WHERE usuarios.cod = codUsuario 
    AND libros.cod = codLibro 
    AND fechaDevolReal IS NOT NULL;

-- Ver datos introducidos (2): préstamos sin devolver

SELECT nombre, titulo, autor, fechaPrest, fechaDevolPrevista
FROM usuarios, libros, prestar
WHERE usuarios.cod = codUsuario 
    AND libros.cod = codLibro 
    AND fechaDevolReal IS NULL;
    
-- Ejemplo de devolución de préstamo

UPDATE prestar SET fechaDevolReal = CURRENT_DATE
WHERE codUsuario = 'u004'
    AND codLibro = 'l001'
    AND fechaPrest = '2017-10-30';

-- Ver morosos

SELECT nombre, titulo, autor, fechaDevolPrevista
FROM usuarios, libros, prestar
WHERE usuarios.cod = codUsuario 
    AND libros.cod = codLibro 
    AND fechaDevolPrevista < CURRENT_DATE
    AND fechaDevolReal IS NULL;
