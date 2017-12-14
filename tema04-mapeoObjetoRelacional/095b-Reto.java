package retoshibernate;
// Generated 14-dic-2017 18:07:18 by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Reto generated by hbm2java
 */
@Entity
@Table(name="reto"
    ,schema="public"
)
public class Reto  implements java.io.Serializable {


     private short id;
     private String titulo;
     private String descripcion;
     private Byte dificultad;

    public Reto() {
    }

	
    public Reto(short id) {
        this.id = id;
    }
    public Reto(short id, String titulo, String descripcion, Byte dificultad) {
       this.id = id;
       this.titulo = titulo;
       this.descripcion = descripcion;
       this.dificultad = dificultad;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false, precision=4, scale=0)
    public short getId() {
        return this.id;
    }
    
    public void setId(short id) {
        this.id = id;
    }

    
    @Column(name="titulo", length=30)
    public String getTitulo() {
        return this.titulo;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    
    @Column(name="descripcion")
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
    @Column(name="dificultad", precision=2, scale=0)
    public Byte getDificultad() {
        return this.dificultad;
    }
    
    public void setDificultad(Byte dificultad) {
        this.dificultad = dificultad;
    }




}


