//finally con fecha y hora + texto 
//Carla Liarte Felipe
//14-09-2017

import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Finally {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        PrintWriter fich = null;
        Date fechaActual = new Date();
        //System.out.println(fechaActual);
        DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        
        try {
            fich = new PrintWriter ("frases.txt");
            String texto = "";
    
            do {  
                System.out.println("Escribe una frase(Intro para salir): ");
                texto=entrada.nextLine();

                if(! texto.equals("")) {
                    //fich.println(texto);
                    fich.println(formatoHora.format(fechaActual) + " " +
                            formatoFecha.format(fechaActual) + "  " +
                            texto);
                }
            }
            while(!texto.equals(""));
            fich.close();
        }
        
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        
        finally{
            if(fich != null){
                fich.close();
            }
        }
    }
}

