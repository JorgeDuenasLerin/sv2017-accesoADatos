
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListaDeComponentes implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 777L;
    List<Componente> componentes;
    Componente[] componentesArray;
    String nombreFichero;

    public ListaDeComponentes(String nombreFichero) {
        componentes = new ArrayList<>();
        this.nombreFichero = nombreFichero;
    }

    public Componente getComponente(int indice) {
        return componentes.get(indice);
    }

    public void setComponente(int indice, Componente componente) {
        componentes.set(indice, componente);
    }

    public Componente[] getComponentes() {
        componentesArray = new Componente[componentes.size()];
        for (int i = 0; i < componentes.size(); i++) {
            componentesArray[i] = componentes.get(i);
        }
        return componentesArray;
    }

    public void setComponentes(Componente[] componentes) {
        this.componentes = Arrays.asList(componentes);
    }

    public int getCantidad() {
        return componentes.size();
    }

    public void anyadir(Componente componente) {
        componentes.add(componente);
    }

    public ListaDeComponentes buscar(String texto) {
        ListaDeComponentes listaTemp = new ListaDeComponentes("listaTemp.dat");
        for (Componente com : componentes) {
            if (com.coincide(texto)) {
                listaTemp.anyadir(com);
            }
        }
        return listaTemp;
    }

    public String getNombreFichero() {
        return nombreFichero;
    }
}