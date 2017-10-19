/*
 *SERGIO GARCIA BALSAS
SAMUEL SERGIO GARCIA ESPINOSA
 */
package consultavideojuegos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ConsultaVideojuegos {
    
    public static void menu()
    {
        System.out.println("1. Añadir videojuego");
        System.out.println("2. Buscar videojuego");
        System.out.println("3. Modificar videojuego");
        System.out.println("4. Salir");
    }

    public static void main(String[] args) 
        throws ClassNotFoundException, SQLException {
        
        Class.forName("org.postgresql.Driver");

        String url = "jdbc:postgresql://localhost:5432/VIDEOJUEGOS";
        String usuario = "postgres";
        String password = "cactus22";
        Connection con = DriverManager.getConnection(url, usuario, password);
        
        // 1.CREAR TABLA DESDE JAVA
        Statement statementCreateTable = con.createStatement();
        String sentenciaSQLCreateTable = " CREATE TABLE IF NOT EXISTS videojuego("
                + "id int PRIMARY KEY,"
                + "titulo varchar(30),"
                + "plataforma varchar(30),"
                + "descripcion varchar(30));";
                    
        int cantidad = statementCreateTable.executeUpdate(sentenciaSQLCreateTable); 
        System.out.println("Tabla creada: " + cantidad);  

        statementCreateTable.close();
        
        //ESTO ES PARA LA SELECT:
        Statement statement = con.createStatement();
     
        boolean salir = false;
        
        do
        {
            Scanner sc = new Scanner(System.in);
            menu();
            String opcion = sc.nextLine();
           
            switch (opcion)
            {
                case "1": //Añadir
                                
                    System.out.println("titulo?");
                    String nuevoTitulo = sc.nextLine();
                    
                    System.out.println("plataforma?");
                    String nuevaPlataforma  = sc.nextLine();
                    
                    System.out.println("descripcion?");
                    String nuevaDescripcion  = sc.nextLine();
                   
                    System.out.println("Id?");
                    int id = sc.nextInt();
                    //vaciar buffer:
                     sc.nextLine();
                   
                    Statement statement2 = con.createStatement();
                    String sentenciaSQLInsert = "INSERT INTO videojuego VALUES ( " +
                            id +  ",'" +
                            nuevoTitulo + "'," +
                            "'" + nuevaPlataforma + "'," +
                            "'" + nuevaDescripcion + "');";
                    
                    int cantidad2 = statement.executeUpdate(sentenciaSQLInsert); 
                    System.out.println("Datos insertados: " + cantidad2);  
                       
                    statement2.close();
                    System.out.println();
                    
                    break;
                    
                case "2": //Mostrar TO DO BUSCAR por titulo
                    System.out.println("Titulo del juego a mostrar?");
                    String titulo2 = sc.nextLine().toLowerCase();
                    
                    //En esta SELECT usar RPAD para que salgan los datos formateados y no usar t
                String sentenciaSQL = "SELECT * FROM videojuego WHERE LOWER (titulo) ="
                     + "'"  + titulo2 +"';" ;
                
                     ResultSet rs = statement.executeQuery(sentenciaSQL);
                    System.out.println("id" + "\t "+ "TITULO" + "\t " + "\t "
                        + "Plataforma"  + "\t "+"\t "+ "Descripcion");
                       System.out.println("-----------------------------------------");

                       while (rs.next()) {
                           System.out.println(rs.getString(1) + "\t " 
                                   + rs.getString(2) 
                          + "\t "  + "\t " + rs.getString(3)  + "\t" 
                                   + "\t "+ rs.getString(4));
                       }
                         rs.close();
                    break;
                
                case "3":
                    System.out.println("ID del juego a modificar?");
                    int id2 = sc.nextInt();
                    sc.nextLine();
                    
                    System.out.println("Titulo nuevo?");
                    String titulonuevo = sc.nextLine();
                    
                    System.out.println("Plataforma nueva?");
                    String plataformanueva = sc.nextLine();
                    
                    System.out.println("Descripcion nueva");
                    String descripcionnueva = sc.nextLine();
                    
                    Statement statement4 = con.createStatement();
                    String sentenciaSQL4 = "UPDATE videojuego SET titulo= " +
                          "'"+  titulonuevo + "'" + "WHERE id = " + id2;
                     String sentenciaSQL5 = "UPDATE videojuego SET plataforma= " +
                          "'"+  plataformanueva + "'" + "WHERE id = " + id2;
                      String sentenciaSQL6 = "UPDATE videojuego SET descripcion= " +
                          "'"+   descripcionnueva  + "'" + "WHERE id = " + id2;       
                    
                    int cantidad4 = statement.executeUpdate(sentenciaSQL4); 
                    int cantidad5 = statement.executeUpdate(sentenciaSQL5); 
                    int cantidad6 = statement.executeUpdate(sentenciaSQL6); 
                    System.out.println("Datos insertados: " + cantidad4);  
                    System.out.println("Datos insertados: " + cantidad5);  
                    System.out.println("Datos insertados: " + cantidad6);  
                       
                       statement4.close();
                    break;

                case "4":
                    salir = true;
                    break;
                    
                default: System.out.println("Opcion no valida");
                     break;
            }
                    
        }
        while (! salir);   
        
        con.close();
    }
}