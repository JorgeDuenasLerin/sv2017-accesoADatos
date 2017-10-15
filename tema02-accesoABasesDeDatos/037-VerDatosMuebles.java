// Fernando Carbonell

import java.io.File;
import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VerDatosMuebles
{

    public static void main(String[] args) throws SQLException
    {

        String url = "jdbc:postgresql://localhost:5432/ikea";
        String usuario = "postgres";
        String password = "1234";
        Connection con = DriverManager.getConnection(url, usuario, password);

        Statement statement = con.createStatement();

        String sentenciaSQL = "SELECT * FROM muebles";
        ResultSet rs = statement.executeQuery(sentenciaSQL);

        System.out.println("Id" + "\t" + "Nombre"+ "\t" +"Precio"+ "\t" +"Material"+ "\t" +"Unidades");
        System.out.println("-----------------------------------------");

        while (rs.next()) {
            System.out.println(rs.getString(1) 
                    + "\t " + rs.getString(2)
                    + "\t "+rs.getString(3) 
                    + "\t "+rs.getString(4) 
                    + "\t "+rs.getString(5));
        }
        rs.close();
        con.close();
    }
}
