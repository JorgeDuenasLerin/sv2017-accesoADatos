package holamundo;

import java.util.Scanner;

/**
 *
 * @author Nacho
 */
class HolaMundo {

    public static void main(String[] args) {
        System.out.println("Hola mundo.");
        
        for ( int i=1 ; i<=10 ; i++ ) { 
            System.out.print( "Hola " );
        }
        System.out.println();
        
        double[] datos = { 10, 23.5, 15, 7, 8.9 };
        for (int i=0; i<datos.length; i++)
            System.out.print(datos[i]+" ");
        System.out.println();
        
        System.out.print( "Dime tu nombre... " );
        Scanner teclado = new Scanner(System.in);
        String nombre = teclado.nextLine();
        System.out.println( "Hola, " + nombre );
        
        if (nombre.length() > 5)
            System.out.println( "Varias letras: " + nombre.substring(2, 4) );
        
        boolean esMayusculas = false;
        if (nombre.equals(nombre.toUpperCase()))
            esMayusculas = true;
        
        if (esMayusculas)
            System.out.println( "Estaba en mayúsculas" );
    }
}
