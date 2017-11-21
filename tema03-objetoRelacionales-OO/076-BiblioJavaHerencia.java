package muestracoches;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;

public class MuestraCoches {

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/BibliotecaV2Herencia";
            String usuario = "postgres";
            String password = "admin";
            Connection con = DriverManager.getConnection(url, usuario, password);
            mostrarMenu();
            String opc = sc.nextLine();
            if (opc.equals("1")) {
                mostrarDocumentos(con);
            } else if (opc.equals("2")) {
                anyadirDocumento(con);
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MuestraCoches.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static void mostrarMenu() {
        System.out.println("1.- Mostrar documemtos.");
        System.out.println("2.- Añadir documentos.");
        System.out.println("Inserte una opcion: ");
    }

    static void mostrarDocumentos(Connection con) {

        Statement statement;
        try {
            statement = con.createStatement();

            Scanner sc = new Scanner(System.in);
            System.out.println("Introduzca un indice para buscar por"
                    + " titulo y/o autores: ");
            String indice = sc.nextLine().toLowerCase();
            String sentenciaSQL = "SELECT * FROM documentos "
                    + "WHERE LOWER(titulo) LIKE '%" + indice + "%' "
                    + "OR LOWER(autor) LIKE '%" + indice + "%' "
                    + "OR '" + indice + "' = ANY(otrosAutores);";
            ResultSet rs = statement.executeQuery(sentenciaSQL);
            while (rs.next()) {
                System.out.println(String.format("%-15s", rs.getString(1)
                        + String.format("%-15s", rs.getString(2))
                        + String.format("%-15s", rs.getString(3)))
                        + String.format("%-15s", rs.getString(4))
                        + String.format("%-15s", rs.getString(5))
                        + String.format("%-15s", rs.getString(6))
                        + String.format("%-15s", rs.getString(7)));
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(MuestraCoches.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    static void anyadirDocumento(Connection con) {
        Statement statement;
        try {
            statement = con.createStatement();
            String[] datos = new String[11];
            Scanner sc = new Scanner(System.in);
            System.out.println("¿Desea añadir un libro o una revista? (L/R)");
            String sentenciaSQL, tipo = sc.nextLine().toLowerCase();
            tipo = (tipo.equals("l")) ? "libros" : tipo.equals("r")
                    ? "revistas" : "";
            if (tipo.equals("revistas")) {
                System.out.println("Inserte a continuacion los siguientes datos respectivamente: ");
                System.out.println("titulo, autor, otros autores, ubicacion,"
                        + "paginas, año de publicacion, numero, mes de publicacion");
                for (int i = 0; i < 8; i++) {
                    datos[i] = sc.nextLine();
                }
                
                sentenciaSQL = "INSERT INTO " + tipo + " (titulo, autor, otrosAutores,"
                        + " ubicacion, paginas, anyoPublicacion, numero, mes)"
                        + "VALUES ("
                        + datos[0] + ", "
                        + datos[1] + ", "
                        + "ARRAY["+datos[2] + "], "
                        + datos[3] + ", "
                        + datos[4] + ", "
                        + datos[5] + ", "
                        + datos[6] + ", "
                        + datos[7] + "); ";
                int cantidad = statement.executeUpdate(sentenciaSQL);
                if (cantidad == 1) {
                    System.out.println("Se ha añadido correctamente la revista.");
                } else {
                    System.out.println("Ha habido problemas al añadir la revista.");
                }
            }
            else if(tipo.equals("libros")){
                System.out.println("Inserte a continuacion los siguientes datos respectivamente: ");
                System.out.println("titulo, autor, otros autores, ubicacion,"
                        + "paginas, año de publicacion, tipo de tapa.");
                for (int i = 0; i < 7; i++) {
                    datos[i] = sc.nextLine();
                }
                
                sentenciaSQL = "INSERT INTO libros (titulo, autor, otrosAutores,"
                        + " ubicacion, paginas, anyoPublicacion, tapa)"
                        + "VALUES ("
                        + datos[0] + ", "
                        + datos[1] + ", "
                        + "ARRAY["+datos[2] + "], " // fuerza al usuario a poner parentesis.
                        + datos[3] + ", "
                        + datos[4] + ", "
                        + datos[5] + ", "
                        + datos[6] + "); ";
                int cantidad = statement.executeUpdate(sentenciaSQL);
                if (cantidad == 1) {
                    System.out.println("Se ha añadido correctamente la revista.");
                } else {
                    System.out.println("Ha habido problemas al añadir la revista.");
                }
            }

        } catch (SQLException ex) {
            System.out.println("Error with sql "+ ex);
        }
    }
}
