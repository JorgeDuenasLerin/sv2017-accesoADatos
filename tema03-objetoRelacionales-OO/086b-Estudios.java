/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estudiosestudiantes;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class Estudios {
    private String nombre;
    private int codigo;
    List<Estudiante> estudiantes_matriculados;

    public Estudios(String nombre, int codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
        estudiantes_matriculados=new ArrayList<>();
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

    public List<Estudiante> getEstudiantes_matriculados() {
        return estudiantes_matriculados;
    }

    public void setEstudiantes_matriculados(List<Estudiante> estudiantes_matriculados) {
        this.estudiantes_matriculados = estudiantes_matriculados;
    }

    @Override
    public String toString() {
        return "Estudios{" + "nombre=" + nombre + ", codigo=" +
                codigo +'}';
    }
    
    public void AddEstudiante(Estudiante nEstudiante)
    {
        estudiantes_matriculados.add(nEstudiante);
    }
    
    
    public boolean estaMatriculado(int codigo_alumno)
    {
        for(Estudiante tmp:estudiantes_matriculados)
        {
            if(tmp.getCodigo()==codigo_alumno)
                return true;
        }
        return false;
    }
    
}
