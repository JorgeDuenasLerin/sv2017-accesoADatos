//Mario Belso Ros
//Binario

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ConvertirPGM {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String nombreFichero, linea;
        String[] dimensiones = new String[2];
        byte[] datos;
        int total;
        if (args.length > 0) {
            nombreFichero = args[0];
        } else {
            System.out.println("Inserte el nombre del fichero: ");
            nombreFichero = sc.nextLine();
        }
        File nomFichero = new File(nombreFichero);
        try {
            
            // -------- Lectura -------- 
            
            BufferedReader fichEntrada = new BufferedReader(
                    new FileReader(nomFichero));
            byte[] bytes = new byte[(int) nomFichero.length()];
            if (fichEntrada.readLine().equals("P2")) {
                System.out.println("Es P2");
                linea = fichEntrada.readLine();
                if (linea.startsWith("#")) {
                    linea = fichEntrada.readLine();
                    System.out.println("Tiene comentario");
                }
                dimensiones = linea.split(" ");
                total = Integer.parseInt(dimensiones[0])
                        * Integer.parseInt(dimensiones[1]);
                System.out.println(dimensiones[0] + "x" +
                    dimensiones[1]);
                fichEntrada.readLine(); //Nos saltamos el rango de colores.
                datos = new byte[total];
                
                String datosLinea = "";
                do {
                    linea = fichEntrada.readLine();
                    if (linea != null) {
                        datosLinea += linea.trim();
                        datosLinea += " ";
                    }
                }
                while (linea != null);
                System.out.println("Datos: "+datosLinea);
                
                String[] detalles = datosLinea.split(" ");
                for (int i = 0; i < total; i++) {
                    datos[i] = (byte) Integer.parseInt(detalles[i]);
                }
                fichEntrada.close();

                // -------- Volcado -------- 
                
                // Cabecera, como texto
                System.out.println("Creando cabecera...");
                PrintWriter ficheroSalida
                        = new PrintWriter(nombreFichero + ".out.pgm");
                ficheroSalida.println("P5");
                //ficheroSalida.println("#Comentario");
                ficheroSalida.println(Integer.parseInt(dimensiones[0]) + " "
                        + Integer.parseInt(dimensiones[1]));
                ficheroSalida.println("255");
                ficheroSalida.close();
                
                // Datos, como fichero binario
                System.out.println("Guardando datos...");
                OutputStream ficheroSalida2 = new FileOutputStream(
                    new File(nombreFichero + ".out.pgm"), true);   
                int contador = 0;
                ficheroSalida2.write(datos,0, total);
                ficheroSalida2.close();
            }
            else {
                System.out.println("No es un PGM válido");
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("El fichero no se ha encontrado.");
        } catch (IOException e) {
            System.out.println("No se ha podido leer: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }

}
