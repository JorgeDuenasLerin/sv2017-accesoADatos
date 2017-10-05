// Serializar y deserializar videojuegos
// (aproximación 1: un objeto cada vez)
// Antonio Sevila

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Scanner;

/**
 *
 * @author antonio
 */

class Videojuego implements Serializable
{
    private String titulo;
    private String genero;
    private String plataforma;
    private String resumen;
    private int anho;
    
    public Videojuego(){
        
    }
    public Videojuego(String titulo, String genero, String plataforma,
        String resumen, int anho)
    {
        this.titulo = titulo;
        this.genero = genero;
        this.plataforma = plataforma;
        this.resumen = resumen;
        this.anho = anho;
    }
    @Override
    public String toString(){
        return ("Titulo: "+titulo+", genero: "+genero+", plataforma: "
                +plataforma+", resumen: "+resumen+", año: "+anho);
    }
    
    public String getTitulo() { return titulo; }
    public String getGenero() { return genero; }
    public String getPlataforma() { return plataforma; }
    public String getResumen() { return resumen; }
    public int getAnyo() { return anho; }
}

public class VideojuegoSerializable {
    public static void serializar(Videojuego juego){
        ObjectOutputStream ficheroObjetos;
        try{
            
            File fichero = new File("videojuego1.dat");
            /* Abrimos fichero para añadir (append = true) */
            FileOutputStream ficheroSalida = new FileOutputStream(fichero, true);            
            ficheroObjetos = new ObjectOutputStream(ficheroSalida);
            ficheroObjetos.writeObject(juego);
            ficheroObjetos.close();
        }
        catch(FileNotFoundException i){
            System.out.println("No se ha podido abrir el archivo");
        }
        catch(IOException e){
            System.out.println("Problemas de entrada o salida");
        }
    }
    
    public static void deserializar(){
        Videojuego juego;
        ObjectInputStream ficheroObjetosEntrada = null;
        try{
            File fichero = new File("videojuego1.dat");
            FileInputStream ficheroEntrada = new FileInputStream(fichero);
            ficheroObjetosEntrada = new ObjectInputStream(ficheroEntrada);
            while(true){
                juego = (Videojuego) ficheroObjetosEntrada.readObject();
                mostrarObjetos(juego);
            }
        }
        catch(FileNotFoundException i){
            System.out.println("No se ha podido abrir el archivo");
        }
        catch(ClassNotFoundException y){
            System.out.println("No se ha encontrado una clase");
        }
        catch(IOException e){
            System.out.println("Fin de fichero");
        }
        finally{
            if(ficheroObjetosEntrada != null){
                try{
                    ficheroObjetosEntrada.close();
                }
                catch(IOException e){
                    
                }
            }
        }
    }
    
    public static void mostrarObjetos(Videojuego juego){
        System.out.println(juego);
    }
    
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        String opcion;
        String titulo,genero,plataforma,resumen;
        Videojuego juego;
        int anyo;
        do{
            System.out.println("Elige qué hacer: ");
            System.out.println("1. Guardar nuevo videojuego");
            System.out.println("2. Mostrar todos");
            System.out.println("S. Salir");
            opcion = teclado.nextLine().toUpperCase();
            
            switch(opcion){
                case "1":
                    System.out.println("Introduce titulo del juego");
                    titulo = teclado.nextLine();
                    System.out.println("Introduce genero: ");
                    genero = teclado.nextLine();
                    System.out.println("Introduce plataforma: ");
                    plataforma = teclado.nextLine();
                    System.out.println("Introduce resumen: ");
                    resumen = teclado.nextLine();
                    System.out.println("Introduce año: ");
                    anyo = teclado.nextInt();
                    teclado.nextLine();
                    
                    juego = new Videojuego(
                            titulo,genero,plataforma,resumen,anyo);
                    serializar(juego);
                    break;
                case "2":
                    deserializar();
                    break;
                case "S":
                    System.out.println("Adios");
                    break;
                default:
                    System.out.println("Selección no válida");
                    break;
            }
        }
        while( ! opcion.equals("S"));
    }
}
