//Mario Belso Ros
//Conversor C# -> Java

//package conversorj_c;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ConversorJavaCsharp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String nombreFicheroEntrada, nombreFicheroSalida;
        if (args.length > 0) {
            nombreFicheroEntrada = args[0];
        } else {
            System.out.println("Inserte el nombre del archivo a convertir: ");
            nombreFicheroEntrada = sc.nextLine();
        }
        try {
            BufferedReader ficheroEntrada = new BufferedReader(
                    new FileReader(
                            new File(nombreFicheroEntrada)));
            nombreFicheroSalida = nombreFicheroEntrada.replace(".java", ".cs");
            PrintWriter ficheroSalida = new PrintWriter(nombreFicheroSalida);
            ficheroSalida.println("using System;");

            String linea = "";
            
            while ( linea!= null ) {
                linea = ficheroEntrada.readLine();
                if (linea != null)
                {
                    if (linea.contains("String")) {
                        linea = linea.replace("String", "string");
                    }
                    if (linea.contains("boolean")) {
                        linea = linea.replace("boolean", "bool");
                    }
                    if (linea.contains("static void main")) {
                        linea = linea.replace("main", "Main");
                    }
                    if (!linea.contains("import") && linea.contains(".")) {
                        for (int contador = 0; contador < linea.length();
                                contador++) {
                            if (linea.charAt(contador) == '.') {
                                String letraMayus = String.valueOf(
                                        linea.charAt(contador + 1)).toUpperCase();
                                String lineaRestante =
                                        linea.substring(contador + 2);
                                linea = linea.substring(0, contador - 1);
                                linea += linea + letraMayus + lineaRestante;
                            }
                        }
                    }
                    if (linea.contains("System.out.print")) {
                        linea = linea.replace("System.out.print", "Console.Write");

                    }
                    if (linea.contains("System.out.println")) {
                        linea = linea.replace("System.out.println", 
                                "Console.WriteLine");
                    }
                    if (linea.contains("package")) {
                        linea = "";
                    }
                    if (linea.contains("Scanner")) {
                        linea = "";
                    }
                    ficheroSalida.println(linea);
                }
            }
            ficheroEntrada.close();
            ficheroSalida.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
