/*

Crea un programa que invierta el contenido de un fichero (de cualquier 
tipo) y vuelque el resultado (invertido) a otro fichero. Ambos nombres 
de ficheros se pedirán al usuario. Se debe usar bloques en la lectura y 
en la escritura, para que el programa resultante sea rápido.

*/

import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class InvertirFichero {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Introduzca el nombre del archivo de entrada: ");
        String ficheroEntrada = scan.nextLine();
        System.out.print("Introduzca el nombre del archivo de salida: ");
        String ficheroSalida = scan.nextLine();
        
        InputStream input = null;
        OutputStream output = null;
        try {
            File fichero = new File(ficheroEntrada);
            int tamanyo = (int)fichero.length();
            byte[] datos = new byte[tamanyo];
            input = new FileInputStream(fichero);
            int cantidadLeida = input.read(datos, 0, tamanyo);
            input.close();
            
            if (cantidadLeida == tamanyo) {
                byte[] datosInvertidos = new byte[cantidadLeida];
                int posicion = 0;
                for (int i = cantidadLeida - 1; i >= 0; i--) {
                    datosInvertidos[posicion] = datos[i];
                    posicion++;
                }
                output = new FileOutputStream(new File(ficheroSalida));
                output.write(datosInvertidos, 0, cantidadLeida);
                output.close();
            }
            else {
                System.out.println("No se ha podido leer todo el archivo");
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado");
        }
        catch (IOException e) {
            System.out.println("Error a la hora de leer o guardar el fichero " +
                e.getMessage());
        }
        catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }
}
