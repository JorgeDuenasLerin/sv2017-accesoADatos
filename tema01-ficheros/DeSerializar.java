import java.io.*;
import java.lang.ClassNotFoundException;

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


public class DeSerializar  {
    public static void main( String[] args ) 
            throws FileNotFoundException, IOException, 
            ClassNotFoundException {
 
        File fichero = new File("ciudades.dat");
        FileInputStream ficheroSalida = 
            new FileInputStream(fichero);
        ObjectInputStream ficheroObjetos = 
            new ObjectInputStream(ficheroSalida);
        
        Ciudad c = (Ciudad) ficheroObjetos.readObject();
        c.escribir();
        
        c = (Ciudad) ficheroObjetos.readObject();
        c.escribir();
        
        ficheroObjetos.close();
    }
}

