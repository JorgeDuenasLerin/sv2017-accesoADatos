// Dibujar en consola archivo bmp
// (versión que sí dibuja la imagen "boca arriba")
// Antonio Sevila y Daniel Ossa

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class DibujarBMP {
    public static void main(String[] args){
        FileInputStream ficheroBMP;
        int buffer_size;
        byte[] datos;
        int alto;
        int ancho;
        int contador;
        String cadenaDe1fila ="";
        try{
            File fichero = new File("welcome8.bmp");
            ficheroBMP = new FileInputStream(fichero);
            buffer_size = (int)fichero.length();
            datos = new byte[buffer_size];
            
            ficheroBMP.read(datos,0,buffer_size);
            alto = datos[22];
            ancho = datos[18];
            contador = buffer_size-1;
            for(int filas = 0; filas < alto; filas++ ){
                for(int columnas = 0; columnas < ancho; columnas++, contador--){
                    if(datos[contador] == 0){
                        cadenaDe1fila =  (".") + cadenaDe1fila;
                    }
                    else{
                        cadenaDe1fila =  ("#") + cadenaDe1fila;
                    }
                }
                System.out.print(cadenaDe1fila);
                System.out.println("");
                cadenaDe1fila = "";
            }
            
        }
        catch(IOException e){
            System.out.println("Problema de entrada o salida");
        }
    }
}
