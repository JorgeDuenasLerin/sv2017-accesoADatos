/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * @author abi
 */
public class InvertTxt2 {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String linea;
        System.out.println("Introduce fichero texto: ");
        String nombreFichero = sc.nextLine();
        ArrayList<String> list = new ArrayList<>();

        try {
            BufferedReader fichero = new BufferedReader(
                    new FileReader(new File(nombreFichero)));
            linea = fichero.readLine();
            while (linea != null) {
                list.add(linea);
                linea = fichero.readLine();
            }
            fichero.close();

            Collections.reverse(list);
            PrintWriter ficheroSalida = new PrintWriter("invertido.txt");
            for (String line : list) {
                ficheroSalida.println(line);
            }
            ficheroSalida.close();

        } catch (FileNotFoundException e) {
            System.out.println("Error, no se encuentra: ");
            e.getMessage();
        } catch (IOException e) {
            System.out.println("Error al leer: ");
            e.getMessage();
        }
    }

}
