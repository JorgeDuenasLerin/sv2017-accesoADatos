/*
Antonio Defez

Crea un programa que pida al usuario el nombre de un fichero (por ejemplo,
"1.java") y un tamaño en bytes (por ejemplo, 2000) y descomponga ese fichero
en fragmentos de ese tamaño (salvo el último, que probablemente será inferior)
cuyo nombre acabe en .001, .002 y así sucesivamente. Si existen parámetros en
línea de comandos, tomará de ella el nombre y el tamaño.
*/

// Nota: esta versión emplea "try with resources"

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;


public class PartirEnTrozos2 {

    static String nombre_archivo;
    static File  archivo;
    static int frag_tam;
    static File[] fragmentos;
    
    public static void cargarArgumentos(String[]args)
    {
        Scanner entrada = new Scanner(System.in);
         if(args.length!=0)
         {
             archivo = new File(args[0]);
             nombre_archivo =args[0];
             frag_tam = Integer.parseInt(args[1]);
         }
         else
         {
             System.out.println("Introduce el nombre del archivo de entrada");
             nombre_archivo =entrada.nextLine();
             archivo=new File(nombre_archivo);
             System.out.println("Introduce el tamaño (bytes) del archivo de salida");
             frag_tam=entrada.nextInt();
             
         }
    }
    
    public static void main(String[] args) 
    {
       cargarArgumentos(args);
       
                
        try (InputStream archivoEntrada = new FileInputStream(archivo)) {
            byte[] buffer = new byte[frag_tam];
            int cantidad_leida = archivoEntrada.read(buffer,0,buffer.length);
            int contador=1;
            while( cantidad_leida > 0 )
            {
                try (OutputStream archivoSalida =
                        new FileOutputStream(
                                "Part_"
                                +String.format("%03d", contador)+"_"
                                +nombre_archivo)) {
                    archivoSalida.write(buffer, 0, cantidad_leida);
                    contador++;
                }
                cantidad_leida = archivoEntrada.read(buffer,0,buffer.length);
            }
        }catch(IOException e) 
        {
            System.out.println("Error de entrada y salida");
        }catch (Exception e)
        {
            System.out.println("Error general");
        }
        
    }
    
}
