// Mostrar capitales a patir del nombre el pais
// Daniel Ossa

import java.io.File;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Capitales {

    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        
        try {
            File inputFile = new File("countries.xml");
            DocumentBuilderFactory dbFactory 
                = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            
            NodeList nList = doc.getElementsByTagName("country");
            
            String pais = "";
            
            do {
                System.out.print("De qué país buscas la capital? ");
                pais = scan.nextLine();

                if ( !pais.equals("") ) {
                    for (int temp = 0; temp < nList.getLength(); temp++) {
                        
                        Node nNode = nList.item(temp);
                        Element element = (Element) nNode;
                        
                        if ( element.getAttribute(
                                "translations").contains(pais) ||
                                element.getAttribute("name").contains(pais) ) {
                            System.out.println("La capital es: " +
                                    element.getAttribute("capital"));
                        }
                    }
                }                
            }
            while ( !pais.equals("") );   
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
