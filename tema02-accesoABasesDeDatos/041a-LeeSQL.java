//Mario Belso
//Azahara Carbonell
//ABM juegos PostrgreSQL.
//En postreSQL se ha hecho un "create database videojuegos;"
package leesql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class LeeSQL {

    public static void main(String[] args) throws ClassNotFoundException,
            SQLException {
        Scanner sc = new Scanner(System.in);
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/videojuegos";
        String usuario = "postgres";
        String password = "admin";
        Connection con = DriverManager.getConnection(url, usuario, password);
        Statement statement = con.createStatement();
        String opc = "  ";
        crearTabla(statement);
        do {
            menu();
            opc = sc.nextLine();
            switch (opc) {
                case "1":
                    String stentenciaSQL = anyadirJuego(statement);
                    int datos = statement.executeUpdate(stentenciaSQL);
                    System.out.println(datos + " añadidos");
                    break;
                case "2":
                    System.out.println("1.- Mostrar todos.");
                    System.out.println("2.- Mostrar por titulo.");
                    opc = sc.nextLine();
                    if (opc.equals("1")) {
                        mostrarTodo(statement);
                    } else {
                        mostrarPorTitulo(statement);
                    }
                    break;
                case "3":
                    modificar(statement);
                    break;
                case "0":
                    System.out.println("Cerrando conexion con PostreSQL.");
            }
        } while (!opc.equals("0"));
        statement.close(); //Por si acaso.
        con.close();
    }

    public static String anyadirJuego(Statement statement) {
        Scanner sc = new Scanner(System.in);
        String titulo, genero, anyo, plataforma, descripcion;
        System.out.println("Introduzca el título: ");
        titulo = sc.nextLine();
        System.out.println("Introduzca el género: ");
        genero = sc.nextLine();
        System.out.println("Introduzca el año: ");
        anyo = sc.nextLine();
        System.out.println("Introduzca la plataforma: ");
        plataforma = sc.nextLine();
        System.out.println("Introduzca una descripción: ");
        descripcion = sc.nextLine();
        return "INSERT INTO videojuegos (titulo, genero, anyo, plataforma,"
                + "descripcion) "
                + "VALUES ('" + titulo + "','" + genero + "','" + anyo + "','"
                + plataforma + "','" + descripcion
                + "');";
    }

    public static void menu() {
        System.out.println("1.- Añadir videojuego.");
        System.out.println("2.- Mostrar videojuegos.");
        System.out.println("3.- Modificar videojuego.");
        System.out.println("0.- Salir.");
        System.out.print("Introduzca una opción: ");
    }

    public static void mostrarTodo(Statement statement)
            throws ClassNotFoundException, SQLException {
        String sentenciaSQL = "SELECT * FROM videojuegos ORDER BY titulo";

        ResultSet rs = statement.executeQuery(sentenciaSQL);

        System.out.println("titulo" + "\t" + "genero" + "\t" + "anyo"
                + "\t" + "plataforma" + "\t" + "descripcion");
        System.out.println("-------------------------------------");

        while (rs.next()) {
            System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t"
                    + rs.getString(3) + "\t" + rs.getString(4) + "\t"
                    + rs.getString(5));
        }
        rs.close();
    }

    public static void mostrarPorTitulo(Statement statement)
            throws ClassNotFoundException, SQLException {
        String titulo;
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca el titulo a buscar: ");
        titulo = sc.nextLine();
        String sentenciaSQL = "SELECT * FROM videojuegos "
                + "where LOWER(titulo) like '%" + titulo.toLowerCase() + "%'"
                + "ORDER BY titulo;";

        ResultSet rs = statement.executeQuery(sentenciaSQL);

        System.out.println("titulo" + "\t" + "genero" + "\t" + "anyo"
                + "\t" + "plataforma" + "\t" + "descripcion");
        System.out.println("-------------------------------------");

        while (rs.next()) {
            System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t"
                    + rs.getString(3) + "\t" + rs.getString(4) + "\t"
                    + rs.getString(5));
        }
        rs.close();
    }

    public static void crearTabla(Statement statement)
            throws ClassNotFoundException, SQLException {
        String sentenciaSQL = "CREATE TABLE IF NOT EXISTS videojuegos ( "
                + "titulo VARCHAR(39) PRIMARY KEY , "
                + "genero VARCHAR(20), "
                + "anyo VARCHAR(5),"
                + "plataforma VARCHAR(15),"
                + "descripcion VARCHAR(100)"
                + ");";
        statement.executeUpdate(sentenciaSQL);
    }

    public static void modificar(Statement statement)
            throws ClassNotFoundException, SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Titulo videojuego a modificar:");
        String nombre = sc.nextLine();
        System.out.println("Plataforma videojuego a modificar:");
        String plataforma = sc.nextLine();
        System.out.println("Nuevo titulo:");
        String nombreN = sc.nextLine();
        System.out.println("Nueva plataforma:");
        String plataformaN = sc.nextLine();
        System.out.println("Nuevo genero:");
        String generoN = sc.nextLine();
        System.out.println("Nuevo año:");
        String anyoN = sc.nextLine();
        System.out.println("Nueva descripcion:");
        String descripcionN = sc.nextLine();

        String sentenciaSQL = "UPDATE videojuegos SET titulo = "
                + nombreN + ", genero = " + generoN + ", anyo = "
                + anyoN + ", plataforma =" + plataformaN + ","
                + "descripcion =" + descripcionN
                + " where LOWER(titulo) like '%" + nombre
                + "%' AND LOWER(plataforma) like '%" + plataforma + "%';";
        int cantidad = statement.executeUpdate(sentenciaSQL);

        System.out.println("Datos insertados: " + cantidad);
    }
}
