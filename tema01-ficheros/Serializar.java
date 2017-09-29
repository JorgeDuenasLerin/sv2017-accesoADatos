import java.io.Serializable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

class Ciudad implements Serializable {
    
    protected String codigo;
    protected String nombre;
    
    // Constructor
    public Ciudad(String cod, String nom) {
        codigo = cod;
        nombre = nom;
    }
    
    // Método adicional
    public void escribir() {
        System.out.println(codigo + ": " + nombre);
    }
}


public class Serializar  {
    public static void main( String[] args ) 
            throws FileNotFoundException, IOException {
 
        File fichero = new File("ciudades.dat");
        FileOutputStream ficheroSalida = 
            new FileOutputStream(fichero);
        ObjectOutputStream ficheroObjetos = 
            new ObjectOutputStream(ficheroSalida);
        
        Ciudad c = new Ciudad("A", "Alicante");
        ficheroObjetos.writeObject(c);
        
        c = new Ciudad("Gr", "Granada");
        ficheroObjetos.writeObject(c);
        
        ficheroObjetos.close();
    }
}

