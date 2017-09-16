// Markdown sencillo a HTML
// Samuel Sergio Garcia Espinosa

// package markdown;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Markdown
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String texto = "";
        PrintWriter fich = null;
        boolean firstTime = true;
        boolean withList = false;
        
        try
        {
            fich = new PrintWriter("markdown.txt");
            
            fich.println("<!DOCTYPE html>");
            fich.println("<html>");
            fich.println("<head>");
            fich.println("</head>");
            fich.println("<body>");
            
            do
            {
                texto = sc.nextLine();
                
                if ( ! texto.equals("") )
                {
                    if ( texto.startsWith("#") )
                    {
                        if ( withList )
                        {
                            fich.println("</ul>");
                            fich.println("<h1>" + texto.substring(1) + "</h1>"); 
                        }
                        else
                        {
                           fich.println("<h1>" + texto.substring(1) + "</h1>"); 
                        }
                        
                        withList = false;
                        firstTime = true;
                    }
                    else if ( texto.startsWith("*") )
                    {
                        withList = true;
                        
                        if ( firstTime )
                        {
                            fich.print("<ul>");
                            fich.println("<li>" + texto.substring(1) + "</li>");
                            
                            firstTime = false;
                        }
                        else
                        {
                            fich.println("<li>" + texto.substring(1) + "</li>");
                        }
                    }
                    else
                    {
                        if ( withList )
                        {
                            fich.println("<ul>");
                            fich.println("<p>" + texto + "</p>");
                        }
                        else
                        {
                            fich.println("<p>" + texto + "</p>");
                        }

                        withList = false;
                        firstTime = true;
                    }
                }
            }
            while( ! texto.equals("") );
            
            fich.println("</body>");
            fich.println("</html>");
            
            fich.close();
            
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
