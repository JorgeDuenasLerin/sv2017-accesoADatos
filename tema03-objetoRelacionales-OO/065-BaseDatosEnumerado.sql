CREATE TYPE tipoVehiculo
AS ENUM ( 'Urbano', 'Deportivo', 'Familiar', 
    'SUV', 'Todoterreno');

ALTER TABLE coche ADD tipo tipoVehiculo;
