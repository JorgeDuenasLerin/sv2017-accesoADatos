//Antonio sevila
package retoshibernate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author antonio
 */
public class RetosHibernate {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        @SuppressWarnings("unused")
        org.jboss.logging.Logger logger
                = org.jboss.logging.Logger.getLogger("org.hibernate");
        java.util.logging.Logger.getLogger("org.hibernate")
                .setLevel(java.util.logging.Level.SEVERE);
        
        String opcion;
        Scanner teclado = new Scanner(System.in);
        Session sesion = NewHibernateUtil.getSessionFactory().openSession();
        Transaction trans;
        List resultados;
        Query consulta;
        do {
            System.out.println("1. Añadir");
            System.out.println("2. Buscar los que empiecen por un cierto texto"
                    + ", ordenados por dificultad creciente (id, titulo,"
                    + " dificultad)");
            System.out.println("3. Modificar (ID)");
            System.out.println("4. Eliminar (ID)");
            System.out.println("S. Salir");
            opcion = teclado.nextLine();
            
            switch (opcion) {
                case "1":
                    System.out.println("Introduce código:");
                    short codigo = teclado.nextShort();
                    teclado.nextLine();
                    
                    System.out.println("Introduce titulo");
                    String titulo = teclado.nextLine();
                    
                    System.out.println("Introduce descripción");
                    String descripcion = teclado.nextLine();
                    
                    System.out.println("Introduce dificultad (1-10)");
                    byte dificultad = teclado.nextByte();
                    teclado.nextLine();
                    
                    trans = sesion.beginTransaction();
                    Reto persona = new Reto(codigo, titulo, descripcion,
                            dificultad);
                    
                    sesion.save(persona);
                    trans.commit();
                    break;
                case "2":
                    
                    System.out.println("Introduce parte del comienzo del titulo");
                    String tituloBuscar = teclado.nextLine().toLowerCase();
                    
                    consulta = sesion.createQuery("select id, titulo, dificultad "
                            + "FROM Reto "
                            + "WHERE LOWER(titulo) LIKE '"+ tituloBuscar + "%' "
                            + "ORDER BY dificultad ASC");
                    
                    List<Object[]> resultados2 = consulta.list();
                    System.out.println(String.format("%-20s","ID")
                            + String.format("%-20s","Titulo")
                            + String.format("%-20s","Dificultad"));
                    
                    System.out.println("------------------------------------------"
                            + "--------------");
                    for(Object[] obj : resultados2){
                        System.out.println(String.format("%-20s",(short)obj[0])
                                +String.format("%-20s",(String)obj[1])
                                + String.format("%-20s",(byte)obj[2]));
                    }
                    break;
                case "3":
                    System.out.println("Introduce código:");
                    short codigoMod = teclado.nextShort();
                    teclado.nextLine();
                    
                    consulta = sesion.createQuery("from Reto where"
                            + " id = " + codigoMod);
                    resultados = consulta.list();
                    
                    trans = sesion.beginTransaction();
                    
                    Reto retoMod = (Reto) resultados.get(0);
                    
                    System.out.println("Título antiguo: " + retoMod.getTitulo()
                            + ", nuevo título: ");
                    String nuevoTitulo = teclado.nextLine();
                    
                    retoMod.setTitulo(nuevoTitulo);
                    
                    sesion.update(retoMod);
                    trans.commit();
                    break;
                case "4":
                    System.out.println("Introduce código:");
                    short codigoDel = teclado.nextShort();
                    teclado.nextLine();
                    
                    consulta = sesion.createQuery("from Reto where"
                            + " id = " + codigoDel);
                    resultados = consulta.list();
                    
                    trans = sesion.beginTransaction();
                    
                    Reto retoDel = (Reto) resultados.get(0);
                    
                    sesion.delete(retoDel);
                    trans.commit();
                    break;
                case "S":
                case "s":
                    System.out.println("Adios!");
                    sesion.close();
                    NewHibernateUtil.getSessionFactory().close();
                    opcion = opcion.toLowerCase();
                    break;
                default:
                    System.out.println("Opción incorrecta.");
                    break;
            }
        } while (!opcion.equals("s"));
    }
    
}
