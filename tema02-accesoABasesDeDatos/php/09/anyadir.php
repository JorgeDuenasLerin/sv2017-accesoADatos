<?php
include "cabecera.php";
?>
        <form action="anyadir2.php" method="POST">
            Introduzca la nueva receta:<br>
            Nombre:<input type="text" name="nombre"><br>
            Detalles:<input type="text" name="detalles"><br>
            Minutos:<input type="text" name="minutos"><br>
            <input type="submit" value="Añadir">
        </form>
<?php
include "pie.php";
?>
