//Añadir parametros pasados por el args a un fichero
//Antonio Sevila
package Ejercicio_4;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
public class anyadirDesdeCmdLine {
    public static void main(String[] args){
        Scanner entrada = new Scanner(System.in); 
        String frase;
        try {
            // Abrimos fichero para añadir
            PrintWriter fichero = new PrintWriter(
                new BufferedWriter(
                new FileWriter("frases.txt",true)));
            // Si no se ha indicado frase como parámetro, la pedimos y volcamos
            if(args.length == 0){
                System.out.println("Introduce una frase");
                frase = entrada.nextLine();
                if( ! frase.equals("") ){
                    fichero.println(frase);
                }
            }
            // Si hay parámetros, los recorremos y volcamos
            else{
                for(int cuentaPalabras = 0; cuentaPalabras < args.length;
                        cuentaPalabras++){
                    if(cuentaPalabras == args.length - 1){ // Última palabra
                        fichero.println(args[cuentaPalabras]); // Avance, sin espacio
                    }
                    else {  // Si no es la última palabra
                        fichero.print(args[cuentaPalabras] + " "); // Sin avanzar
                    }
                }
            }
            fichero.close();
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException i){
            i.printStackTrace();
        }
    }
}
