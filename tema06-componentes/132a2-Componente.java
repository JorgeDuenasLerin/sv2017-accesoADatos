
public class Componente implements java.io.Serializable {

    /**
     * Mario Belso
     */
    private static final long serialVersionUID = 776L;
    private String marca;
    private String modelo;
    private String categoria;
    private float precio;

    public Componente() {

    }

    public Componente(String marca, String modelo, String categoria,
            float precio) {
        super();
        this.marca = marca;
        this.modelo = modelo;
        this.categoria = categoria;
        this.precio = precio;
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

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public boolean coincide(String text) {
        text = text.toLowerCase();
        if (getMarca().toLowerCase().contains(text)
                || getModelo().toLowerCase().contains(text)
                || getCategoria().toLowerCase().contains(text)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Componente [marca=" + marca + ", modelo=" + modelo
                + ", categoria=" + categoria + ", precio=" + precio + "]";
    }

}
