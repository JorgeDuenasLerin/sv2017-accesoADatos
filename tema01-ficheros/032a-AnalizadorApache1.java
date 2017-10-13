/*Crea un programa que pida al usuario el nombreEntrada de un fichero de log de Apache 
y vuelque a un segundo fichero (cuyo nombreEntrada indicará el usuario) las líneas 
separadas por más de "n" segundos (también escogidos por el usuario). Mejora 
deseable: que no muestre sólo la línea anterior al evento, sino las 10 
anteriores.*/

//Azahara Carbonell Mateo

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class AnalizadorApache {
   
    public static void main(String[] args) {
        ArrayList <String> lista = new ArrayList<>();
        ArrayList <String> resultado = new ArrayList<>();
        
        Scanner sc = new Scanner(System.in);
        int diferencia = 0;
        int mostrar = 0;
        System.out.print("Nombre del fichero de entrada: ");
        String nombreEntrada = sc.nextLine();
                
        if((! new File(nombreEntrada).exists())){
            System.out.println("El fichero no existe");
        }
        else{
            System.out.print("Nombre del fichero de salida: ");
            String nombreSalida = sc.nextLine();
            System.out.print("Segundos de diferencia: ");
            diferencia = sc.nextInt();
            System.out.print("Lineas a visualizar: ");
            mostrar = sc.nextInt();
            leer(nombreEntrada,lista);
  
            for(int i = 0;i<lista.size()-1;i++){                
                
                String linea = lista.get(i);
                Calendar fecha1  = obtenerFecha(linea);
                               
                Calendar fecha2  =  Calendar.getInstance();
                String linea2 = lista.get(i+1);
                fecha2 = obtenerFecha(linea2);
                
                long segundos = 
                        (fecha2.getTimeInMillis()-fecha1.getTimeInMillis())/1000;
                System.out.println(segundos);
                if(segundos >= diferencia){
                    int auxLineas = i;
                    int contador = mostrar;                    
                    resultado.add("--- Linea " + (i+2) +
                        ": Mas de "+ diferencia + " segundos de diferencia---");
                    while(auxLineas >= 0 && contador>0){ 
                        resultado.add(lista.get(auxLineas));
                        auxLineas--;
                        contador--;
                    }                    
                }
            }
            guardar(nombreSalida,resultado);
        }       
    }
    public static Calendar obtenerFecha(String linea){
        Calendar fecha1  =  Calendar.getInstance();
        
        //con esto separo la fecha que es el lineas[1]
        linea = linea.replace('[', '@');
        linea = linea.replace(']', '@');
        String[]lineas = linea.split("@");

        lineas[1] = lineas[1].replace(" +0200", "");
        String[]tiempo1 = lineas[1].split(":");
        String[]fechas1 = tiempo1[0].split("/");
        int dia1 = Integer.parseInt(fechas1[0]);
        int mes1 = obtenerNumeroMes(fechas1[1]);                
        int anyo1 = Integer.parseInt(fechas1[2]);

        fecha1.set(Calendar.YEAR, anyo1);
        fecha1.set(Calendar.MONTH, mes1);
        fecha1.set(Calendar.DATE, dia1);

        int hora1 = Integer.parseInt(tiempo1[1]);
        int minuto1 = Integer.parseInt(tiempo1[2]); 
        int segundo1 = Integer.parseInt(tiempo1[3]);

        fecha1.set(Calendar.HOUR_OF_DAY, hora1);
        fecha1.set(Calendar.MINUTE, minuto1);
        fecha1.set(Calendar.SECOND, segundo1);

        return fecha1;
    }
    
    public static void leer(String nombreEntrada, ArrayList<String> contenido) {
        try {
            BufferedReader fichLeer  =  new BufferedReader(new FileReader(nombreEntrada));
            String linea  =  "";
            String fecha = "";
            boolean esFecha = false;
            while (linea != null) {
                linea  =  fichLeer.readLine();
                if (linea != null) {
                    contenido.add(linea);                    
                }
            }            
            fichLeer.close();            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }        
    }
    public static int obtenerNumeroMes(String mes){
        int m = 0;
        String[]meses = {"Ene","Feb","Mar","Abr","May","Jun","Jul","Ago","Sep","Oct","Nov","Dic"};
        for(int i = 0;i<meses.length;i++){
            if(meses[i].equals(mes)){
                m = i;
            }
        } 
        return m;
    }

    public static void guardar(String nombreEntrada, ArrayList<String> contenido) {
        try {
            PrintWriter fichero  =  new PrintWriter(nombreEntrada);   
            for (String v : contenido) {
                fichero.println(v);
            }            
            fichero.close();
        } catch (FileNotFoundException e) {
            System.out.println("Se ha producido un fallo: "+e);
        }
    }
}
