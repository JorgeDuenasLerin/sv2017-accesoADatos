/*
 *Carla Liarte Felipe
 * Alex Gascon
 */
package videojuego_bd;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class VideoJuego_bd {
    public static void anyadir() throws ClassNotFoundException, SQLException {
 
        Class.forName("org.postgresql.Driver");
 
        String url = "jdbc:postgresql://localhost:5432/videojuegos";
        String usuario = "postgres";
        String password = "FELICIDAD22";
        Scanner scanner = new Scanner(System.in);
 
        Connection con = DriverManager.getConnection(url, usuario, password);
        Statement statement = con.createStatement();
        
        DatabaseMetaData dbm = con.getMetaData();
        // check if "employee" table is there
        ResultSet tables = dbm.getTables(null, null, "videojuegos", null);
        if ( ! tables.next()){
            String sentenciaSQL = "CREATE TABLE videojuegos ( "
                    + "id SERIAL PRIMARY KEY, "
                    + "titulo VARCHAR(30), "
                    + "genero VARCHAR(30), "
                    + "anyo INT, "
                    + "plataforma VARCHAR(30), "
                    + "resumen VARCHAR(50) "
                    + ");";
            statement.executeUpdate(sentenciaSQL);
        }
 
        System.out.println("Título: ");
        String titulo = scanner.nextLine();
        System.out.println("Género: ");
        String genero = scanner.nextLine();
        System.out.println("Año: ");
        int anyo = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Plataforma: ");
        String plataforma = scanner.nextLine();
        System.out.println("Resumen: ");
        String resumen = scanner.nextLine();
        scanner.nextLine();
        String insertSql = "Insert into videojuegos "
                + "(titulo,genero,anyo,plataforma,resumen) values"
                + "('" + titulo + "','" + genero + "'," + anyo + ",'"
                +plataforma+"','"+resumen+"');";
 
        int cantidad = statement.executeUpdate(insertSql);
        System.out.println("Datos insertados: " + cantidad);
        con.close();
 
    }

    public static void Buscar() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");

        String url = "jdbc:postgresql://localhost:5432/polos";
        String usuario = "postgres";
        String password = "FELICIDAD22";
        Connection con = DriverManager.getConnection(url, usuario, password);
        Statement statement = con.createStatement();

        Scanner teclado = new Scanner(System.in);
        String titulo;
        String genero;
        int anyo;
        String plataforma;
        String resumen;

        System.out.println("Dime el titulo a buscar: ");
        String buscar = teclado.nextLine().toUpperCase();

        String selectSQL = "SELECT TITULO FROM videojuegos "
                + "where UPPER(titulo) like '%' " + buscar;

        ResultSet rs = statement.executeQuery(selectSQL);

        System.out.println(String.format("%-20s", "titulo"));
        System.out.println("-------------------------------------"
                + "-------------------------------");

        while (rs.next()) {
            titulo = rs.getString(2);
            genero = rs.getString(3);
            anyo = rs.getInt(4);
            plataforma = rs.getString(5);
            resumen = rs.getString(6);
            System.out.printf("%-10s", titulo);
            System.out.printf("%-15s", genero);
            System.out.printf("%-5s \n", anyo);
            System.out.printf("%-5s \n", plataforma);
            System.out.printf("%-5s \n", resumen);
        }
        rs.close();
        con.close();
    }
    
    public static void modificar() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");

        String url = "jdbc:postgresql://localhost:5432/polos";
        String usuario = "postgres";
        String password = "FELICIDAD22";
        Connection con = DriverManager.getConnection(url, usuario, password);
        Statement statement = con.createStatement();

        Scanner teclado = new Scanner(System.in);
        String titulo;
        String genero;
        int anyo;
        String plataforma;
        String resumen;

        System.out.println("Dime videojuego modificar: ");
        String modif = teclado.nextLine().toUpperCase();

        System.out.println("Cambiar titulo, Intro para no cambiar: ");
        titulo = teclado.nextLine();
        String selectSQL = "update videojuegos "
                + "where UPPER(titulo) like '%' " + modif;
        
        //if(titulo)

        statement.executeUpdate(selectSQL);
        ResultSet rs = statement.executeQuery(selectSQL);
        

        System.out.println(String.format("%-20s", "titulo"));
        System.out.println("-------------------------------------"
                + "-------------------------------");

        while (rs.next()) {
            titulo = rs.getString(2);
            genero = rs.getString(3);
            anyo = rs.getInt(4);
            plataforma = rs.getString(5);
            resumen = rs.getString(6);
            System.out.printf("%-10s", titulo);
            System.out.printf("%-15s", genero);
            System.out.printf("%-5s \n", anyo);
            System.out.printf("%-5s \n", plataforma);
            System.out.printf("%-5s \n", resumen);
        }
        rs.close();
        con.close();
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Scanner teclado = new Scanner(System.in);

        int opt;

        do {
            System.out.println("1- Añadir videoJuegos:");
            System.out.println("2- Buscar videoJuegos:");
            System.out.println("3- Modificar");
            System.out.println("0- Salir:");
            opt = Integer.parseInt(teclado.nextLine());

            switch (opt) {
                case 1:
                    anyadir();
                    break;

                case 2:
                    Buscar();

                case 3:
                    modificar();
                    System.out.println("Proximamente");
                    break;
            }
        } while (opt != 0);

    }

}
