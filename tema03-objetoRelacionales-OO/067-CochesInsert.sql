-- Añade los siguientes datos de coches:

-- Dodge Challenger SXT, 1.739 kg, 305 CV
INSERT INTO coches (marca, modelo, peso, potencia)
VALUES ('Dodge', 'Challenger SXT', 1739, 305 );

-- Seat Panda 35CV 1988, 728 kg, -1 CV
-- No se guarda: no cumple la restricción del dominio
INSERT INTO vehiculos (marca, modelo, peso, potencia)
VALUES ('Seat',  'Panda 35CV 1988', 728, -1); 

-- Dato compuesto para las ruedas, con paréntesis:
-- BMW M4 Coupé 2017, ? Kg, 431 CV, 255 / 18
INSERT INTO coches (marca, modelo, potencia, rueda)
VALUES ('BMW','M4 2017', 431, (255,18) );

-- Dato compuesto para las ruedas, desglosado:
INSERT INTO coches (marca, modelo, potencia, rueda.anchura, rueda.llanta)
VALUES ('BMW', 'M2 Coupé 2017', 370, 245, 19);

-- Dato enumerado para el tipo de coche:
-- DMC DeLorean 1982, 1230 Kg, 130 CV, ? , Deportivo
INSERT INTO vehiculos(marca, modelo, peso, potencia, tipo) 
VALUES ('DMC','Delorean 1982','1230kg',130,'Deportivo');

-- Vector para extras:
-- Tata Safari 2002, ? Kg, ? CV, ?, Todoterreno, 
--   Extras: Radiocasette + Elevalunas eléctricos
INSERT INTO vehiculos(marca, modelo, tipo, extras) 
VALUES ('Tata','Safari 2002','Todoterreno', 
    ARRAY['Radiocasette','Elevalunas electrico']);
