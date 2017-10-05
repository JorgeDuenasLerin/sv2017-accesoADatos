//Serializar y deserializar videojuegos
//Antonio Sevila
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
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

public class GestionVideojuegos {

    public static void serializar(ArrayList<Videojuego> listaJuego){
        try{
            File fichero = new File("videojuego.dat");
            FileOutputStream ficheroSalida = new FileOutputStream(fichero);
            ObjectOutputStream ficheroObjetos =
                    new ObjectOutputStream(ficheroSalida);
            ficheroObjetos.writeObject(listaJuego);
            ficheroObjetos.close();
        }
        catch(FileNotFoundException i){
            System.out.println("No se ha podido abrir el archivo");
        }
        catch(IOException e){
            System.out.println("Problemas de entrada o salida");
        }
    }
    
    public static ArrayList<Videojuego> deserializar(){
        ArrayList<Videojuego> listaJuego = new ArrayList<>();
        try{
            File fichero = new File("videojuego.dat");
            FileInputStream ficheroEntrada = new FileInputStream(fichero);
            ObjectInputStream ficheroObjetosEntrada =
                    new ObjectInputStream(ficheroEntrada);
            listaJuego = (ArrayList<Videojuego>) ficheroObjetosEntrada.
                    readObject();
            ficheroObjetosEntrada.close();
            
        }
        catch(FileNotFoundException i){
            System.out.println("No se ha podido abrir el archivo");
        }
        catch(IOException e){
            System.out.println("Problemas de entrada o salida");
        }
        finally{
            return listaJuego;
        }
    }
    
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        String opcion;
        String titulo,genero,plataforma,resumen;
        Videojuego juego;
        ArrayList<Videojuego> listaJuegos;
        
        int anyo;
        if(new File("videojuego.dat").exists()){
            listaJuegos = deserializar();
        }
        else{
            listaJuegos = new ArrayList<>();
        }
        
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
                    listaJuegos.add(juego);
                    serializar(listaJuegos);
                    break;
                case "2":
                    for(Videojuego juegoDeLista : listaJuegos){
                        System.out.println(juegoDeLista);
                    }
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
