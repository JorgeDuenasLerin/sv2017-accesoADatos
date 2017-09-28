/*comparo si 2 ficheros son iguales*/
//Alexandra Sanchez

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class CompararFicheros {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.print("Dime fichero 1: ");
        String fich1 = teclado.nextLine();

        System.out.print("Dime fichero 2: ");
        String fich2 = teclado.nextLine();

        File fichero1 = new File(fich1);
        File fichero2 = new File(fich2);
        
        CompararFicheros(fichero1,fichero2);
    }
    
    public static byte[] LeerFicheroBin(File fichero) {
        byte[] buf = null;
        try {
            InputStream fichero1 = new FileInputStream(fichero);

            final int SIZE = (int) fichero.length();
            buf = new byte[SIZE];

            int cantidadLeida = fichero1.read(buf, 0, SIZE);
            fichero1.close();

        } 
        catch (Exception e) {
            System.out.println("Error, " + e.getMessage());
        } 
        finally {
            return buf;
        }

    }
    
    public static void CompararFicheros(File fichero1, File fichero2){
        if (fichero1.length() == fichero2.length()) {

            byte[] buf1 = LeerFicheroBin(fichero1);
            byte[] buf2 = LeerFicheroBin(fichero2);

            int cont = 0;
            boolean iguales = true;

            while (cont < fichero1.length() 
                    && buf1[cont] == buf2[cont]) {
                if (buf1[cont] != buf2[cont]) {
                    iguales = false;
                }
                cont++;
            }
            if (cont == fichero1.length()) {
                System.out.println("Son iguales");
            }
        } 
        else {
            System.out.println("No son iguales");
        }
    }
}
