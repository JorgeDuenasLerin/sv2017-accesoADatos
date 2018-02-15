
public class Componente implements java.io.Serializable {

    /**
     * Mario Belso
     */
    private static final long serialVersionUID = 1L;
    private String marca;
    private String modelo;
    private String categoria;
    private float precio;

    public Componente() {

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

}
