package biblioteca1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Biblioteca1 {

    public static void main(String[] args) {
        String opcion;
        Scanner entrada = new Scanner(System.in);
        String nomLibro;

        try {
            Class.forName("org.postgresql.Driver");

            String url = "jdbc:postgresql://localhost:5432/biblioteca";
            String usuario = "postgres";
            String password = "1234";

            Connection con = DriverManager.getConnection(url, usuario, password);

            do {
                System.out.println("Menú: ");
                System.out.println("1. Añadir usuario.");
                System.out.println("2. Añadir libro.");
                System.out.println("3. Añadir prestamo.");
                System.out.println("4. Devolver prestamo.");
                System.out.println("5. Buscar libros");
                System.out.println("6. Lista de morosos");
                System.out.println("S. Salir");
                opcion = entrada.nextLine();
                switch (opcion) {
                    case "1":
                        // Más adelante
                        System.out.println("No disponible aún");
                        break;
                    case "2":
                        System.out.println("Introduce codigo de libro:");
                        String codLibro = entrada.nextLine();
                        System.out.println("Introduce autor:");
                        String autor = entrada.nextLine();
                        System.out.println("Introduce título:");
                        String titulo = entrada.nextLine();
                        anadirLibro(con, codLibro, autor, titulo);
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
                        System.out.println("Introduce nombre del libro a buscar (o parte)");
                        nomLibro = entrada.nextLine().toLowerCase();
                        mostrarLibros(con, nomLibro);
                        break;
                    case "6":
                        mostrarMorosos(con);
                        break;
                    case "S":
                    case "s":
                        System.out.println("Adios!");
                        con.close();
                        break;
                }

            } while (!opcion.toUpperCase().equals("S"));
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void mostrarMorosos(Connection con) {
        try {
            Statement statement = con.createStatement();
            String mostrarDatos = "SELECT nombre, titulo, autor, fechaDevolPrevista " +
                "FROM usuarios, libros, prestar " +
                "WHERE usuarios.cod = codUsuario " +
                "    AND libros.cod = codLibro " +
                "    AND fechaDevolPrevista < CURRENT_DATE" +
                "    AND fechaDevolReal IS NULL;";
            ResultSet rs = statement.executeQuery(mostrarDatos);
            System.out.println("Usuario        Título         Autor          "
                    +"Devoluc.Prev");
            System.out.println("---------------------------------------------------");
            while (rs.next()) {
                System.out.println(String.format("%-15s", rs.getString("nombre"))
                    + String.format("%-15s", rs.getString("titulo"))
                    + String.format("%-15s", rs.getString("autor"))
                    + String.format("%-15s", rs.getString("fechaDevolPrevista")));
            }
            statement.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error. " + e.getMessage());
        }
    }

    public static void mostrarLibros(Connection con, String fragmentoNombre) {
        try {
            Statement statement = con.createStatement();
            String orden = "SELECT RPAD(codLibro, 10), RPAD(autor, 30), RPAD(titulo,30), fechaPrest, fechaDevolReal "
                    + "FROM libros, prestar"
                    + " WHERE (LOWER(titulo) LIKE '%" + fragmentoNombre + "%' "
                    + " OR LOWER(autor) LIKE '%" + fragmentoNombre + "%') AND codLibro=libros.cod";
                    
            ResultSet rs = statement.executeQuery(orden);
            System.out.println("Cod.       Autor                          Título"
                    +"                        Prestado   Devuelto");
            System.out.println("-----------------------------------------------"
                +"-----------------------------------------------");
            while (rs.next()) {
                String devuelto = rs.getString(5);
                if (devuelto == null)
                    devuelto = "-No dev.-";
                System.out.println(rs.getString(1)+ " "
                    + rs.getString(2) + " "
                    + rs.getString(3) + " "
                    + rs.getString(4) + " "
                    + devuelto);
                // .replace("null","-no-")
            }
            statement.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("No existe el libro " + fragmentoNombre + ".");
        }
    }

    public static void anadirLibro(Connection con, 
            String cod, String autor, String titulo) {
        try {
            Statement statement = con.createStatement();
            String anhadirLibro = "INSERT INTO libros (cod, autor, titulo) "
                    +" VALUES ('" + cod
                    + "','" + autor
                    + "','" + titulo 
                    + "');";
            statement.executeUpdate(anhadirLibro);
            System.out.println("Libro guardado satisfactoriamente.");
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
}
