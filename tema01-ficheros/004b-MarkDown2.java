//Convertir inputs de formato Markdown a formato HTML.
//Mario Belso Ros

//package markdown;

import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MarkDown {

    public static void main(String[] args) {
        try {
            String frase;
            int cont = 0;
            Scanner entrada = new Scanner(System.in);
            System.out.println("Inserte una frase 'MD' para convertirlo a HTML: ");
            PrintWriter fichero = new PrintWriter("conversiónHTML.html");
            fichero.println("<!DOCTYPE html>");
            fichero.println("<html>");
            fichero.println("<head>");
            fichero.println("<meta charset='UFT-8'>");
            fichero.println("</head>");
            fichero.println("<body>");
            do {
                frase = entrada.nextLine();
                if (!frase.equals("")) {
                    switch (frase.charAt(0)) {
                        case '#':
                            fichero.println("<h1>" + frase.substring(1) + "</h1>");
                            cont = 0;
                            break;
                        case '*':
                            if (cont == 0) {
                                fichero.println("<ul>");
                            }
                            fichero.println(frase.substring(1));
                            cont++;
                            break;
                        default:
                            fichero.println("<p>" + frase + "</p>");
                            cont = 0;
                            break;
                    }
                }
            } while (!frase.equals(""));
            fichero.println("</body");
            fichero.println("/html>");
            fichero.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
