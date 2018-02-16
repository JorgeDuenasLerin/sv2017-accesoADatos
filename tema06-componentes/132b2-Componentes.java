//Antonio Sevila
//Componentes class
import java.io.Serializable;

/**
 * 
 * @author antonio
 *
 */
public class Componentes implements Serializable {
    
    private static final long serialVersionUID = 123156L;
    private String marca;
    private String modelo;
    private String categoria;
    private double precio;
    
    public Componentes() {
        
    }
    public Componentes(String marca, String modelo, String categoria,
            double precio) {
        this.marca = marca;
        this.modelo = modelo;
        this.categoria = categoria;
        this.precio = precio;
    }
    public boolean contieneTexto(String texto) {
        if(getCategoria().contains(texto) ||
                getMarca().contains(texto) ||
                getModelo().contains(texto)) {
            return true;
        }
        else {
            return false;
        }
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    @Override
    public String toString() {
        return "Componentes [marca=" + marca + ", modelo=" + modelo
                + ", categoria=" + categoria + ", precio=" + precio + "]";
    }
    
}
