<?php /*
<!--
create table recetas(
  id serial primary key,
    nombre varchar(25),
    descripcion text,
    minutos numeric(3)
    );  

INSERT INTO recetas (nombre, descripcion, minutos)
VALUES ('arrocete', 'esta rico', 25),
        ('ensalada', 'sano', 5),
        ('filetaco', 'completo', 60);

    -->

*/ ?>
<!DOCTYPE html>
<html>
    <head>
        <title>Ejercicio 6b</title>
        <meta charset="utf-8"/>
    </head>
    <body>
        <h1>Ejercicio 6</h1>
        <p>
            <?php 
                if (isset($_POST["nombre"]))
                {
                    $nombre=$_POST["nombre"];
                    $mysqli = new mysqli("localhost", "root", "", "recetas");
                    if ($mysqli->connect_errno) {
                        printf("Falló la conexión: %s\n", $mysqli->connect_error);
                        echo "</p></body></html>";
                        exit();
                    }
                    $mysqli->query("SET NAMES utf8;");
                    if ($resultado = $mysqli->query("SELECT * FROM recetas where nombre like '%$nombre%';")) {
                        printf("La selección devolvió %d filas.<br />", $resultado->num_rows);
                        /* liberar el conjunto de resultados */
                        
                        while ($registro = $resultado->fetch_array(MYSQLI_ASSOC))
                        {
                            echo $registro["id"]. ": ".
                            $registro["nombre"]."<br/>".
                            $registro["descripcion"]."<br/>Minutos: ".
                            $registro["minutos"]."<br/>";
                        }
                        $resultado->close();
                    }
                }
                else
                {
                    echo "<p>Debe rellenar el <a href='index.php'>formulario</a></p>";
                }
            ?>
        </p>
    </body>
</html>

