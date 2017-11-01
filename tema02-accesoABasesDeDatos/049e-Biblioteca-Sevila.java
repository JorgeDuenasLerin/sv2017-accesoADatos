//Samuel García, Abilio Reig, Antonio Sevila
//Control de libros, de personas, prestar (con fecha inicio, fin, fecha devolucion)
//Añadir usuarios, añadir libro, indicar prestamo, devolver prestamo.
//Buscar libros: preguntar si el libro está prestado o no.
//Lista morosos.
package Ejercicio_20173110;

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
public class Biblioteca {

    public static void crearTablas(Connection con) {
        try {
            Statement statement = con.createStatement();
            String createTable = "CREATE TABLE IF NOT EXISTS libros("
                    + "isbn char(3) PRIMARY KEY,"
                    + "nombre varchar(15));";
            statement.executeUpdate(createTable);
            createTable = "CREATE TABLE IF NOT EXISTS personas("
                    + "dni char(9) PRIMARY KEY,"
                    + "nombre varchar(15));";
            statement.executeUpdate(createTable);
            createTable = "CREATE TABLE IF NOT EXISTS prestar("
                    + "dni_persona char(9),"
                    + "isbn_libro char(3),"
                    + "fecha_inicio date,"
                    + "fecha_fin date,"
                    + "fecha_devolucion date,"
                    + "PRIMARY KEY (dni_persona, isbn_libro, fecha_inicio),"
                    + "FOREIGN KEY (dni_persona) REFERENCES personas(dni),"
                    + "FOREIGN KEY (isbn_libro) REFERENCES libros(isbn));";
            statement.executeUpdate(createTable);
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void mostrarMorosos(Connection con) {
        try {
            Statement statement = con.createStatement();
            String mostrarDatos = "SELECT p.nombre , l.nombre"
                    + " FROM personas AS p, prestar, libros l"
                    + " WHERE p.dni=dni_persona AND l.isbn = isbn_libro AND"
                    + " fecha_devolucion IS NULL;";
            ResultSet rs = statement.executeQuery(mostrarDatos);
            System.out.println(String.format("%-15s", "Nombre persona")
                    + String.format("%-15s", "Nombre libro"));
            System.out.println("-----------------------------------------------");
            while (rs.next()) {
                System.out.println(String.format("%-15s", rs.getString(1))
                        + String.format("%-15s", rs.getString(2)));
            }
            statement.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error. " + e.getMessage());
        }
    }

    public static void mostrarLibro(Connection con, String nombreLibro) {
        try {
            Statement statement = con.createStatement();
            String mostrarDatos = "SELECT isbn, nombre, fecha_devolucion "
                    + "FROM libros, prestar"
                    + " WHERE LOWER(nombre) LIKE '%" + nombreLibro + "%' AND isbn=isbn_libro";
            ResultSet rs = statement.executeQuery(mostrarDatos);
            System.out.println(String.format("%-5s", "ISBN")
                    + String.format("%-15s", "Nombre libro")
                    + String.format("%-15s", "¿Está disponible?"));
            System.out.println("-----------------------------------------------");
            while (rs.next()) {
                System.out.print(String.format("%-5s", rs.getString(1))
                        + String.format("%-15s", rs.getString(2)));
                if (rs.getString(3) == null || rs.getString(3).isEmpty()) {
                    System.out.print(String.format("%-15s", "No"));
                } else {
                    System.out.print(String.format("%-15s", "Sí"));
                }
                System.out.println("");
            }

            statement.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("No existe el libro " + nombreLibro + ".");
        }
    }

    public static void anhadirLibro(Connection con, String cod, String nombre) {
        try {
            Statement statement = con.createStatement();
            String anhadirLibro = "INSERT INTO libros VALUES ('" + cod
                    + "','" + nombre + "');";
            statement.executeUpdate(anhadirLibro);
            System.out.println("Programador guardado satisfactoriamente.");
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error: No se ha podido guardar el libro.");
        }
    }

    public static void mostrarDNIS(Connection con) {
        try {
            Statement statement = con.createStatement();
            String sql = "SELECT dni, nombre FROM personas;";
            ResultSet rs = statement.executeQuery(sql);
            System.out.println(String.format("%-10s", "DNI")
                    + String.format("%-15s", "NOMBRE"));
            System.out.println("-----------------------------------");

            while (rs.next()) {
                System.out.println(String.format("%-10s", rs.getString(1))
                        + String.format("%-15s", rs.getString(2)));
            }
            statement.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void indicarPrestamo(Connection con, String fechaInicio,
            String fechaFin) {
        Scanner sc = new Scanner(System.in);
        try {
            // MOSTRAR LIBROS
            Statement statementLibros = con.createStatement();
            String mostrarLibros = "SELECT dni, nombre FROM libros";
            ResultSet rsLibros = statementLibros.executeQuery(mostrarLibros);
            System.out.println(String.format("%-20s", "DNI")
                    + String.format("%-20s", "Nombre"));
            System.out.println("--------------------------------------------"
                    + "-----------------------------------------------------"
                    + "--------");
            while (rsLibros.next()) {
                System.out.println(String.format("%-20s", rsLibros.getString(1))
                        + String.format("%-20s", rsLibros.getString(2)));
            }
            statementLibros.close();
            rsLibros.close();

            System.out.println("Dime el libro que quieres: ");
            String codLibro = sc.nextLine();

            // MOSTRAR PERSONAS
            Statement statementPersonas = con.createStatement();
            String mostrarPersonas = "SELECT dni, nombre FROM libros";
            ResultSet rsPersonas = statementPersonas.executeQuery(mostrarPersonas);
            System.out.println(String.format("%-20s", "DNI")
                    + String.format("%-20s", "Nombre"));
            System.out.println("--------------------------------------------"
                    + "-----------------------------------------------------"
                    + "--------");
            while (rsPersonas.next()) {
                System.out.println(String.format("%-20s", rsPersonas.getString(1))
                        + String.format("%-20s", rsPersonas.getString(2)));
            }
            statementPersonas.close();
            rsPersonas.close();

            System.out.println("Dime tu DNI: ");
            String dni = sc.nextLine();

            // GUARDAMOS EL PRESTAMO
            Statement statementPrestamos = con.createStatement();
            String anhadirPrestamo = "INSERT INTO prestar (dni_persona,"
                    + " isbn_libro, fecha_inicio, fecha_fin) VALUES ('" + dni
                    + "','" + codLibro + "','" + fechaInicio + "','"
                    + fechaFin + "');";
            statementPrestamos.executeUpdate(anhadirPrestamo);
            System.out.println("Prestamo guardado satisfactoriamente.");
            statementPrestamos.close();
        } catch (SQLException e) {
            System.out.println("Error: No se ha podido guardar el prestamo.");
        }
    }

    public static void mostrarCogidos(Connection con, String dni) {
        try {
            Statement statement = con.createStatement();
            String sql = "SELECT l.isbn, l.nombre, p.nombre, p.dni, fecha_inicio"
                    + " from libros l, personas p, prestar"
                    + " where p.dni = prestar.dni_persona"
                    + " and l.isbn = prestar.isbn_libro";
            ResultSet rs = statement.executeQuery(sql);
            System.out.println(String.format("%-10s", "Nombre Persona")
                    + String.format("%-15s", "Nombre Libro"));
            System.out.println("-----------------------------------");

            while (rs.next()) {
                System.out.println(String.format("%-10s", rs.getString(1))
                        + String.format("%-15s", rs.getString(2)));
            }
            statement.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void devolverPrestamos(Connection con, String dniPersona,
            String isbnLibro) {
        //persona, libro, fecha actual

        try {
            //update tabla prestamo, eliminar ese dni y ese 
            Statement statement = con.createStatement();
            String update = "DELETE FROM prestar WHERE dni_persona = "
                    + dniPersona + " AND isbn_libro = "
                    + isbnLibro + ";";

            statement.executeUpdate(update);
            statement.close();
            System.out.println("Préstamo devuelto");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        String opcion;
        Scanner entrada = new Scanner(System.in);
        String nomLibro;

        try {
            Class.forName("org.postgresql.Driver");

            String url = "jdbc:postgresql://localhost:5432/biblioteca";
            String usuario = "postgres";
            String password = "postgre2017";

            Connection con = DriverManager.getConnection(url, usuario, password);

            crearTablas(con);
            do {
                System.out.println("Menú: ");
                System.out.println("1. Añadir persona.");
                System.out.println("2. Añadir libro.");
                System.out.println("3. Añadir prestamo.");
                System.out.println("4. Devolver prestamo.");
                System.out.println("5. Buscar libros");
                System.out.println("6. Lista de morosos");
                System.out.println("S. Salir");
                opcion = entrada.nextLine();
                switch (opcion) {
                    case "1":
                        //No se hace
                        break;
                    case "2":
                        System.out.println("Introduce codigo de libro:");
                        String isbn = entrada.nextLine();
                        System.out.println("Introduce nombre de libro:");
                        nomLibro = entrada.nextLine();
                        anhadirLibro(con, isbn, nomLibro);
                        break;
                    case "3":
                        System.out.println("Indica la fecha de inicio (aaaa/mm/dd):");
                        String fechaInicio = entrada.nextLine();
                        System.out.println("Indica la fecha de fin (aaaa/mm/dd):");
                        String fechaFin = entrada.nextLine();
                        indicarPrestamo(con, fechaInicio, fechaFin);
                        break;
                    case "4":
                        mostrarDNIS(con);
                        System.out.println("Introduce DNI: ");
                        String dni = entrada.nextLine();
                        mostrarCogidos(con, dni);
                        System.out.println("Introduce ISBN: ");
                        String isbn2 = entrada.nextLine();
                        devolverPrestamos(con,dni,isbn2);
                        break;
                    case "5":
                        System.out.println("Introduce nombre del libro");
                        nomLibro = entrada.nextLine().toLowerCase();
                        mostrarLibro(con, nomLibro);
                        break;
                    case "6":
                        mostrarMorosos(con);
                        break;
                    case "S":
                        System.out.println("Adios!");
                        con.close();
                        break;
                }

            } while (!opcion.equals("S"));
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
