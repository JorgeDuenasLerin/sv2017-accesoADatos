
//Antonio Sevila
//Lista de componentes
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * @author antonio
 *
 */
public class ListaDeComponentes implements Serializable {

    private static final long serialVersionUID = 141235L;
    private ArrayList<Componentes> listaComponentes;
    private String nombreFichero;

    public ListaDeComponentes() {
        listaComponentes = new ArrayList<>();
        nombreFichero = "componentes.dat";
        if(new File(nombreFichero).exists()) {
            cargar();
        }
    }

    public ListaDeComponentes(String nombreFichero) {
        listaComponentes = new ArrayList<>();
        this.nombreFichero = nombreFichero;
    }

    public void anyadir(Componentes componente) {
        listaComponentes.add(componente);
        guardar();
    }

    public int getCantidad() {
        return listaComponentes.size();
    }

    public Componentes getComponente(int index) {
        return listaComponentes.get(index);
    }

    public Componentes[] getComponentes() {
        Componentes[] componentesArray = new Componentes[listaComponentes
                .size()];
        for (int i = 0; i < listaComponentes.size(); i++) {
            componentesArray[i] = listaComponentes.get(i);
        }
        return componentesArray;
    }

    public void setComponente(int index, Componentes componente) {
        listaComponentes.set(index, componente);
        guardar();
    }

    public void setComponentes(Componentes[] newArray) {
        listaComponentes = new ArrayList<>();
        for (Componentes componente : newArray) {
            listaComponentes.add(componente);
        }
        guardar();
    }
    
    public ListaDeComponentes buscar(String texto) {
        ListaDeComponentes listaTmp = new ListaDeComponentes("fichTmp.dat");
        for(Componentes comp : listaComponentes) {
            if(comp.contieneTexto(texto)) {
                listaTmp.anyadir(comp);
            }
        }
        return listaTmp;
    }

    private void cargar() {
        try {
            File fichero = new File(nombreFichero);
            FileInputStream ficheroSalida = new FileInputStream(fichero);
            ObjectInputStream ficheroObjetos = new ObjectInputStream(
                    ficheroSalida);
            Componentes[] lista = (Componentes[]) ficheroObjetos
                    .readObject();
            setComponentes(lista);
            ficheroObjetos.close();
        } catch (ClassNotFoundException | IOException e) {
            System.err.println("Ha habido un problema con el fichero");
        }
    }

    private void guardar() {
        try {
            File fichero = new File(nombreFichero);
            FileOutputStream ficheroSalida = new FileOutputStream(fichero);
            ObjectOutputStream ficheroObjetos = new ObjectOutputStream(
                    ficheroSalida);

            ficheroObjetos.writeObject(getComponentes());
            ficheroObjetos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
