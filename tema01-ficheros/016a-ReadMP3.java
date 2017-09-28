/*
* Leer fichero MP3 ID3 v1
* 
* Sergio Garcia
* Carla Liarte
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class ReadMP3 {
    
    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        System.out.print("Introduzca el nombre del archivo de entrada: ");
        String fichero = scan.nextLine();
        
        try {
            File ficheroEntrada = new File(fichero);
            InputStream entrada = new FileInputStream(ficheroEntrada);
            int tamanyo = (int)ficheroEntrada.length();
            byte[] datos = new byte[128];
            entrada.skip(tamanyo - 128);
            int cantidadDatos = entrada.read(datos, 0, 128);
            entrada.close();
            
            if (cantidadDatos != 128) {
                System.out.print("Fichero MP3 no legible");
                return;
            }
            if ((char)datos[0] == 'I' && (char)datos[1] == 'D' && 
                    (char)datos[2] == '3') {
                
                System.out.println("Tiene cabecera ID3 V1");
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado.");
        }
        catch (IOException e) {
            System.out.println("Error en la lectura del archivo.");
        }
        catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
        
    }
}
