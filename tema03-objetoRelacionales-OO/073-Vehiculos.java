//Antonio Sevila
//Mostrar todos los datos de vehiculos que contengan un texto o en la marca o
// el modelo.
package vehiculos_BDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author antonio
 */
public class vehiculos {
    public static void mostrarVehiculosPorTexto(Connection con, String texto) {
        try {
            Statement statement = con.createStatement();
            String sql = "SELECT * FROM vehiculos WHERE LOWER(marca) LIKE"
                    + " '%"+texto+"%' OR LOWER(modelo) LIKE '%"+texto+"%';";
            ResultSet rs = statement.executeQuery(sql);
            System.out.println(String.format("%-20s", "id")
                    + String.format("%-20s", "Marca")
                    + String.format("%-20s", "Modelo"));
            System.out.println("--------------------------------------------"
                    + "-----------------------------------------------------"
                    + "--------");
            while (rs.next()) {
                System.out.println(String.format("%-20s", rs.getString(1))
                        + String.format("%-20s", rs.getString(2))
                        + String.format("%-20s", rs.getString(3)));
            }
            statement.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
        String texto;
        Scanner entrada = new Scanner(System.in);
        
        System.out.println("Introduce el texto por el que buscar");
        texto = entrada.nextLine().toLowerCase();
        
        try {
            Class.forName("org.postgresql.Driver");
            
            String url = "jdbc:postgresql://localhost:5432/VehiculosOO";
            String usuario = "postgres";
            String password = "postgre2017";

            Connection con = DriverManager.getConnection(url, usuario, password);
            
            mostrarVehiculosPorTexto(con, texto);
            
            con.close();
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: "+ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Error: "+ex.getMessage());
        }

            
    }
}
