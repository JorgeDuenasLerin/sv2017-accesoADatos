// Mario Belso
// ABM VideoJuegos.

import java.util.ArrayList;
import java.util.Scanner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;


// -------------------------------

class VideoJuego {

    private String titulo;
    private String genero;
    private String plataforma;
    private String anyo;
    private String resumen;

    public VideoJuego(String titulo, String genero, String plataforma,
            String anyo, String resumen) {
        this.titulo = titulo;
        this.genero = genero;
        this.plataforma = plataforma;
        this.anyo = anyo;
        this.resumen = resumen;
    }
    
    public void escribir()
    {
        System.out.println(this.titulo);
        System.out.println(this.genero);
        System.out.println(this.plataforma);
        System.out.println(this.anyo);
        System.out.println(this.resumen);
    }

    public String getTitulo() {
        return titulo;
    }

    public String getGenero() {
        return genero;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public String getAnyo() {
        return anyo;
    }

    public String getResumen() {
        return resumen;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public void setAnyo(String anyo) {
        this.anyo = anyo;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

}


// -------------------------------

class ManejoJuegos {

    private ArrayList<VideoJuego> videojuegos;

    public ManejoJuegos(ArrayList<VideoJuego> videojuegos) {
        this.videojuegos = videojuegos;
    }

    public ArrayList<VideoJuego> menu(ArrayList<VideoJuego> videojuegos) {
        String opc = "";
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println();//Separador
            System.out.println("1.- Añadir videojuego.");
            System.out.println("2.- Buscar videojuego.");
            System.out.println("3.- Modificar videojuego.");
            System.out.println("0.- Salir");
            System.out.print("Introduzca una opción:  ");
            opc = sc.nextLine();
            switch (opc) {
                case "1":
                    anyadirVideojuego();
                    break;
                case "2":
                    buscarVideojuego();
                    break;
                case "3":
                    modificarVideojuego();
                    break;
                case "0":
                    // No se debe dar mensaje de error al salir!
                    break;
                default:
                    System.out.println("Entrada erronea, prueba otra vez: ");
                    break;
            }
        } while (!opc.equals("0"));
        return this.videojuegos;
    }

    public void anyadirVideojuego() {
        String titulo, genero, plataforma, anyo, resumen;
        do {

            Scanner sc = new Scanner(System.in);
            System.out.print("Introduzca el nombre del videojuego: ");
            titulo = sc.nextLine();
            System.out.print("Introduzca el genero: ");
            genero = sc.nextLine();
            System.out.print("Introduzca la plataforma: ");
            plataforma = sc.nextLine();
            System.out.print("Introduzca el año del videojuego: ");
            anyo = sc.nextLine();
            System.out.print("Introduzca un resumen del videojuego: ");
            resumen = sc.nextLine();
        } while (titulo.equals("") || genero.equals("") || anyo.equals("")
                || anyo.equals("") || resumen.equals(""));
        VideoJuego juegoTemp = new VideoJuego(titulo, genero, plataforma, anyo,
                resumen);
        videojuegos.add(juegoTemp);
        System.out.println();
        System.out.println("Videojuego añadido!");
        System.out.println();
    }

    public void buscarVideojuego() {
        String tituloBusq, plataformaBusq;
        boolean coincidencias = false;
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduzca el titulo del videojuego: ");
        tituloBusq = sc.nextLine();
        //System.out.print("Introduzca la plataforma: ");
        //plataformaBusq = sc.nextLine();
        for (VideoJuego v : videojuegos) {
            if (v.getTitulo().toLowerCase().equals(tituloBusq.toLowerCase())
                    //&& v.getPlataforma().toLowerCase().equals(
                    //        plataformaBusq.toLowerCase())
                            ) {
                System.out.println("Se ha encontrado la siguiente "
                        + "coincidencia: ");
                System.out.println("Titulo: " + v.getTitulo());
                System.out.println("Plataforma: " + v.getPlataforma());
                System.out.println("Genero: " + v.getGenero());
                System.out.println("Año: " + v.getAnyo());
                System.out.println("Resumen: " + v.getResumen());
                coincidencias = true;
            }

        }
        if (!coincidencias) {
            System.out.println("No se han encontrado coincidencias.");
        }
    }

    public void modificarVideojuego() {
        String tituloBusq, plataformaBusq;
        boolean coincidencias = false;
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduzca el titulo del videojuego: ");
        tituloBusq = sc.nextLine();
        System.out.print("Introduzca la plataforma: ");
        plataformaBusq = sc.nextLine();
        for (int i = 0; i < videojuegos.size(); i++) {
            if (videojuegos.get(i).getTitulo().toLowerCase().equals(
                    tituloBusq.toLowerCase())
                    && videojuegos.get(i).getPlataforma().toLowerCase().equals(
                            plataformaBusq.toLowerCase())) {
                videojuegos.remove((int) videojuegos.indexOf(videojuegos.get(i)));
                String titulo, genero, plataforma, anyo, resumen;
                do {
                    System.out.print("Introduzca el nuevo nombre del videojuego: ");
                    titulo = sc.nextLine();
                    System.out.print("Introduzca el nuevo genero: ");
                    genero = sc.nextLine();
                    System.out.print("Introduzca la nueva plataforma: ");
                    plataforma = sc.nextLine();
                    System.out.println("Introduzca el nuevo año del videojuego: ");
                    anyo = sc.nextLine();
                    System.out.print("Introduzca un nuevo resumen del videojuego: ");
                    resumen = sc.nextLine();
                } while (titulo.equals("") || genero.equals("")
                        || anyo.equals("") || anyo.equals("")
                        || resumen.equals(""));

                VideoJuego vJTemp = new VideoJuego(titulo, genero, plataforma,
                        anyo, resumen);
                videojuegos.add(vJTemp);
            }
        }
    }

    public void setVideojuegos(ArrayList<VideoJuego> videojuegos) {
        this.videojuegos = videojuegos;
    }

    public ArrayList<VideoJuego> getVideojuegos() {
        return videojuegos;
    }

}

// -------------------------------

class EntradaSalida {

    private File nombreFichero;
    private ArrayList<VideoJuego> videojuegos;
    private VideoJuego juegoTmp;
    private String[] resultadoSplit;

    public EntradaSalida(String nombreFichero) {
        videojuegos = new ArrayList();
        this.nombreFichero = new File(nombreFichero);
    }

    public ArrayList<VideoJuego> lectura() {
        File archivoEntrada = new File(nombreFichero.getPath());
        if (archivoEntrada.exists()) {
            if (archivoEntrada.length() > 5) { //Nos aseguraremos de no intentar leer un fichero existente pero vacio.
                try {
                    BufferedReader ficheroEntrada
                            = new BufferedReader(
                                    new FileReader(nombreFichero));
                    String linea = ficheroEntrada.readLine();
                    while (linea != null) {
                        if (!linea.equals("")) {
                            resultadoSplit = linea.split("&&");
                            juegoTmp = new VideoJuego(resultadoSplit[0],
                                    resultadoSplit[1], resultadoSplit[2],
                                    resultadoSplit[3], resultadoSplit[4]);
                            videojuegos.add(juegoTmp);
                        }
                        linea = ficheroEntrada.readLine();
                    }
                    ficheroEntrada.close();
                    System.out.println("Lectura completada, "
                            + "guardando en memoria!");
                } catch (FileNotFoundException e) {
                    System.out.println("Fichero no encontrado.");
                } catch (IOException e) {
                    System.out.println("Error de lectura.");
                }
            }
        } else {
            // System.out.println("Archivo no encontrado.");
        }
        return videojuegos;
    }

    public void escritura(ArrayList<VideoJuego> videojuegos) {
        try {
            PrintWriter ficheroSalida = new PrintWriter(nombreFichero);
            //Dado que contamos con la posibilidad de modificar, conviene resetear el fichero.
            for (VideoJuego vj : videojuegos) {
                ficheroSalida.println(vj.getTitulo() + "&&" + vj.getGenero()
                        + "&&" + vj.getPlataforma() + "&&" + vj.getAnyo() + "&&"
                        + vj.getResumen());
            }
            ficheroSalida.close();
        } catch (IOException e) {
            System.out.println("Error de escritura..");
        }
        System.out.println("Escritura completada, finalizando programa!");
    }

    public File getNombreFichero() {
        return nombreFichero;
    }
}


// -------------------------------



public class GestorVideojuegos {

    public static void main(String[] args) {
        // String nombreFichero = setNombreFichero(args);
        String nombreFichero = "juegos.dat";
        EntradaSalida eS = new EntradaSalida(nombreFichero);
        ArrayList<VideoJuego> videojuegos = new ArrayList();
        videojuegos = eS.lectura();
        ManejoJuegos manejador = new ManejoJuegos(videojuegos);
        videojuegos = manejador.menu(videojuegos);
        eS.escritura(videojuegos);
    }

    static String setNombreFichero(String[] args) {
        Scanner sc = new Scanner(System.in);
        String nombreFichero;
        if (args.length > 0) {
            nombreFichero = args[0];
        } else {
            System.out.println("Introduzca el nombre del fichero a leer: ");
            nombreFichero = sc.nextLine();
        }
        return nombreFichero;
    }
}

