import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthSeparatorUI;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        
        Serializador ser = new Serializador();
        ListaDeComponentes listaComponentes = new ListaDeComponentes(
                "ListaComps.dat");
        try {
            listaComponentes.setComponentes(ser.cargar());
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String opc = "";
        do {
            mostrarMenu();
            opc = sc.nextLine();

            switch (opc) {
            case "1":
                listaComponentes.anyadir(anyadirComponente());
                System.out.println("Componente añadido!");
                try {
                    ser.guardar(listaComponentes);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            case "2":
                buscar(listaComponentes);
                break;

            case "0":

                System.out.println("Saliendo!");
                break;
            }

        } while (!opc.equals("0"));

    }

    static void mostrarMenu() {
        System.out.println();
        System.out.println("1.- Añadir componente.");
        System.out.println("2.- Buscar componentes.");
        System.out.println("0.- Salir.");
        System.out.println("Introduzca una opcion: ");
        System.out.println();
    }

    static Componente anyadirComponente() {
        System.out.println("Introduzca marca: ");
        String marca = sc.nextLine();
        System.out.println("Introduzca modelo: ");
        String modelo = sc.nextLine();
        System.out.println("Introduzca el precio: ");
        Float precio = sc.nextFloat();
        sc.nextLine();
        System.out.println("Introduzca la categoria: ");
        String categoria = sc.nextLine();

        return new Componente(marca, modelo, categoria, precio);
    }

    static void buscar(ListaDeComponentes lista) {
        System.out.println("Introduzca un texto para buscar coincidencias: ");
        String text = sc.nextLine();
        lista = lista.buscar(text);
        for(Componente com : lista.getComponentes()) {
            System.out.println(com.toString());
        }
    }
}
