// Pedir lineas y columnas y dibujar con * una figura
// Daniel Ossa

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class EjemploThrows {

    public static void main(String[] args) 
		throws FileNotFoundException {
        
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Filas: ");
		int filas = scan.nextInt();
		System.out.print("Columnas: ");
		int columnas = scan.nextInt();
		
		PrintWriter fich = new PrintWriter("rectangulo.txt");
		for ( int fila = 0; fila < filas; fila++ ) {
			for ( int col = 0; col < columnas; col++) {
				fich.print("*");
			}
			fich.println();
		}
		fich.close();
    }    
}
