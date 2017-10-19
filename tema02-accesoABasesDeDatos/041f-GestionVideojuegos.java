//Anadir y modificar datos de una base de datos desde java
//Javier Montejo Lorente, Ivan Galan Pastor

package gestionvideojuegos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class GestionVideojuegos {
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Scanner teclado = new Scanner(System.in);
        String opcion;
        String titulo,genero,plataforma,resumen,id;
        
        int anyo;
        
        Class.forName("org.postgresql.Driver");

        String url = "jdbc:postgresql://localhost:5432/gestorVideojuegos";
        String usuario = "postgres";
        String password = "CAMBIARPORCONTRASEÑAQUETOQUE";
        Connection con = DriverManager.getConnection(url, usuario, password);

        Statement statement = con.createStatement();
        
        String sentenciaSQL = "CREATE TABLE if NOT EXISTS videojuegos (" +
            " id VARCHAR(10) PRIMARY KEY," +
            " titulo VARCHAR(30)," +
            " genero VARCHAR(30)," +
            " plataforma VARCHAR(30)," +
            " resumen VARCHAR(50)," +
            " anyo INTEGER" +
            ");";
        
        statement.executeUpdate(sentenciaSQL);
         
        do{
            System.out.println("Elige qué hacer: ");
            System.out.println("1. Añadir un videojuego");
            System.out.println("2. Buscar un videojuego");
            System.out.println("3. Modificar un videojuego");
            System.out.println("S. Salir");
            opcion = teclado.nextLine().toUpperCase();
            
            switch(opcion){
                case "1":
                    System.out.println("Introduce el ID del juego");
                    id= teclado.nextLine();
                    System.out.println("Introduce titulo del juego");
                    titulo = teclado.nextLine();
                    System.out.println("Introduce genero: ");
                    genero = teclado.nextLine();
                    System.out.println("Introduce plataforma: ");
                    plataforma = teclado.nextLine();
                    System.out.println("Introduce resumen: ");
                    resumen = teclado.nextLine();
                    System.out.println("Introduce año: ");
                    anyo = teclado.nextInt();
                    
                    teclado.nextLine();
                    
                    String sentenciaSQLanyadir = "INSERT INTO videojuegos "
                        + "('" + id + "', '" + titulo + "', '" +
                        genero + "', '" + plataforma + "', '" +
                            resumen + "', " + anyo + ");";
                
                    statement.executeUpdate(sentenciaSQLanyadir);
                    
                    break;
                case "2":
                    String idAux,dato;
        Scanner x = new Scanner(System.in);
        int opcionAux = 0;
                
        try{
            
            System.out.println("1.- Buscar por Titulo.");
            System.out.println("2.- Buscar por Genero.");
            System.out.println("3.- Buscar por Plataforma.");
            System.out.println("0.- No buscar.");
            opcionAux = x.nextInt();
            x.nextLine();
            
            switch(opcionAux){
                case 1:
                    System.out.println("Introduzca el titulo");
                    dato = x.nextLine();
                    sentenciaSQL = "select id,titulo,genero,plataforma from videojuegos where titulo like '%" + dato + "%'";
                    break;
                case 2:
                    System.out.println("Introduzca el Genero:");
                    dato = x.nextLine();
                    sentenciaSQL = "select id,titulo,genero,plataforma from videojuegos where genero like '%" + dato + "%'";
                    break;
                case 3:
                    System.out.println("Introduzca la plataforma:");
                    dato = x.nextLine();
                    sentenciaSQL = "select id,titulo,genero,plataforma from videojuegos where plataforma like '%" + dato + "%'";
                    break;
                case 0:
                    break;
                default:
                    break;
            }
            
            ResultSet rs = statement.executeQuery(sentenciaSQL);

            System.out.println("Id" + "Titulo" + "\t\t" + "Genero" + "\t\t" + "Plataforma");
            System.out.println("----------------------------------------------"
                    + "------------------------------------------------");

            while (rs.next()) {
                System.out.println(rs.getString(1) + rs.getString(2)
                        + rs.getString(3) + rs.getString(4));
            }
            
        }catch(SQLException e){
            System.out.println("SQL Exception");
        }
                    break;
                    
                case "3" :
                    String auxId,nuevoDato;
                    x = new Scanner(System.in);
                    int auxOpcion = 0;

                    try{

                        sentenciaSQL = "select rpad(id,20,' '),rpad(titulo,20,' '),rpad(genero,20,' '),"
                                + "rpad(plataforma,20,' ') from videojuegos";
                        ResultSet rs = statement.executeQuery(sentenciaSQL);

                        System.out.println("Id" + "Titulo" + "\t\t" + "Genero" + "\t\t" + "Plataforma");
                        System.out.println("----------------------------------------------"
                                + "------------------------------------------------");

                        while (rs.next()) {
                            System.out.println(rs.getString(1) + rs.getString(2)
                                    + rs.getString(3));
                        }

                        System.out.println("Introduzca el ID del juego que desea modificar: ");
                        id = x.nextLine();

                        System.out.println("1.- Modificar Titulo.");
                        System.out.println("2.- Modificar Genero.");
                        System.out.println("3.- Modificar Plataforma.");
                        System.out.println("4.- Modificar Resumen.");
                        System.out.println("0.- No modificar.");
                        auxOpcion = x.nextInt();
                        x.nextLine();

                        switch(auxOpcion){
                            case 1:
                                System.out.println("Introduzca el nuevo titulo");
                                nuevoDato = x.nextLine();
                                sentenciaSQL = "update videojuegos set titulo='" + nuevoDato + "' where id='" + id + "';";
                                break;
                            case 2:
                                System.out.println("Introduzca el nuevo Genero:");
                                nuevoDato = x.nextLine();
                                sentenciaSQL = "update videojuegos set genero='" + nuevoDato + "' where id='" + id + "';";
                                break;
                            case 3:
                                System.out.println("Introduzca la nueva plataforma:");
                                nuevoDato = x.nextLine();
                                sentenciaSQL = "update videojuegos set plataforma='" + nuevoDato + "' where id='" + id + "';";
                                break;
                            case 4:
                                System.out.println("Introduzca el nuevo Resumen:");
                                nuevoDato = x.nextLine();
                                sentenciaSQL = "update videojuegos set resumen='" + nuevoDato + "' where id='" + id + "';";
                                break;
                            case 0:
                                break;
                            default:
                                break;
                        }


                        int cantidad = statement.executeUpdate(sentenciaSQL);

                        statement.close();

                        if(cantidad>0)System.out.println("Se han modificado los datos");
                        else System.out.println("No se ha podido modificar");

                    }catch(SQLException e){
                        System.out.println("SQL Exception");
                    }
                    break;
                    
                case "S":
                    System.out.println("Adios");
                    break;
                default:
                    System.out.println("Selección no válida");
                    break;
            }
        }
        while( ! opcion.equals("S"));
        
        con.close();

    }
    
    
}