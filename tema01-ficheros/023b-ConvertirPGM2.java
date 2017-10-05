// convertir una imagen pgm P2 a un pgm P
// Alexandra Sanchez

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

public class ConvertirPGM {

    public static void main(String[] args) {
        try {
            File file = new File("j.pgm");

            if (file.exists()) {
                OutputStream ficheroOut = new FileOutputStream(
                        new File("2.pgm"));//para escribir
                BufferedReader ficheroIn = new BufferedReader(
                        new FileReader(file));
                String linea = "";
                linea = ficheroIn.readLine();//leo 1º linea

                if (!linea.equals("P2")) {//1º tipo formato
                    System.out.println("fichero no valido");
                } else {
                    linea = "P5\n";
                    ficheroOut.write(linea.getBytes());

                    linea = ficheroIn.readLine();//leo 2º linea
                    if (!linea.startsWith("#")) {//2º linea comentario
                        System.out.println("no hay comentario");
                    } else {
                        linea = linea + "\n";
                        ficheroOut.write(linea.getBytes());
                    }
                    linea = ficheroIn.readLine();//leo 3º linea
                    String[] datos = linea.split(" ");
                    int ancho = Integer.parseInt(datos[0]);
                    int alto = Integer.parseInt(datos[1]);

                    linea = linea + "\n";
                    ficheroOut.write(linea.getBytes()/*,0,linea.length()*/);

                    linea = ficheroIn.readLine();//leo 4º linea
                    linea = linea + "\n";
                    ficheroOut.write(linea.getBytes());

                    String datosln = "";
                    do {
                        linea = ficheroIn.readLine();

                        if (linea != null) {
                            datosln += linea.trim();//[0,0,0,128,255,0]
                            //datosln += " ";
                            String[] grises = linea.split(" ");

                            for (int i = 0; i < grises.length; i++) {
                                linea = grises[i];
                                int b = Integer.parseInt(linea);
                                ficheroOut.write((byte) b);                                
                            }
                            //linea = "\n";
                            //ficheroOut.write(linea.getBytes());

                        }
                    } while (linea != null);

                    ficheroOut.close();
                    ficheroIn.close();
                }

            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

}
