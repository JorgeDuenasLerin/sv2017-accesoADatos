/*
 *Sergio Garcia Balsas
 */
package vercoches;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author sergio
 */
public class VerCoches
{
     public static void menu()
    {
        System.out.println("1. Mostrar coches");
        System.out.println("2. Salir");
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException
    {
         Class.forName("org.postgresql.Driver");

        String url = "jdbc:postgresql://localhost:5432/COCHEBD";
        String usuario = "postgres";
        String password = "1234";
        Connection con = DriverManager.getConnection(url, usuario, password);

         Statement statement = con.createStatement();

          boolean salir = false;

        do
        {
            Scanner sc = new Scanner(System.in);
            menu();
            String opcion = sc.nextLine();

            switch (opcion)
            {
                case "1": //Mostrar
                     String sentenciaSQL = "SELECT * FROM coches ;" ;
                     ResultSet rs = statement.executeQuery(sentenciaSQL);

                      while (rs.next()) {
                           System.out.println(rs.getString(1) + "\t "
                                   + rs.getString(2)
                                   + "\t "  + "\t " + rs.getString(3)  + "\t"
                                   + "\t "+ rs.getString(4)
                                   + "\t " + rs.getString(5)  + "\t"
                                   + "\t " + rs.getString(6)  + "\t"
                                   + "\t " + rs.getString(7)  + "\t"
                           );
                       }
                         rs.close();
                    break;
                case "2":
                    salir = true;
                    break;
            }
        }
        while (!salir);
        con.close();
    }
}

/*
Resultados:

Dodge    Challenger SXT  1739.00     305     null        null        null
BMW      M4 2017         null        431     (255,18)    null        null
BMW      M5 2017b        null        431     (255,18)    null        null
DMC      Delorean        null        130     null        Deportivo   null
Tata     Safari 2002     null        null    null        Todoterreno {Radiocasette,"Elevalunas electrico"}
*/
