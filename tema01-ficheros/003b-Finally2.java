// Para cada frase que se introduzca poner fecha y hora. Se debe usar finally
// Daniel Ossa

// package pkg003_login;

import java.io.*;
import java.util.Scanner;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Finally2 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        PrintWriter fichero = null;
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        try {
            String texto;
            fichero = new PrintWriter("frases.txt");
            
            do {
                System.out.print("Nueva Entrada: ");
                texto = scan.nextLine();
                
                if ( ! texto.equals("") )
                {
                    Date fecha = new Date();
                    texto = formatoFecha.format(fecha) + " -> " + texto;
                    fichero.println(texto);
                }
            }
            while ( ! texto.equals("") );
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        finally {
            if ( fichero != null ) {
                fichero.close();
            }
        }
    }
    
}
