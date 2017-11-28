/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estudiosestudiantes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Usuario
 */
public class Estudiante {
    private String  nombre;
    private int codigo;
    private List<Estudios> estudios_realizados;

    public Estudiante(String nombre, int codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
        estudios_realizados = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public List<Estudios> getEstudios_realizados() {
        return estudios_realizados;
    }

    public void setEstudios_realizados(List<Estudios> estudios_realizados) {
        this.estudios_realizados = estudios_realizados;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Estudiante other = (Estudiante) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.estudios_realizados, other.estudios_realizados)) {
            return false;
        }
        return true;
    }

    
    @Override
    public String toString() {
        return "Estudiante{" + "nombre=" + nombre + ", codigo=" + codigo + 
                '}';
    }
    
    public void AddEstudio(Estudios Est)
    {
        estudios_realizados.add(Est);
    }
    
    
    public boolean estaMatriculado(int codigo)
    {
        for(Estudios tmp : estudios_realizados)
        {
            if(tmp.getCodigo()==codigo)
            {
                return true;
            }
        }
        return false;
    }
    
    
    
}
