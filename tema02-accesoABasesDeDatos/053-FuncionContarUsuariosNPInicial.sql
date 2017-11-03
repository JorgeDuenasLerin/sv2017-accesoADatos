--Antonio Sevila

CREATE OR REPLACE FUNCTION ContarUsuariosNPInicial(letra CHAR(1)) 
RETURNS INTEGER 
AS
$$
DECLARE cantidad INTEGER;
BEGIN
    SELECT COUNT(*)
    INTO cantidad
    FROM usuarios
    WHERE nombre LIKE CONCAT(UPPER(letra), '%')
        AND cod NOT IN (SELECT codUsuario FROM prestar);
        
    RETURN cantidad;
END;
$$
LANGUAGE plpgsql;

-- SELECT ContarUsuariosNPInicial('m');
