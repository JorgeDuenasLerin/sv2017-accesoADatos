//Mario Belso Ros
//Serializacion y Deserializacion

import java.io.Serializable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;

// ------------------------------------

class VideoJuego implements Serializable{

    private String titulo;
    private String genero;
    private int anyo;
    private String plataforma;
    private String resumen;

    public VideoJuego() {
    }

    public VideoJuego(String titulo, String genero, int anyo,
            String plataforma, String resumen) {
        this.titulo = titulo;
        this.genero = genero;
        this.anyo = anyo;
        this.plataforma = plataforma;
        this.resumen = resumen;
    }

    public void escribir()
    {
        System.out.print("Juego: "+this.titulo);
        System.out.print(" género: "+this.genero);
        System.out.print(" año: "+this.anyo);
        System.out.print(" plataforma: "+this.plataforma);
        System.out.println(" resumen: "+this.resumen);
        
    }
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getAnyo() {
        return anyo;
    }

    public void setAnyo(int anyo) {
        this.anyo = anyo;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }
}

// ------------------------------------

public class DeSerializacion {

    public static void main(String[] args)
            throws FileNotFoundException, IOException, ClassNotFoundException {
        try {
            //Lectura
            File fichero = new File("videojuegosIn.dat");
            FileInputStream ficheroEntrada
                    = new FileInputStream(fichero);
            ObjectInputStream ficheroObjetosIn
                    = new ObjectInputStream(ficheroEntrada);
            VideoJuego v1 = (VideoJuego) ficheroObjetosIn.readObject();
            v1.escribir();
            v1 = (VideoJuego) ficheroObjetosIn.readObject();
            v1.escribir();
            ficheroObjetosIn.close();
            //Salida
            fichero = new File("videojuegosOut.dat");
            FileOutputStream ficheroSalida
                    = new FileOutputStream(fichero);
            ObjectOutputStream ficheroObjetosOut
                    = new ObjectOutputStream(ficheroSalida);
            VideoJuego v2 = new VideoJuego("Dark Souls", "RPG", 2012, "PC", 
                    "Morir");
            ficheroObjetosOut.writeObject(v2);
            VideoJuego v3 = new VideoJuego("Dark Souls 2", "RPG", 2014, "PC",
                    "Más morir");
            ficheroObjetosOut.writeObject(v3);
            ficheroObjetosOut.close();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado.");
        } catch (IOException ex) {
            System.out.println("Error de lectura/escritura.");
        }
    }
}
