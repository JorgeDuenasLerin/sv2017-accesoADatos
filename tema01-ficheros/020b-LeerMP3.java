//Leer un mp3 v2
//Antonio Sevila

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author antonio
 */ //MOSTRAR SOLO LETRAS DEL ABECEDARIO, PUNTO Y NUMEROS. CAMBIAR EL comprobarSiEsNumOLetra PARA QUE COMPRUEBE LOS 4 BYTES REFERENTES A CADA FRAME QUE QUIERO MOSTRAr.
public class LeerMP3 {
    //Para mostrar el dato solo cuando sea algo logico y coherente.
    public static boolean esImprimible(char dato) {
        if ((dato >= 'a' && dato <= 'z') || (dato >= 'A' && dato <= 'Z')
                || (dato >= '0' && dato <= '9') || (dato == ' ') || (dato == '.')) {
            return true;
        } else {
            return false;
        }
    }
    //Busco una serie de identificadores preestablecidos
    public static boolean esIdCorrecto(byte[] buffer, int posicion) {
        String palabra = String.valueOf((char) buffer[posicion])
                + String.valueOf((char) buffer[posicion + 1])
                + String.valueOf((char) buffer[posicion + 2])
                + String.valueOf((char) buffer[posicion + 3]);
        switch (palabra) {
            case "TIT1":
            case "TIT2":
            case "TIT3":
            case "TALB":
            case "TRCK":
            case "TPE1":
            case "TPE2":
            case "TPE3":
                System.out.print(palabra + ": ");
                return true;
            default:
                return false;
        }
    }
    //Cada identificador (de los que busco) empieza por T
    public static boolean comprobarSiEsT(byte[] buffer, int posicion) {
        if (buffer[posicion] == 'T') {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduce el nombre del fichero: ");
        String nombreFichero = teclado.nextLine();

        int bufferSize;
        int tamanyoFrame;

        byte[] buffer;
        try {
            FileInputStream ficheroBin1 = new FileInputStream(
                    new File(nombreFichero));
            bufferSize = (int) new File(nombreFichero).length();
            buffer = new byte[bufferSize];
            ficheroBin1.read(buffer, 0, bufferSize);
            if (buffer[0] == 'I' && buffer[1] == 'D' && buffer[2] == '3') {
                for (int byteNum = 0; byteNum < bufferSize; byteNum++) {
                    if (comprobarSiEsT(buffer, byteNum)) {
                        /*Comprobar si los 4 bytes corresponden a la etiqueta*/
                        if (esIdCorrecto(buffer, byteNum)) {
                            /*Sacar tamaño del frame, sumo 7 porque el tamaño
                            esta 7 bytes mas adelante*/
                            tamanyoFrame = buffer[byteNum + 7];
                            /*posicionDatos empieza en 10 porque es a
                                partir de ahi que se empieza a contar el tamaño
                                del frame*/
                            for (int posicionDatos = 10;
                                    posicionDatos < tamanyoFrame + 10;
                                    posicionDatos++) {
                                if (esImprimible(
                                        (char) buffer[byteNum + posicionDatos])){
                                    System.out.print((char)
                                            buffer[byteNum + posicionDatos]);
                                }
                            }
                            System.out.println("");
                        }
                    }
                }

            }
            else{
                System.out.println("El archivo es un mp3 v1.");
            }

            ficheroBin1.close();
        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el archivo.");
        } catch (IOException y) {
            System.out.println("Problema de entrada o salida");
        } catch (Exception x) {
            System.out.println("Problema desconocido");
        }
    }

}
