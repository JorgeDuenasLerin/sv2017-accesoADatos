//Gestor de una biblioteca de libros
//Alejandro Gascón, Javier Montejo, Daniel Ossa

package biblioteca;

import java.util.Scanner;

public class Biblioteca {

    public static void main(String[] args) {

        boolean finish = false;
        Scanner scanner = new Scanner(System.in);
        byte opcion;
        
        ListaLibros listaLibros = new ListaLibros();
        listaLibros.cargarLista();
        
        while (!finish) {
            System.out.println("¿Qué operación desea realizar?");
            System.out.println("    1) Añadir.");
            System.out.println("    2) Mostrar.");
            System.out.println("    3) Exportar.");
            System.out.println("    4) Salir.");
            opcion = scanner.nextByte();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    System.out.println("Título: ");
                    String titulo = scanner.nextLine();
                    System.out.println("Autor: ");
                    String autor = scanner.nextLine();
                    System.out.println("Número de páginas: ");
                    int numPag = scanner.nextInt();
                    
                    Libro libro=new Libro(titulo,autor,numPag);
                    listaLibros.nuevoLibro(libro);
                    
                    
                    break;
                case 2:
                    listaLibros.cargarLista();
                    listaLibros.mostrarLibros();
                    break;
                case 3:
                    listaLibros.exportarLista();
                    break;
                case 4:
                    System.out.println("Saliendo del programa");
                    finish=true;
                    break;
                default:
                    System.out.println("Opción incorrecta");
                    break;
                         
            }

        }

    }
}
