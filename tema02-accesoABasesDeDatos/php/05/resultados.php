<!doctype html>
<html>
    <head>
        <title>Ejercicio 5b</title>
        <meta charset="utf-8" />
    </head>
    
    <body>
        <h1>Ejercicio 5b</h1>
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

            $nombre = $_POST["nombre"];
            $consulta = "SELECT * FROM retos WHERE nombre 
                LIKE  '%$nombre%'";
                
            if ($resultado = $mysqli->query($consulta)) 
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
