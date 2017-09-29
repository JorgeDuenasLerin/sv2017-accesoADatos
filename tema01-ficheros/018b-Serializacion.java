//Mario Belso Ros
//Serializacion.

import java.io.Serializable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

// -------------------------------------

class VideoJuego implements Serializable{

    private String titulo;
    private String genero;
    private int anyo;
    private String plataforma;
    private String resumen;//descripcion
    //CONSTRUCTOR VACIO

    public VideoJuego() {
    }

    //CONSTRUCTOR que pide todos los datos
    public VideoJuego(String titulo, String genero, int anyo,
            String plataforma, String resumen) {
        this.titulo = titulo;
        this.genero = genero;
        this.anyo = anyo;
        this.plataforma = plataforma;
        this.resumen = resumen;
    }

    //gets y sets
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

// -------------------------------------

public class Serializacion
{

    public static void main(String[] args)
            throws FileNotFoundException, IOException {
        File fichero = new File("videojuegos.dat");
        FileOutputStream ficheroSalida
                = new FileOutputStream(fichero);
        ObjectOutputStream ficheroObjetos
                = new ObjectOutputStream(ficheroSalida);
        VideoJuego v1 = new VideoJuego("Dark Souls", "RPG", 2012,"PC","Morir");
        ficheroObjetos.writeObject(v1);
        VideoJuego v2 = new VideoJuego("Dark Souls 2", "RPG", 2014, "PC",
        "Más morir");
        ficheroObjetos.writeObject(v2);
        ficheroObjetos.close();
    }
}
