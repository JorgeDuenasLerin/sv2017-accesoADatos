/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectorecetas;

/**
 *
 * @author Juan Salinas
 */
public class Receta {
    private String nombre;
    private String ingredientes;
    private int comensales;
    private int tiempo;
    private int dificultad;
    private String preparacion;

    public Receta() {
    }

    
    public Receta(String nombre, String ingredientes, 
            int comensales, int tiempo, int dificultad, 
            String preparacion) {
        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.comensales = comensales;
        this.tiempo = tiempo;
        this.dificultad = dificultad;
        this.preparacion = preparacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public int getComensales() {
        return comensales;
    }

    public void setComensales(int comensales) {
        this.comensales = comensales;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public int getDificultad() {
        return dificultad;
    }

    public void setDificultad(int dificultad) {
        this.dificultad = dificultad;
    }

    public String getPreparacion() {
        return preparacion;
    }

    public void setPreparacion(String preparacion) {
        this.preparacion = preparacion;
    }
    
    
}
