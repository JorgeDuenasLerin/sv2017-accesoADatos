// Base de datos Polos: mostrar y añadir datos
// Miguel Moya Ortega

// package bbddpolos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class BBDDPolos{

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String url = "jdbc:postgresql://localhost:5432/polos";
        String usuario = "postgres";
        String password = "n";
        Connection con = DriverManager.getConnection(url, usuario, password);
        boolean finish = false;
        Scanner scanner = new Scanner(System.in);
        byte opcion;

        while (!finish) {
            System.out.println("¿Qué operación desea realizar?");
            System.out.println("    1) Añadir polo.");
            System.out.println("    2) Ver polos.");
            System.out.println("    0) Salir.");
            opcion = scanner.nextByte();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    System.out.println("Introduce el id numérico:");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // (para vaciar el buffer del teclado)
                    System.out.println("Introduce la marca:");
                    String marca = scanner.nextLine();
                    System.out.println("Introduce el sabor:");
                    String sabor = scanner.nextLine();
                    System.out.println("Introduce el precio:");
                    double price = scanner.nextDouble();
                    scanner.nextLine(); // (para vaciar el buffer del teclado)
                    addPolo(con, id, marca, sabor, price);
                    break;
                case 2:
                    try {
                        showPolos(con);
                    } catch (ClassNotFoundException ex) {
                        System.out.println("No se ha encontrado la clase.");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        System.out.println("Error en la sql.");
                    }
                    break;
                case 0:
                    finish = !finish;
                    break;
                default:
                    System.out.println("Opción no reconocida.");
            }
        }
        con.close();
    }


    public static void showPolos(Connection con) throws ClassNotFoundException, SQLException {

        Statement statement = con.createStatement();

        String sentenciaSQL = "SELECT * FROM polos";
        ResultSet rs = statement.executeQuery(sentenciaSQL);

        System.out.println(String.format("%-20s", "id")
                + String.format("%-20s", "marca")
                + String.format("%-20s", "sabor")
                + String.format("%-20s", "precio"));
        System.out.println("-------------------------------------"
                + "-------------------------------");
        while (rs.next()) {
            System.out.println(String.format("%-20s", rs.getString(1))
                    + String.format("%-20s", rs.getString(2))
                    + String.format("%-20s", rs.getString(3))
                    + String.format("%-20s", rs.getString(4)));
        }
        rs.close();
        statement.close();
    }


    public static void addPolo(Connection con, int id, String marca, 
            String sabor, double precio)
            throws ClassNotFoundException, SQLException {

        Statement statement = con.createStatement();
        String sql = " INSERT INTO polos (id, marca, sabor, precio) VALUES ("
                + id +", '" + marca + "', '" + sabor + "', '" + precio + "');";
        int cantidad = statement.executeUpdate(sql);
        if (cantidad > 0)
            System.out.println("Guardado correctamente");
        else
            System.out.println("No se ha podido guardar");
        statement.close();
    }
}

