--Antonio Defez
CREATE OR REPLACE FUNCTION ContarPrestamos() RETURNS BIGINT AS '
SELECT COUNT(*) from prestar
WHERE fechaDevolReal IS NULL
' LANGUAGE sql;

select ContarPrestamos();
