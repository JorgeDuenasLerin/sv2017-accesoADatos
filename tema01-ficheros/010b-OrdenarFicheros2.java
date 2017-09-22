/*
 * Miguel Moya Ortega
    Ordenar ficheros
 */
package orden;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Orden {

    public static void main(String[] args) {
        List<String> files = new LinkedList<>();
        if (args.length == 0) {
            Scanner scanner = new Scanner(System.in);  
            String file;
            do {
                System.out.println(
                        "Introduce el nombre de un fichero (Intro para terminar)");
                file = scanner.nextLine();

                if (!"".equals(file)) files.add(file);
            } while (!"".equals(file));
        } else files.addAll(Arrays.asList(args));
        
        // Leemos del fichero
        List<String> totalLinesToAdd = new LinkedList<>();
        files.forEach(elem -> {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(
                        new File(elem)));
                String line;
                while ((line = reader.readLine()) != null) 
                    totalLinesToAdd.add(line);
                reader.close();
            } catch (FileNotFoundException e) {
                System.out.println(
                        "No se ha podido abrir o leer el fichero.");
            } catch (IOException ex) {
                System.out.println(
                        "No se ha podido abrir o leer el fichero.");
            }
        });
        // Escribir en el fichero
        try {
            PrintWriter writer = new PrintWriter(new BufferedWriter(
                    new FileWriter(files.get(0) + "ordenado.txt", true)));
            // Ordenamos el array
            totalLinesToAdd.sort((o1, o2) -> o1.compareTo(o2));
            // Escribimos en el fichero
            totalLinesToAdd.forEach(line -> writer.write(line));
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("No se ha podido abrir o leer el fichero.");
        } catch (IOException e) {
            System.out.println( "No se ha podido abrir o leer el fichero.");
        }  
    }
}
