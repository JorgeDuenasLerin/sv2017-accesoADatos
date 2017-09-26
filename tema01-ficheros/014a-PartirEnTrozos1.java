//Mario Belso Ros

/* Crea un programa que pida al usuario el nombre de un fichero (por 
ejemplo, "1.java") y un tamaño en bytes (por ejemplo, 2000) y 
descomponga ese fichero en fragmentos de ese tamaño (salvo el último, 
que probablemente será inferior) cuyo nombre acabe en .001, .002 y así 
sucesivamente. Si existen parámetros en línea de comandos, tomará de 
ella el nombre y el tamaño. */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;

public class PartirTrozos {

    public static void main(String[] args) {
        byte[] buffer = null;
        String nombreFichero;
        int tamanyoBytes;
        int numFicheros;
        
        Scanner sc = new Scanner(System.in);
        if (args.length > 1) {
            nombreFichero = args[0];
            tamanyoBytes = Integer.parseInt(args[1]);
        } else {
            System.out.println("Inserte el nombre de fichero a partir: ");
            nombreFichero = sc.nextLine();
            System.out.println("Inserte la cantidad de bytes por archivo: ");
            tamanyoBytes = sc.nextInt(); 
        }
        try {
            File ficheroBMP = new File(nombreFichero);
            InputStream ficheroEntradaBin
                    = new FileInputStream(ficheroBMP);
            int cantidadALeer = ((int) ficheroBMP.length());
            if(cantidadALeer % tamanyoBytes != 0)
            {
                numFicheros = (cantidadALeer / tamanyoBytes) +1;
            }
            else
            {
                numFicheros = cantidadALeer / tamanyoBytes;
            }
            if (ficheroBMP.exists()) {
                buffer = new byte[cantidadALeer];
                int cantidadLeida;
                while ((cantidadLeida = ficheroEntradaBin.read(buffer, 0, 
                        cantidadALeer)) > 0) {
                }
            }
            ficheroEntradaBin.close();
            OutputStream ficheroSalida = null;
            DecimalFormat formatter = new DecimalFormat("000");
            for(int contadorFicheros = 0; contadorFicheros<numFicheros;
                    contadorFicheros++)
            {
                for(int tamanyoMax = 0; tamanyoMax<tamanyoBytes; tamanyoMax++)
                {
                    nombreFichero += formatter.format(String.valueOf(contadorFicheros)) +".java";
                    ficheroSalida = new FileOutputStream(
                            new File(nombreFichero));
                    ficheroSalida.write(buffer[tamanyoMax]);

                }
                ficheroSalida.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
