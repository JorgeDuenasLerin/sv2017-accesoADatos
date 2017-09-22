/*Antonio defez*/
package ordenarficheros;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Ordenarficheros 
{

    public static ArrayList<String> load(ArrayList<String> miLista,
            String titulo) throws IOException
    {
        String linea;
        String nombrefich=titulo;
        BufferedReader ficheroEntrada=null;
        try 
        {
            ficheroEntrada=
                    new BufferedReader(
                    new FileReader(new File(nombrefich)));
            do
            {
                linea=ficheroEntrada.readLine();
                if(linea!=null)
                {
                    miLista.add(linea);
                }
            }
            while(linea!=null);
        } 
        catch (IOException e) 
        {
            System.out.println("Error en entrada salida");
        }
        finally
        {
            if(ficheroEntrada!=null)
                ficheroEntrada.close();
        }
       return miLista;
    }
    
    public static void save(ArrayList<String> miLista,ArrayList<String> titulo)
    {
        String nombrefich=titulo.get(0)+"ordenado.txt";
        PrintWriter ficherosalida=null;
        try 
        {
            ficherosalida=new PrintWriter(nombrefich);
            
            for(int i=0; i<miLista.size();i++)
            {
                ficherosalida.println(miLista.get(i));
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Error de salida");
        }
        finally
        {
            if(ficherosalida!=null)
            {
                ficherosalida.close();
            }
        }
    }
    
    
    public static void main(String[] args) 
    {
       ArrayList<String>titulos = new ArrayList<>();
       ArrayList<String>lineas = new ArrayList<>();
       Scanner entrada = new Scanner(System.in);
       String titulo;
       if(args.length!=0)
       {
           for(int i=0; i<args.length; i++)
           {
               titulos.add(args[i]);
           }
       }
       else
       {
           do
           {
               System.out.println("Introduce el nombre del fichero a ordenar");
               titulo=entrada.nextLine();
               if(!titulo.equals(""))
               {
                   titulos.add(titulo);
               }
           }
           while(!titulo.equals(""));
       }
       
       for(int i=0; i<titulos.size();i++)
       {
           try 
           {
               lineas=load(lineas,(String)titulos.get(i));
           }
           catch (IOException ex) 
           {
               System.out.println("Error de Entrada salida");
           }
       }
       
       Collections.sort(lineas);
       //ordenar el arraylist
       /*
       for(int i=0 ; i < lineas.size()-1;i++)
       {
           for(int j=i+1; j<lineas.size(); j++)
           {
               if( lineas.get(i).compareTo(lineas.get(j))>0)
               {
               String temporal=lineas.get(i);
               lineas.set(j, lineas.get(j));
               lineas.set(j, temporal);
               }
           }
       }
        */       
       
       //guardar en fichero nuevo
       save(lineas,titulos);
    }
    
}
