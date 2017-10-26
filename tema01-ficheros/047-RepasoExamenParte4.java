// package parte4;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;

/**
 *
 * @author Miguel Moya Ortega
 */
public class RepasoExamenParte4 {

    public static void main(String[] args) {
        try {
            FileInputStream bin
                    = new FileInputStream(new File("vehiculos.dat"));
            PrintWriter print = new PrintWriter("vehiculos.txt");

            int dato = bin.read();
            while (dato != -1) {
                if ((dato >= 48 && dato <= 57)
                        || (dato >= 65 && dato <= 90)
                        || (dato >= 97 && dato <= 122)
                        || dato == 32) {
                    print.print((char) dato);
                }
                dato = bin.read();
            }
            bin.close();
            print.close();
            System.out.println("Done!");
        } catch (Exception e) {
            System.out.println("Ups! Error...");
        }
    }
}
