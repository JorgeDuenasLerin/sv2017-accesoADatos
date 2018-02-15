//Antonio Sevila
//Lista componentes
import java.util.ArrayList;


public class ListaDeComponentes {
    ArrayList<Componentes> listaComponentes;
    
    public ListaDeComponentes() {
        listaComponentes = new ArrayList<>();
    }
    
    public void anyadir(Componentes componente) {
        listaComponentes.add(componente);
    }
    
    public int getCantidad() {
        return listaComponentes.size();
    }
    
    public Componentes getComponente(int index) {
        return listaComponentes.get(index);
    }
    
    public Componentes[] getComponentes() {
        Componentes[] componentesArray = 
                new Componentes[listaComponentes.size()];
        for(int i = 0; i < listaComponentes.size(); i++) {
            componentesArray[i] = listaComponentes.get(i);
        }
        return componentesArray;
    }
    
    public void setComponente(int index, Componentes componente) {
        listaComponentes.set(index, componente);
    }
    
    public void setComponentes(Componentes[] newArray) {
        listaComponentes = new ArrayList<>();
        for(Componentes componente : newArray) {
            listaComponentes.add(componente);
        }
    }
}
