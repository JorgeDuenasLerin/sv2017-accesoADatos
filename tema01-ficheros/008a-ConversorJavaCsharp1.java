//Fernando Carbonell Selva
//Crea un programa que haga una conversión básica de un programa en Java a su 
//equivalente en C#.

// Primera versión preliminar, sin refinar

//package ejercicios.java;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;


public class ConversorJavaCsharp1 {
    
        public static void main(String[] args) {
            
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Fichero a pasar a c#");
        String nomFichLectura = sc.nextLine();
        String nomFichEscritura;
        
        if ( ! (new File(nomFichLectura)).exists()) {
            System.out.println("No se ha encontrado el archivo");
        }
        else{
            nomFichEscritura = nomFichLectura.replace(".java", ".cs");
            try {
                BufferedReader fichLectura = new BufferedReader(
                        new FileReader(new File(nomFichLectura)));
                PrintWriter fichEscritura = new PrintWriter(
                        new BufferedWriter(new FileWriter(
                                nomFichEscritura, true)));
                                
                String linea = fichLectura.readLine();
                while (linea != null) {
                    
                    if (linea.contains("package")){
                        fichEscritura.println("");
                    }          
                    else if (linea.contains("main")){
                        linea=linea.replace("main","Main");
                        fichEscritura.println(linea);
                    }
                    else if (linea.contains("String")){
                        linea=linea.replace("String","string");
                        fichEscritura.println(linea);
                    }
                    else if (linea.contains("import")){
                        String[] arrayLinea=linea.split(linea);
                        fichEscritura.println("using "+arrayLinea.length);
                    }
                    else if (linea.contains("println")){
                        linea=linea.replace("println","WriteLine");
                        fichEscritura.println(linea);
                    }
                    else if (linea.contains("boolean")){
                        linea=linea.replace("boolean","bool");
                        fichEscritura.println(linea);
                    }
                    else if (linea.contains("substring")){
                        linea=linea.replace("substring", "Substring");
                        fichEscritura.println(linea);
                    }
                    else{
                        fichEscritura.println(linea);
                    }  
                    linea = fichLectura.readLine();                    
                }                
                fichLectura.close();
                fichEscritura.close();
            } 
            catch (Exception e) {
            }
        }
    }
}
  
