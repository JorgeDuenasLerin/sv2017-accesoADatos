
// Nacho  ;-p

import java.util.Scanner;

public class PruebaDeEclipse {

    public static void main(String[] args) 
    {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Dime tu nombre: ");
        String nombre = entrada.nextLine();
        System.out.print("Hola, "+ nombre + "!");
        entrada.close();
    }
}
