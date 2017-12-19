<!doctype html>
<html>
    <head>
        <title>Ejercicio 4</title>
        <meta charset="utf-8" />
    </head>
    
    <body>
        <h1>Ejercicio 4</h1>
        <p>
            <?php
            $mysqli = new mysqli("localhost", 
                "root", "", "retosBD");
            
            if ($mysqli->connect_errno) 
            {
                printf("Falló la conexión: %s\n", $mysqli->connect_error);
                echo "</p></body></html>";
                exit();
            }
            
            $mysqli->query("SET NAMES utf8;");

            if ($resultado = $mysqli->query(
                "SELECT * FROM retos")) 
            {
                printf("La selección devolvió %d filas.<br />", 
                    $resultado->num_rows);
                    
                while ($registro = $resultado->fetch_array(MYSQLI_ASSOC))
                {
                    echo $registro["id"] . " " .
                        $registro["nombre"] . "<br />";
                }
            }

            /* liberar el conjunto de resultados */
            $resultado->close();
            
            // for($i=0; $i<100; $i++)
            //    echo "Hola, mundo " . $i . " - ";
            ?>
        </p>
    </body>
</html>
