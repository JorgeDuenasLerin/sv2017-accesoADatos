<!doctype html>
<html>
<head>
  <title>Prueba de JSP</title>
</head>
<body>
  <h1>Prueba de JSP</h1>
  <p>Fecha y hora actual: <% out.print(new java.util.Date());

    for(int i=0; i<100; i++) {
        out.print("¡Hola "+i+"!<br />");
    }

  %></p>
  <p>(Este fichero debería estar en tomcat/webapps/root/carpetaXYZ
  y se debe abrir como http://localhost:8080/carpetaXYZ/)</p>
</body>
</html>
