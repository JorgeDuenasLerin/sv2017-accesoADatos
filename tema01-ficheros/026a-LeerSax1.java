// Leer detalles de un XML con SAX (1)
// Daniel Ossa

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

class ReadXml extends DefaultHandler{
 
public void procesarXml(){
    try {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        DefaultHandler manejadorEventos = new DefaultHandler(){

            String etiquetaActual = "";
            String contenido = "";

            // Método que se llama al encontrar inicio de etiqueta: '<'
            public void startElement(String uri, String localName,
                    String qName, Attributes attributes) 
                    throws SAXException {
                
                etiquetaActual = qName;
            }

            // Obtiene los datos entre '<' y '>'
            public void characters(char ch[], int start, int length)
                    throws SAXException {

                contenido = new String(ch, start, length);
            }

            // Llamado al encontrar un fin de etiqueta: '>'
            public void endElement(String uri, String localName, String qName)
                    throws SAXException {

                if ( etiquetaActual == "titulo" ) {
                    System.out.print("Título: " +contenido + ", ");
                    etiquetaActual = "";
                }
                else if ( etiquetaActual == "anyo") {
                    System.out.println("año: "+contenido);
                    etiquetaActual = "";
                }
            }
        };

        // Cuerpo de la función: trata de analizar el fichero deseado
        // Llamará a startElement(), endElement() y character() 
        saxParser.parse("videojuegos.xml", manejadorEventos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


public class LeerSax1 {
    
    public static void main(String[] args) {
        ReadXml ficheroXml = new ReadXml();
        ficheroXml.procesarXml();
    }
    
}
