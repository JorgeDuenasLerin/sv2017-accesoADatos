<?php
include "cabecera.php";
?>
        <p>
        <?php 
            if (isset($_POST["nombre"]))
            {
                $nombre = htmlspecialchars($_POST["nombre"]);
                $detalles = $_POST["detalles"];
                $minutos = $_POST["minutos"];
                
                $mysqli = new mysqli("localhost", "root", "", "recetas");
                $mysqli->query("SET NAMES utf8;");
                
                $orden = "INSERT INTO recetas VALUES
                    (null, '$nombre', '$detalles', $minutos);";
                //echo $orden;
                $resultado = $mysqli->query($orden);
                echo $resultado . " datos insertados";
            }
            else
            {
                echo "<p>Debe rellenar el <a href='index.php'>formulario</a></p>";
            }
        ?>
        </p>
<?php
include "pie.php";
?>

