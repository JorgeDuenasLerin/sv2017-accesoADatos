// Dump lines to a text file
// Mario Belso

import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FicheroDeTexto1 {

    public static void main(String[] args) {
		
        Scanner entrada = new Scanner(System.in);
        try {
            PrintWriter fichero = new PrintWriter("frases.txt");
            String frase = "";
            do {
				System.out.print("Dime una frase (Intro para salir): ");
				frase = entrada.nextLine();
				if( ! frase.equals("") ) {
					fichero.println(frase);
				}
            }
            while( ! frase.equals("") );
            fichero.close();
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
