<!DOCTYPE html>
<html>
    <head>
        <title>Ejercicio 7</title>
        <meta charset="utf-8"/>
    </head>
    <body>
        <h1>Ejercicio 7</h1>
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
        ?>    
            <form method="POST">
                Introduzca parte del nombre de la receta:<br>
                <input type="text" name="nombre"><br>
                <input type="submit" value="Buscar">
            </form>
        <?php
        }
        ?>
    </body>
</html>
