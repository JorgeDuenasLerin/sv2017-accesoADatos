// Base de datos Polos: mostrar datos

// package bbddpolos;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class BBDDPolos{

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/polos";
        String usuario = "postgres";
        String password = "n";
        Connection con = DriverManager.getConnection(url, usuario, password);

        Statement statement = con.createStatement();

        String sentenciaSQL = "SELECT id, rpad(marca, 20), rpad(sabor, 20), "
                + "precio FROM polos";
        ResultSet rs = statement.executeQuery(sentenciaSQL);

        System.out.println("Id Marca                Sabor               Precio");
        System.out.println("--------------------------------------------------");

        while (rs.next()) {
            System.out.println(rs.getString(1) 
                    + "  " + rs.getString(2)
                    + " " + rs.getString(3) 
                    + " " + rs.getString(4));
        }
        rs.close();
        con.close();
    }
}
