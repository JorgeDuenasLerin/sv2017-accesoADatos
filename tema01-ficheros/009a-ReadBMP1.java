/*
 * Leer fichero de BMP 
 * Carla Liarte Felipe
 */
package readbmp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ReadBMP {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        
        System.out.println("Nombre del archivo: ");
        String nombre = teclado.nextLine();
        
        FileInputStream fichEntrada = null;
        
        try {
            fichEntrada = new FileInputStream(new File(nombre));
            
            int dato0 = fichEntrada.read();
            int dato1= fichEntrada.read();
            
            if(esBMP(dato0, dato1)) {
                System.out.println("Es un fichero BMP");
            }
            else 
                System.out.println("No es un fichero BMP");
            
        }
        
        catch (FileNotFoundException e) {
            System.out.println("El fichero no se ha encontrado.");
        }
        catch (IOException e) {
            System.out.println("No se ha podido leer: " + e.getMessage());
        }
        catch(Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }
    
    public static boolean esBMP (int dato0, int dato1) {
        return ((dato0=='B') && (dato1== 'M'));
    }
    
}
