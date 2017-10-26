//Crear dos tablas relacion m:m y poder añadir datos y mostrarlos
//Antonio Sevila
package BBDD_tablas_m_m;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author antonio
 */
public class Tablas_m_m {

    public static void crearTablas(Connection con) {
        try{
            Statement statement = con.createStatement();
            String createTable = "CREATE TABLE IF NOT EXISTS programadores("
                    + "dni char(9) PRIMARY KEY,"
                    + "nombre varchar(15),"
                    + "apellidos varchar(30),"
                    + "edad numeric(3));";
            statement.executeUpdate(createTable);
            createTable = "CREATE TABLE IF NOT EXISTS lenguajes("
                    + "cod char(3) PRIMARY KEY,"
                    + "nombre varchar(15));";
            statement.executeUpdate(createTable);
            createTable = "CREATE TABLE IF NOT EXISTS conocer("
                    + "dni_programador char(9),"
                    + "cod_lenguaje char(3),"
                    + "nivel numeric(2),"
                    + "PRIMARY KEY (dni_programador, cod_lenguaje),"
                    + "FOREIGN KEY (dni_programador) REFERENCES programadores(dni),"
                    + "FOREIGN KEY (cod_lenguaje) REFERENCES lenguajes(cod));";
            statement.executeUpdate(createTable);
            statement.close();
        }
        catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    public static void anyadirProgramador(Connection con,String dni, String nombre,
            String apellidos, int edad){
        try{
            Statement statement = con.createStatement();
            String insertarDatos = "INSERT INTO programadores VALUES ('"+ dni +
                    "','" + nombre + "','" + apellidos + "','" + edad +"');";
            statement.executeUpdate(insertarDatos);
            System.out.println("Programador guardado satisfactoriamente.");
            statement.close();
        }
        catch(SQLException e){
            System.out.println("Error: No se ha podido guardar el programador.");
        }
    }
    public static void anyadirLenguaje(Connection con,String codigo,
            String nombre){
        try{
            Statement statement = con.createStatement();
            String insertarDatos = "INSERT INTO lenguajes VALUES ('"+ codigo +
                    "','" + nombre + "');";
            statement.executeUpdate(insertarDatos);
            System.out.println("Lenguaje guardado satisfactoriamente.");
            statement.close();
        }
        catch(SQLException e){
            System.out.println("Error: No se ha podido guardar el lenguaje.");
        }
    }
    public static void anyadirNivel(Connection con, String dni,
            String codigo, int nivel){
        try{
            Statement statement = con.createStatement();
            String insertarDatos = "INSERT INTO conocer VALUES ('"+ dni +
                    "','" + codigo+ "','" + nivel + "');";
            statement.executeUpdate(insertarDatos);
            System.out.println("Nivel guardado satisfactoriamente.");
            statement.close();
        }
        catch(SQLException e){
            System.out.println("Error: No se ha podido guardar el nivel.");
        }
    }
    public static void mostrarLenguajes(Connection con){
        try{
            Statement statement = con.createStatement();
            String mostrarDatos = "SELECT * FROM lenguajes;";
            ResultSet rs = statement.executeQuery(mostrarDatos);
            System.out.println(String.format("%-10s","Código")
                                + String.format("%-15s","Nombre lenguaje"));
            System.out.println("-----------------------------------------------");
            while(rs.next()){
                System.out.println(String.format("%-10s",rs.getString(1))
                                   + String.format("%-15s",rs.getString(2)));
            }
            statement.close();
            rs.close();
        }
        catch(SQLException e){
            System.out.println("Error. "+ e.getMessage());
        }
    }
    public static void mostrarDatosProgramador(Connection con, String dni){
        try{
            Statement statement = con.createStatement();
            String mostrarDatos = "SELECT dni,p.nombre,apellidos,"
                    + "edad, l.nombre AS lenguaje_programacion, nivel "
                    + "FROM lenguajes l, programadores p, conocer c "
                    + "WHERE p.dni='"+dni+"' AND c.cod_lenguaje=l.cod"
                    + " AND p.dni=c.dni_programador;";
            ResultSet rs = statement.executeQuery(mostrarDatos);
            System.out.println(String.format("%-20s","DNI")+
                                String.format("%-20s","Nombre")+
                                String.format("%-20s","Apellidos")+
                                String.format("%-20s","Edad")+
                                String.format("%-20s","Lenguaje")+
                                String.format("%-20s","Nivel"));
            System.out.println("--------------------------------------------"
                    + "-----------------------------------------------------"
                    + "--------");
            while(rs.next()){
                System.out.println(String.format("%-20s",rs.getString(1))
                                    +String.format("%-20s",rs.getString(2))
                                    +String.format("%-20s",rs.getString(3))
                                    +String.format("%-20s",rs.getInt(4))
                                    +String.format("%-20s",rs.getString(5))
                                    +String.format("%-20s",rs.getInt(6)));
            }
            statement.close();
            rs.close();
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("No se han encontrado datos de ese programador");
        }
    }
    
    public static void main(String[] args) {
        String opcion;
        Scanner entrada = new Scanner(System.in);
        String dni, nombre, apellidos;
        String cod, nombreLenguaje;
        int edad, nivel;
        try {
            Class.forName("org.postgresql.Driver");

            String url = "jdbc:postgresql://localhost:5432/personal";
            String usuario = "postgres";
            String password = "postgre2017";

            Connection con = DriverManager.getConnection(url, usuario, password);
            
            crearTablas(con);
            do{
                System.out.println("Menú: ");
                System.out.println("1. Añadir programador.");
                System.out.println("2. Añadir lenguaje de programación.");
                System.out.println("3. Añadir nivel de programación a"
                        + " un programador.");
                System.out.println("4. Buscar datos de programador.");
                System.out.println("S. Salir");
                opcion = entrada.nextLine();
                switch(opcion){
                    case "1":
                        do{
                            System.out.println("Introduce DNI del programador:");
                            dni = entrada.nextLine().trim().toLowerCase();
                            if( ! dni.matches("^[0-9]{8}[a-z]{1}")){
                                System.out.println("Error. Introduce un dni válido"
                                        + " (Ej. 12345678A)");
                            }
                        }
                        while( ! dni.matches("^[0-9]{8}[a-z]{1}"));
                        
                        System.out.println("Introduce nombre: ");
                        nombre = entrada.nextLine().trim().toLowerCase();
                        System.out.println("Introduce apellidos: ");
                        apellidos = entrada.nextLine().trim().toLowerCase();
                        System.out.println("Introduce edad: ");
                        edad = entrada.nextInt();
                        entrada.nextLine();
                        anyadirProgramador(con, dni,nombre,apellidos,edad);
                        break;
                    case "2":
                        do{
                            System.out.println("Introduce el código del"
                                    + " lenguaje: ");
                            cod = entrada.nextLine().trim().toLowerCase();
                            if(cod.length() != 3){
                                System.out.println("ERROR, el código debe tener"
                                        + "3 caracteres");
                            }
                        }
                        while(cod.length() != 3);
                        System.out.println("Introduce el nombre del lenguaje: ");
                        nombreLenguaje = entrada.nextLine().trim().toLowerCase();
                        anyadirLenguaje(con, cod, nombreLenguaje);
                        break;
                    case "3":
                        System.out.println("Introduce el dni del programador:");
                        dni = entrada.nextLine().trim().toLowerCase();
                        mostrarLenguajes(con);
                        System.out.println("Introduce el código del lenguaje: ");
                        cod = entrada.nextLine().trim().toLowerCase();
                        System.out.println("Introduce el nivel (0-10) del"
                                + " lenguaje");
                        nivel = entrada.nextInt();
                        entrada.nextLine();
                        
                        anyadirNivel(con,dni,cod,nivel);
                        break;
                    case "4":
                        System.out.println("Introduce DNI del programador: ");
                        dni = entrada.nextLine().trim().toLowerCase();
                        mostrarDatosProgramador(con,dni);
                        break;
                    case "S":
                        System.out.println("Adios!");
                        con.close();
                        break;
                }
                
            }
            while( ! opcion.equals("S"));
        }
        catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
        catch(ClassNotFoundException e){
            System.out.println("Error: " + e.getMessage());
        }

    }
}
