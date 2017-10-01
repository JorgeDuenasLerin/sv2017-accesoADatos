//Mario Belso Ros
//Escribir en un fichero inverso a la respectiva lectura.

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class InvertTxt1 {
    
    public static void main(String[] args) {
        String nombreFichero;
        if (args.length > 0) {
            nombreFichero = args[0];
        } else {
            Scanner sc = new Scanner(System.in);
            System.out.println("Introduzca el nombre del fichero: ");
            nombreFichero = sc.nextLine();
        }
        try {
            ArrayList<String> lineas = new ArrayList<>();
            File txtEntrada = new File(nombreFichero);
            
            if (txtEntrada.exists()) {
                
                BufferedReader ficheroEntrada = new BufferedReader(
                    new FileReader(txtEntrada));
                String linea = ficheroEntrada.readLine();
                while (linea != null) {
                if ( ! linea.equals("")) {
                    lineas.add(linea);
                    linea = ficheroEntrada.readLine();
                }
                ficheroEntrada.close();
                    
                PrintWriter ficheroSalida = new PrintWriter(nombreFichero
                    + "invertido.txt");
                    int maximo = lineas.size() - 1;
                    for (int i = maximo; i >= 0; i--) {
                        ficheroSalida.println(lineas.get(i));
                    }
                    ficheroSalida.close();
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Archivo no encontrado.");
        } catch (IOException e) {
            System.out.println("Error en la lectura.");
        }
        
    }
    
}
