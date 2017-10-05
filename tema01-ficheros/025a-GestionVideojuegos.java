/**
 * Gestión de videojuegos (v2: Lista de datos)
 * Ivan Galan Pastor
*/

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Videojuego implements Serializable
{
    private String titulo;
    private String genero;
    private String plataforma;
    private String resumen;
    private int anho;
    
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
        return "Titulo: " + titulo + " Genero: " + genero 
                + " Plataforma: " + plataforma;
    }
    
    public String getTitulo() { return titulo; }
    public String getGenero() { return genero; }
    public String getPlataforma() { return plataforma; }
    public String getResumen() { return resumen; }
    public int getAnyo() { return anho; }
}



public class GestionVideojuegos 
{
    public static void guardarVideojuegos(
            ArrayList<Videojuego> listaVideojuegos)
    {

        try{
            PrintWriter fichero = new PrintWriter("videojuegos.txt");
            for(int posicionJuego = 0; posicionJuego < listaVideojuegos.size();
                    posicionJuego++){
                fichero.print(listaVideojuegos.get(posicionJuego).getTitulo()+",");
                fichero.print(listaVideojuegos.get(posicionJuego).getGenero()+",");
                fichero.print(listaVideojuegos.get(posicionJuego).getAnyo()+",");
                fichero.print(listaVideojuegos.get(posicionJuego).getPlataforma()+",");
                fichero.println(listaVideojuegos.get(posicionJuego).getResumen());
            }
            fichero.close();
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
    
    public static void buscar(ArrayList<Videojuego> lista){
        Scanner entrada = new Scanner(System.in);
        int posicionJuego = 0;
        System.out.println("Introduce el titulo a buscar");
        String titulo = entrada.nextLine();
        for(Videojuego juego : lista){
            if( ! juego.getTitulo().equals(titulo)){
                posicionJuego++;
            }
            else{
                break;
            }
        }
        if(posicionJuego==lista.size()){
            System.out.println("Título no encontrado.");
        }
        else{
            System.out.println("Título: "+lista.get(posicionJuego).getTitulo());
            System.out.println("Género: "+lista.get(posicionJuego).getGenero());
            System.out.println("Año: "+lista.get(posicionJuego).getAnyo());
            System.out.println("Plataforma: "+lista.get(posicionJuego).getPlataforma());
            System.out.println("Resumen: "+lista.get(posicionJuego).getResumen());
        }
        
    }
    
    public static void modificar(String titulo, String genero, int anyo,
                String plataf, String resumen, ArrayList<Videojuego>lista){
        Scanner teclado = new Scanner(System.in);
        System.out.println("Quieres modificar algun videojuego? S / N: ");
        String opcion = teclado.nextLine().toUpperCase();
        
        if(opcion.equals("S")){
            //titulo =
            String texto;
            int newAnyo=0;
            System.out.println("Titulo o intro para continuar: "+ titulo);
            texto = teclado.nextLine();
            if( ! texto.equals("")){
                titulo= texto;
            }
            
            System.out.println("Genero o intro para continuar: "+ genero);
            texto = teclado.nextLine();
            if( ! texto.equals("")){
                genero= texto;
            }
            
            System.out.println("Año o intro para continuar: "+ anyo);
            texto = teclado.nextLine();
            if( ! texto.equals("")){
                anyo= newAnyo;
            }
            
            System.out.println("Titulo o intro para continuar: "+ plataf);
            texto = teclado.nextLine();
            if( ! texto.equals("")){
                plataf= texto;
            }
            
            System.out.println("Titulo o intro para continuar: "+ resumen);
            texto = teclado.nextLine();
            if( ! texto.equals("")){
                resumen= texto;
            }  
        }
    }
    
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        
        int opcion;

        List<Videojuego> listaDeVideojuegos = new ArrayList();
        
        System.out.println("Bienvenido!");
        
        try{
            listaDeVideojuegos = Serializar.Cargar();
            System.out.println("Datos Cargados");
        }catch(Exception e){
            System.out.println("Error en la carga de los datos");
        }

        do
        {
            System.out.println("1. Añadir un nuevo videojuego");
            System.out.println("2. Mostrar Juegos");
            System.out.println("0. Salir");
            opcion = sc.nextInt();
            sc.nextLine();
            
            switch(opcion)
            {
                case 1:
                    
                    String nuevoTitulo;
                    String nuevoGenero;
                    String nuevaPlataforma;
                    String nuevoResumen;
                    int nuevoAnho;
                    
                    do
                    {
                        System.out.println("Introduce el titulo: ");
                        nuevoTitulo = sc.nextLine();
                        
                        System.out.println("Introduce su genero: ");
                        nuevoGenero = sc.nextLine();
                        
                        System.out.println("Introduce su plataforma: ");
                        nuevaPlataforma = sc.nextLine();
                        
                        System.out.println("Introduce un resumen: ");
                        nuevoResumen = sc.nextLine();
                        
                        System.out.println("Introduce su fecha de lanzamiento:");
                        nuevoAnho = sc.nextInt();

                        if ( ! nuevoTitulo.equals("") )
                        {
                            Videojuego videojuego = new Videojuego(nuevoTitulo, 
                                nuevoGenero, nuevaPlataforma, nuevoResumen,
                                nuevoAnho);
                            listaDeVideojuegos.add(videojuego);
                        }
                        else
                            System.out.println("Introduzca un titulo valido");
                    }
                    while( nuevoTitulo.equals("") );
                    
                    try{
                        Serializar.Guardar(listaDeVideojuegos);
                        System.out.println("Guardado con éxito");
                    }catch(Exception e){
                        System.out.println("Error al guardar");
                    }
                    
                    break;
                    
                case 2:
                    for(Videojuego x : listaDeVideojuegos){
                        System.out.println(x.toString());
                    }
                    break;

                default:
                    break;
            }
            
        }
        while( opcion != 0 );
    }
    
}

class Serializar  {
    public static void Guardar( List<Videojuego> listaDeVideojuegos ) 
            throws FileNotFoundException, IOException {
 
        File fichero = new File("videojuegos.dat");
        ObjectOutputStream ficheroObjetos = new ObjectOutputStream(
                                                new FileOutputStream(fichero));
        
        ficheroObjetos.writeObject(listaDeVideojuegos);
        
        ficheroObjetos.close();
    }
    
    public static List<Videojuego> Cargar() throws FileNotFoundException, 
                                IOException, ClassNotFoundException {
        File fichero = new File("videojuegos.dat"); 
        ObjectInputStream ficheroObjetos = new ObjectInputStream(
                                                new FileInputStream(fichero));
        List<Videojuego> listaDeVideojuegos;
        listaDeVideojuegos = (List<Videojuego>) ficheroObjetos.readObject();
        
        ficheroObjetos.close();
        
        return listaDeVideojuegos;
    }
}
