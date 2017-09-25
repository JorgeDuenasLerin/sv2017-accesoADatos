/*pasar ficheros por linea de comandos y fusionarlos en uno 
"nombreFichero1.ordenado.txt"*/

// Sandra Sanchez 

package ejercicio10_ordenarficheros;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Ejercicio10_OrdenarFicheros {

    public static void crearFichero(ArrayList<String> textos, String nombre1) {
        try {
            PrintWriter miFichero = new PrintWriter(nombre1 + ".ordenado.txt");
            Collections.sort(textos);//ordenamos

            for (String lineas : textos) {
                miFichero.println(lineas);
            }

            miFichero.close();
         
        } catch (Exception e3) {
            System.out.println("Error" + e3.getMessage());
        }

    }

    public static void GuardarArray(String nombre, ArrayList<File> milista) {

        File fichero = new File(nombre);
        if (fichero.exists()) {
            milista.add(fichero);//añado a la lista
        } else {
            System.out.println("No existe ese fichero");
        }

    }

    public static void LeerFicheros(ArrayList<File> milista, ArrayList<String> textos) {

        for (File fichero : milista) {
            try {
                BufferedReader miFichero = new BufferedReader(
                        new FileReader(fichero));
                String linea;

                do {
                    linea = miFichero.readLine();
                    if (linea != null) {

                        textos.add(linea);//añado la linea.
                    }

                } while (linea != null);
                miFichero.close();

            } catch (Exception e2) {
                System.out.println("Error" + e2.getMessage());
            }
        }

    }

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        String nombre;
        String nombre1="";       

        ArrayList<String> textos = new ArrayList<>();//aqui se guardaran los textos.

        ArrayList<File> misFicheros = new ArrayList<>();//lista de ficheros de texto

        if (args.length == 0) {

            do {
                System.out.println("Dime nombre del fichero:");
                nombre = teclado.nextLine();
                nombre1=nombre;
                if (!nombre.equals("")) {
                    GuardarArray(nombre, misFicheros);//guardo el fichero
                    System.out.println("nombre ficher guardado");

                }
            } while (!nombre.equals(""));

        } else {//leo cada parametro y lo añado...
            for (int fichs = 0; fichs < args.length; fichs++) {

                GuardarArray(args[fichs], misFicheros);
                System.out.println("nombre ficher guardado");
            }
            nombre1=args[0];
        }
        System.out.println("lista de nombres de fichero guardada.");
        
        LeerFicheros(misFicheros, textos);
        System.out.println("textos guardados en el array ordenados");
                
        crearFichero(textos,nombre1);
        System.out.println("Fichero mixto creado");
    }
}
