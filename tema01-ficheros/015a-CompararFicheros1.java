//Comparar si dos ficheros son iguales
//Antonio Sevila
package compararficheros;
import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;
/**
 *
 * @author antonio
 */
public class CompararFicheros {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduce el nombre del fichero 1: ");
        String nombreFichero1 = teclado.nextLine();
        System.out.println("Introduce el nombre del fichero 2: ");
        String nombreFichero2 = teclado.nextLine();
        int tamanyo1;
        int tamanyo2;
        int contador = 0;
        boolean noSonIguales = false;
        boolean sonIguales = false;
         try{
            FileInputStream ficheroBin1 = new FileInputStream(
                    new File(nombreFichero1));
            tamanyo1 = (int)new File(nombreFichero1).length();
            
            FileInputStream ficheroBin2 = new FileInputStream(
                    new File(nombreFichero2));
            tamanyo2 = (int)new File(nombreFichero1).length();
            if(tamanyo1 != tamanyo2)
                sonIguales = false;

            if(sonIguales){
                byte[] bytes1 = new byte[tamanyo1];
                byte[] bytes2 = new byte[tamanyo1];
                
                ficheroBin1.read(bytes1,0,tamanyo1);
                ficheroBin2.read(bytes2,0,tamanyo1);
                
                ficheroBin1.close();
                ficheroBin2.close();
            }
            
            while( sonIguales && contador < tamanyo1){
                if(bytes1[contador] !=bytes2[contador]){
                    sonIguales = false;
                }
                contador++;
            }
            if( ! sonIguales){
                System.out.println("No son iguales los ficheros");
            }
            else {
                System.out.println("Son iguales");
            }
        }
            
        catch(Exception e){
            System.out.println("Unknown error");
        }
    }
}
