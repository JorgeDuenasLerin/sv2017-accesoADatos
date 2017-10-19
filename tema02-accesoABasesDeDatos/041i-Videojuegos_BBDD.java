//Crear base de datos videojuego
//Roberto Garcia, Antonio Sevila
package EJERCICIO_NOTA;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Videojuegos_BBDD {

    public static void main(String[] args) throws SQLException {
        String opcion = "";
        String tituloBuscar;
        Scanner sc = new Scanner(System.in);
        boolean salida = false;
        Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/videojuegos",
                "postgres", "postgre2017");
        crearTabla(con);
        
        do{
            System.out.println("1. Añadir Videojuego");
            System.out.println("2. Buscar Videojuego");
            System.out.println("3. Modificar Vieojuego");
            System.out.println("Selecciona opcion: ");
            opcion = sc.nextLine();
           
            
            switch(opcion){
                case "1":
                    String titulo = "";
                    String genero = "";
                    int anyo = 0;
                    String plataforma = "";
                    String resumen = "";
                    int id;
                    System.out.println("ID: ");
                    id = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Titulo: ");
                    titulo = sc.nextLine();
                    System.out.println("Genero: ");
                    genero = sc.nextLine();
                    System.out.println("Año de lanzamiento: ");
                    anyo = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Plataforma: ");
                    plataforma = sc.nextLine();
                    System.out.println("Resumen: ");
                    resumen = sc.nextLine();
                    
                    anyadirVideojuego(con,id, titulo, genero, anyo,
                            plataforma,resumen);
                    break;
                case "2":
                    System.out.println("Introduce el titulo a buscar: ");
                    tituloBuscar = sc.nextLine();
                    BuscarVideojuego(tituloBuscar, con);
                    break;
                case "3":
                    System.out.println("Introduce el titulo a buscar: ");
                    tituloBuscar = sc.nextLine();
                    modificarVideojuego(con, tituloBuscar);
                    break;
                case "0":
                    salida = true;
                    break;
                default:
                    System.out.println("OPCION INCORRECTA");
                    break;
            }
        }
        while(!salida);
    }
    public static void anyadirVideojuego(Connection conexion, int id,
            String titulo, String genero, int anyo, String plataforma,
            String resumen){
        try{
            
            Statement statement = conexion.createStatement();
            

            String sentenciaSQL = "INSERT INTO videojuegos VALUES ("+id+",'"+titulo
                    +"','"+genero+"',"+anyo+",'"+plataforma+"','"+resumen+"');";
            int totalInsertado = statement.executeUpdate(sentenciaSQL);
            if(totalInsertado >0){
                System.out.println(totalInsertado + " líneas insetadas.");
            }
            else{
                System.out.println("No se han podido insertar lineas");
            }
        }
        catch(SQLException e){
            System.out.println("Problema de base de datos");
        }
    }
    public static void modificarVideojuego(Connection conexion, String titulo){
        String nuevoDatoString ="";
        int nuevoDatoInt = 0;
        Scanner teclado = new Scanner(System.in);
        try{
            Statement statement = conexion.createStatement();
            

            String sentenciaSQL = "SELECT * FROM videojuegos"
                    + " WHERE LOWER(titulo) LIKE '%"+titulo+"%';";
            ResultSet rs = statement.executeQuery(sentenciaSQL);
            while(rs.next()){
                
                System.out.println("Titulo: "+rs.getString(2)+" -> Nuevo dato: ");
                nuevoDatoString = teclado.nextLine();
                if( ! nuevoDatoString.equals("")){
                    sentenciaSQL = "UPDATE TABLE videojuegos SET titulo='"
                        +nuevoDatoString+"' WHERE LOWER(titulo) LIKE '%"+titulo+"%';";
                    statement.executeUpdate(sentenciaSQL);
                }
                nuevoDatoString = "";
                
                System.out.println("Genero: "+rs.getString(3)+" -> Nuevo dato: ");
                nuevoDatoString = teclado.nextLine();
                if( ! nuevoDatoString.equals("")){
                    sentenciaSQL = "UPDATE TABLE videojuegos SET genero='"
                        +nuevoDatoString+"' WHERE LOWER(titulo) LIKE '%"+titulo+"%';";
                    statement.executeUpdate(sentenciaSQL);
                }


                System.out.println("Año: "+rs.getInt(4)+" -> Nuevo dato: ");
                nuevoDatoInt = teclado.nextInt();
                teclado.nextLine();
                if( ! nuevoDatoString.equals("")){
                    sentenciaSQL = "UPDATE TABLE videojuegos SET anyo="
                        +nuevoDatoString+" WHERE LOWER(titulo) LIKE '%"+titulo+"%';";
                    statement.executeUpdate(sentenciaSQL);
                }
                nuevoDatoString = "";


                System.out.println("Plataforma: "+rs.getString(5)+" -> Nuevo dato: ");
                nuevoDatoString = teclado.nextLine();
                if( ! nuevoDatoString.equals("")){
                    sentenciaSQL = "UPDATE TABLE videojuegos SET plataforma='"
                        +nuevoDatoString+"' WHERE LOWER(titulo) LIKE '%"+titulo+"%';";
                    statement.executeUpdate(sentenciaSQL);
                }
                nuevoDatoString = "";

                System.out.println("Resumen: "+rs.getString(6)+" -> Nuevo dato: ");
                nuevoDatoString = teclado.nextLine();
                if( ! nuevoDatoString.equals("")){
                    sentenciaSQL = "UPDATE TABLE videojuegos SET resumen='"
                        +nuevoDatoString+"' WHERE LOWER(titulo) LIKE '%"+titulo+"%';";
                    statement.executeUpdate(sentenciaSQL);
                }
                nuevoDatoString = "";
                
            }
            
        }
        catch(SQLException e){
            System.out.println("Problema de base de datos");
        }
    }
    // Buscar Videojuego
    public static void BuscarVideojuego(
            String titulo, Connection con) throws SQLException{
        Statement statement = con.createStatement();
        String sentenciaSQL = "SELECT * FROM videojuegos "
                + "WHERE LOWER(titulo) LIKE '%" + titulo.toLowerCase() + "%'";
        ResultSet rs = statement.executeQuery(sentenciaSQL);

        System.out.println("ID" + "\t" + "Titulo" + "\t" + "Genero" + "\t" + 
                "Año" + "\t" + "Plataforma" + "\t" + "Resumen");
        System.out.println("-----------------------------------------------------"
                + "--------------");

        while (rs.next()) {
            System.out.println(rs.getInt(1) + "\t " + rs.getString(2) + "\t "
                    + rs.getString(3) + rs.getInt(4) + "\t " + rs.getString(5)
                    + "\t " + rs.getString(6));
        }
    }
    public static void crearTabla(Connection con) throws SQLException{
        Statement statement = con.createStatement();
        String sentenciaSQL = "CREATE TABLE videojuegos (id int PRIMARY KEY, "
                    + "titulo VARCHAR(30), genero VARCHAR(30), anyo int,"
                    + "plataforma VARCHAR(30), resumen VARCHAR(100));";
        ResultSet rs = statement.executeQuery(sentenciaSQL);
    }

}