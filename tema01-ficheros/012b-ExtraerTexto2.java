/*Crea un programa que lee el contenido de un fichero binario, mostrando 
en pantalla todo lo que sean caracteres imprimibles (basta con que sean desde
la A hasta la Z, junto con el espacio en blanco) e ignorando todos los demás 
caracteres. El nombre del fichero de entrada lo elegirá el usuario*/
//Azahara Carbonell Mateo

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;


public class ExtraerTexto2 {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Nombre del fichero :");
        String nombre=sc.nextLine();
        if((! new File(nombre).exists())){
            System.out.println("El fichero no existe");
        }
        else{
            try{
                FileInputStream fichero=new FileInputStream(new File(nombre));
                int dato=fichero.read();
                while(dato != -1){
                    if ((dato>='a'&& dato<='z') || (dato>='A'&& dato<='Z') || dato==' '){
                        System.out.println((char)dato);
                    }
                    dato=fichero.read();
                }
                fichero.close();
            }
            catch(IOException e){
                System.out.println(e.getMessage());
            }
        }
                
    }
    
}
