/*
Crea un programa que convierta a HTML un fichero Markdown simple que 
el usuario escoja (sólo H1, P y UL / LI), como en este ejemplo:

# Acceso a datos

Este es un ejemplo

* Apartado 1

* Apartado 2

Adiós
 */

package pkg17_09_15conversorhtml;

/**
 *
 * @author Juan Salinas
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class ConversorHTML {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Fichero a leer");
        String nomFichLectura = sc.nextLine();
        String nomFichEscritura;
        boolean listaAbierta = false;
        
        if ( ! (new File(nomFichLectura)).exists()) {
            System.out.println("No se ha encontrado el archivo");
        }
        else{
            nomFichEscritura = nomFichLectura.replace(".txt", ".html");
            try {
                BufferedReader fichLectura = new BufferedReader(
                        new FileReader(new File(nomFichLectura)));
                PrintWriter fichEscritura = new PrintWriter(
                        new BufferedWriter(new FileWriter(
                                nomFichEscritura, true)));
                                
                fichEscritura.println("<DOCTYPE html>");
                fichEscritura.println("<html>");
                fichEscritura.println("<head>  </head>");
                fichEscritura.println("<body>");
                
                String linea = fichLectura.readLine();
                while (linea != null) {
                    
                    if (linea.startsWith("#")) {
                        fichEscritura.println("<h1>" + 
                                linea.substring(1).trim() + "</h1>");
                    }
                    else if (linea.startsWith("*")) {
                        if ( ! listaAbierta ) {
                            fichEscritura.print("<ul>"); 
                            listaAbierta = true;
                        }
                        fichEscritura.println("<li>" + 
                                linea.substring(1).trim() + "</li>");                        
                    }
                    else {
                        if ( listaAbierta ){
                            fichEscritura.println("</ul>");
                            listaAbierta = false;
                        }
                        fichEscritura.println("<p>" + linea.trim() + "</p>");
                    }     
                    linea = fichLectura.readLine();                    
                }                
                fichLectura.close();
                
                fichEscritura.println("</body>");
                fichEscritura.println("</html>");
                
                fichEscritura.close();
            } 
            catch (Exception e) {
            }
        }
    }
    
}
