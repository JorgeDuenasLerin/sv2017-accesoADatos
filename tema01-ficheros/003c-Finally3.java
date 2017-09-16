// package ejercicio1;


/*
  Crea un programa que pida frases al usuario 
  y las guarde en un fichero llamado "frases.txt", 
  repitiendo hasta que pulse Intro sin introducir ninguna frase. 
  Debe guardar la fecha y hora actual (en formato español) antes de cada frase, 
  y debe usar "finally".
 */
//Alexandra Sanchez Alonso

import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Calendar;

public class Finally3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner teclado = new Scanner(System.in);
        String cadena;
        PrintWriter fichero = null;

        try {
            fichero = new PrintWriter("frases.txt");

            do {
				System.out.println("Dime una frase: (intro para salir)");
				
                Calendar fecha = Calendar.getInstance();
                int anyo = fecha.get(Calendar.YEAR);
                int mes = fecha.get(Calendar.MONTH);
                int dia = fecha.get(Calendar.DAY_OF_MONTH);
                
                String hoy = anyo + "-" + mes + "-" + dia;
                
                int hora = fecha.get(Calendar.HOUR_OF_DAY);
                int minuto = fecha.get(Calendar.MINUTE);
                int segundo = fecha.get(Calendar.SECOND);
                
                String horaActual=hora + ":" + minuto + ":" + segundo;
                cadena = teclado.nextLine();

                if (!cadena.equals("")) {
                    fichero.println(hoy + " " + horaActual + " " + cadena);
                }
            } while (!cadena.equals(""));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fichero != null) {
                fichero.close();
            }
        }
    }
}
