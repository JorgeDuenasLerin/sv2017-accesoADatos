/*Crea un programa que pida al usuario el nombre de un fichero BMP y diga si
está comprimido (deberás buscar información sobre cómo es la cabecera de un
BMP, que deberás leer como bloque).*/
//Azahara Carbonell Mateo

package bmpcomprimido;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;


public class BMPcomprimido {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        // TODO code application logic here
        Scanner sc=new Scanner(System.in);
        //el tamaño de la cabecera
        final int CABECERABMP = 54;
        String nombre="";
        boolean esBMP=false;
        if(args.length>0){
            nombre=args[0];
        }
        else{
            System.out.println("Nombre del fichero :");
            nombre=sc.nextLine();            
        }
        if((! new File(nombre).exists())){
            System.out.println("El fichero no existe");
        }
        else{
            try{
                //comprobar si es BMP    
                FileInputStream fichero =new FileInputStream(new File(nombre));
                int dato = fichero.read();
                if(dato=='B'){
                    dato=fichero.read();
                    if(dato=='M'){
                        esBMP=true;
                    }
                }
                fichero.close();
                
                if(esBMP){
                    System.out.println("Es un fichero BMP");
                    //comprobar si está comprimido
                    InputStream ficheroEntrada = 
                            new FileInputStream(new File(nombre));
                    //el array donde meto la cabecera
                    byte[] buf = new byte[CABECERABMP];
                    int cantidadLeida;
                    cantidadLeida = ficheroEntrada.read(buf, 0, CABECERABMP);
                    //si el tamaño de la cabecera y lo que he leido no es igual hay un fallo
                    if (cantidadLeida != CABECERABMP){
                        System.out.println("Fallo al leer el archivo");
                    }
                    else{
                        if(buf[30]==0){
                            System.out.println("Está sin comprimir");
                        }
                        else{
                            System.out.println("Está comprimido");
                        }
                    }
                    ficheroEntrada.close();    
                }
                else{
                    System.out.println("No es un fichero BMP");
                }
            }
            catch(IOException e){
                System.out.println("Se ha producido un fallo: "+e);
            }
        }
    }    
}
