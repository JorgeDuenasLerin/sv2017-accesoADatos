//Crea un programa que pida al usuario el nombre de dos ficheros y diga si son 
//iguales o no lo son.

//Azahara Carbonell Mateo

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;


public class CompararFicheros {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        boolean iguales=false;
        String nombre1="";
        String nombre2="";
        byte[] buf1=null;
        byte[] buf2=null;

        int contador=0;
        if(args.length>0){
            nombre1=args[0];
            nombre2=args[1];
        }
        else{
            System.out.println("Nombre del fichero 1:");
            nombre1=sc.nextLine();
            System.out.println("Nombre del fichero 2:");
            nombre2=sc.nextLine();
        }
        buf1=leerEnBloque(nombre1);
        buf2=leerEnBloque(nombre2);
        if(buf1.length==buf2.length){
            while( contador <= buf1.length-1 
                    && buf1[contador]==buf2[contador]){
                contador++;
            }
            if(contador > (buf2.length-1)){
                iguales=true;
            }
        }
        if(iguales){
            System.out.println("Son iguales");
        }
        else{
            System.out.println("No son iguales");
        }       
    }
    
    public static byte[] leerEnBloque(String nombre){
        byte[] buf=null;
        try{
            File ficheroTamanyo=new File(nombre); 
            final int TAMANYO = (int)ficheroTamanyo.length();            
            InputStream ficheroEntrada = new FileInputStream(new File(nombre));
            //el array donde relleno
            buf = new byte[TAMANYO];
            int cantidadLeida;
            cantidadLeida = ficheroEntrada.read(buf, 0, TAMANYO);          
            if (cantidadLeida != TAMANYO){
                System.out.println("Fallo al leer el archivo");
            }
            ficheroEntrada.close();           
        }
        catch(IOException e){
            System.out.println("Se ha producido un fallo: "+e);
        }
        finally{
            return buf;
        }
    }

}
