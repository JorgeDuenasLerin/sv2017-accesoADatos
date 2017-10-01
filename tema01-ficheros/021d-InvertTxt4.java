//Escribir al reves en un fichero de texto
//Javier Montejo 29/09/2017

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class InvertTxt4 {

    public static void main(String[] args) throws IOException {
        
        Scanner escanear = new Scanner(System.in);
        System.out.print("introduzca el nombre del archivo: ");
        String nombre = escanear.nextLine();
        
        File archivo = new File(nombre);
        if (! archivo.exists()) {
            System.out.println("No existe el archivo.");
            return;
        }
        
        BufferedReader leer = null;
        BufferedWriter imprimir = null;
        
        try {
            leer = new BufferedReader(new FileReader(archivo));
            
            Stack<String> lineas = new Stack<>();
            String linea;
            do {
                linea = leer.readLine();
                if (linea != null) {
                    lineas.add(linea);
                }
            }
            while (linea != null);
            
            imprimir = new BufferedWriter(new FileWriter(
                new File(nombre + ".inv" + ".txt")));
            while (lineas.size() > 0) {
                linea = lineas.pop();
                imprimir.write(linea);
                imprimir.newLine();
            }
        }
        catch (IOException e) {
            System.out.println("No se ha podido leer o crear el archivo.");
        }
        finally {
            if (leer != null) {
                leer.close();
            }
            if (imprimir != null) {
                imprimir.close();
            }
        }
        
    }
    
}
