//Antonio Sevil
//Main
import java.util.Scanner;

public class Main {

    private static void anyadirComponente(Scanner teclado,
            ListaDeComponentes listaComponentes) {
        System.out.println("Introduce marca");
        String marca = teclado.nextLine();
        System.out.println("Introduce modelo");
        String modelo = teclado.nextLine();
        System.out.println("Introduce categoria");
        String categoria = teclado.nextLine();
        System.out.println("Introduce precio");
        double precio = teclado.nextDouble();
        teclado.nextLine();
        Componentes componente = 
                new Componentes(marca, modelo, categoria, precio);
        listaComponentes.anyadir(componente);
    }
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        String opcion;
        ListaDeComponentes listaComponentes = new ListaDeComponentes();
        do {
            System.out.println("1. Añadir");
            System.out.println("2. Buscar");
            System.out.println("S. Salir");
            opcion = teclado.nextLine();
            
            switch(opcion) {
            case "1":
                anyadirComponente(teclado, listaComponentes);
                break;
            case "2":
                System.out.println("Introduce texto a buscar");
                String texto = teclado.nextLine();
                ListaDeComponentes compEncontrados =
                        listaComponentes.buscar(texto);
                for(Componentes comp : compEncontrados.getComponentes()) {
                    System.out.println(comp);
                }
                break;
            case "s":
            case "S":
                opcion = opcion.toUpperCase();
                break;
            }
        }
        while( ! opcion.equals("S"));
        teclado.close();
    }

}
