//Conversor de un fichero csv a uno xml
//Alejandro Gascón Martí
package conversorcsvaxml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ConversorCSVaXML {

    public static void main(String[] args) {

        String nombreEntrada, nombreSalida;
        
        nombreEntrada="datos.csv";
        nombreSalida="datos.xml";
        
        if ( ! (new File(nombreEntrada)).exists()) {
            System.out.println("No se ha encontrado el archivo");
        } else {
                try {
                
                BufferedReader entrada = new BufferedReader(
                        new FileReader(new File(nombreEntrada)));
                PrintWriter salida = new PrintWriter(nombreSalida);
                
                salida.println("<datos>");
                String linea = entrada.readLine();
                while(linea!=null){
                    String[] datos= linea.split(",");
                    salida.println("    <dato>");
                    salida.println("        <mes>"+datos[0]+"</mes>");
                    salida.println("        <ventas>"+datos[1]+"</ventas>");
                    salida.println("        <acumulado>"+datos[2]+
                                                            "</acumulado>");
                    salida.println("    </dato>");  
                    linea = entrada.readLine();
                }
                salida.println("</datos>");
                
                entrada.close();
                salida.close();
                
            } catch (FileNotFoundException ex) {
                System.err.println("Fichero no encontrado");
            } catch (IOException ex) {
                System.err.println("Error de entrada/salida");          
            }          
        }
    }
}