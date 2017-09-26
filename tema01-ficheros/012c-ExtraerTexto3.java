// Mario Belso Ros
// Enunciado: Mostrar caracteres alfabeticos de un binario.

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class ExtraerTexto2 {

    public static void main(String[] args) {
        byte[] buffer = null;
        String nombreFichero;
        Scanner sc = new Scanner(System.in);
        if (args.length > 0) {
            nombreFichero = args[0];
        } else {
            System.out.println("Inserte el fichero a comprobar si es comprimido: ");
            nombreFichero = sc.nextLine();
        }
        try {
            File ficheroBMP = new File(nombreFichero);
            InputStream ficheroEntradaBin
                    = new FileInputStream(ficheroBMP);
            int cantidadALeer = ((int) ficheroBMP.length());
            if (ficheroBMP.exists()) {
                buffer = new byte[cantidadALeer];
                int cantidadLeida;
                while ((cantidadLeida = ficheroEntradaBin.read(buffer, 0, cantidadALeer)) > 0) {
                }
            }
            ficheroEntradaBin.close();
            for (byte datoTemp : buffer) {
                if (datoTemp >= 'a' && datoTemp <= 'z'
                        || datoTemp >= 'A' && datoTemp <= 'Z') {
                    System.out.print(((char)datoTemp)+ " ");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
