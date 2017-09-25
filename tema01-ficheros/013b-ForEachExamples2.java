/*
 * Para practicar el uso de foreach, crea un programa vuelque tres cadenas 
de texto de un ArrayList a un fichero de texto, de tres formas distintas:
primero usando un "for" convencional, 
luego un "foreach" (for : ) y finalmente un ".forEach".

Sergio Garcia Balsas
 */

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ForEachExamples2 
{
    public static void main(String[] args) 
    {
        ArrayList<String> lista = new ArrayList<>();
        lista.add("Sergio");
        lista.add("Fernando");
        lista.add("Juan");
        
        try 
        {
            //Constructor fichero
            PrintWriter ficheroSalida = new PrintWriter ("fichero.txt");
            
            //Volcado con for convencional: 
            for (int i = 0; i < lista.size(); i++)
                ficheroSalida.println(lista.get(i));
            
            //Volcado con for extendido
            for (String nombre: lista)
                ficheroSalida.println(nombre);
            
            //Volcado con un .foreach
            lista.forEach( i -> ficheroSalida.println(i);); 
            
            ficheroSalida.close();
        } 
        catch (FileNotFoundException ex) 
        {
            System.out.println("Fichero no encontrado");
        }
        
    }
    
}
