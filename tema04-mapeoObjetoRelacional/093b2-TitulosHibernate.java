//Antonio Sevila
package tituloshibernate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author antonio
 */
public class TitulosHibernate {

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
            System.out.println("1. Introducir persona");
            System.out.println("2. Introducir curso");
            System.out.println("3. Relacionar persona/curso");
            System.out.println("4. Ver datos de cursos");
            System.out.println("5. Ver datos de cursos por nombre de curso");
            System.out.println("6. Ver datos de personas por nombre de persona");
            System.out.println("S. Salir");
            opcion = teclado.nextLine();

            switch (opcion) {

                case "1":
                    System.out.println("Introduce código:");
                    short codigo = teclado.nextShort();
                    teclado.nextLine();

                    System.out.println("Introduce nombre");
                    String nombre = teclado.nextLine();

                    System.out.println("Introduce email");
                    String email = teclado.nextLine();

                    trans = sesion.beginTransaction();
                    Personas persona = new Personas(codigo, nombre, email, null);

                    sesion.save(persona);
                    trans.commit();
                    break;
                case "2":
                    System.out.println("Introduce código:");
                    short codCurso = teclado.nextShort();
                    teclado.nextLine();

                    System.out.println("Introduce nombre");
                    String nomCurso = teclado.nextLine();

                    DateFormat format = new SimpleDateFormat("yyyy/mm/dd");
                    System.out.println("Introduce fechaInicio (yyyy/mm/dd)");
                    String fechaInicio = teclado.nextLine();

                    System.out.println("Introduce fechaInicio (yyyy/mm/dd)");
                    String fechaFin = teclado.nextLine();

                    trans = sesion.beginTransaction();
                    Cursos curso = null;
                    try {
                        curso = new Cursos(codCurso, nomCurso,
                                format.parse(fechaInicio), format.parse(fechaFin),
                                null);
                    } catch (ParseException ex) {
                        System.out.println("Error: " + ex.getMessage());
                    }

                    sesion.save(curso);
                    trans.commit();

                    break;
                case "3":
                    // TO DO
                    break;
                case "4":
                    System.out.println("Mostrando todos los datos de cursos:");

                    consulta = sesion.createQuery("from Cursos");
                    resultados = consulta.list();
                    System.out.println("Cursos:");
                    for (Object resultado : resultados) {
                        Cursos cursoTmp = (Cursos) resultado;
                        System.out.println(" " + cursoTmp.getCod() + "-> Nombre: "
                                + cursoTmp.getNombre() + ", fechaInicio:  "
                                + cursoTmp.getFechainicio() + ", fechaFin: "
                                + cursoTmp.getFechafin());
                        for (Object resultado2 : cursoTmp.getPersonases()) {
                            Personas personaTmp = (Personas) resultado2;
                            System.out.println("    " + personaTmp.getCod() + ": "
                                    + personaTmp.getNombre());
                        }
                    }
                    break;
                case "5":
                    System.out.println("Introduce nombre del curso");
                    String nomCursoBuscar = teclado.nextLine();

                    consulta = sesion.createQuery("from Cursos where"
                            + " nombre = '" + nomCursoBuscar + "'");
                    resultados = consulta.list();
                    System.out.println("Cursos:");
                    for (Object resultado : resultados) {
                        Cursos cursoTmp = (Cursos) resultado;
                        System.out.println(" " + cursoTmp.getCod() + "-> Nombre: "
                                + cursoTmp.getNombre() + ", fechaInicio:  "
                                + cursoTmp.getFechainicio() + ", fechaFin: "
                                + cursoTmp.getFechafin());
                        for (Object resultado2 : cursoTmp.getPersonases()) {
                            Personas personaTmp = (Personas) resultado2;
                            System.out.println("    " + personaTmp.getCod() + ": "
                                    + personaTmp.getNombre() + ", Email: "
                                    + personaTmp.getEmail());
                        }
                    }
                    break;
                case "6":
                    System.out.println("Introduce nombre del curso");
                    String nomPersonaBuscar = teclado.nextLine();

                    consulta = sesion.createQuery("from Personas where"
                            + " nombre = '" + nomPersonaBuscar + "'");
                    resultados = consulta.list();
                    System.out.println("Personas:");
                    for (Object resultado : resultados) {
                        Personas personaTmp = (Personas) resultado;
                        System.out.println(" " + personaTmp.getCod() + "-> Nombre: "
                                + personaTmp.getNombre() + ", Email: "
                                + personaTmp.getEmail());
                        for (Object resultado2 : personaTmp.getCursoses()) {
                            Cursos cursoTmp = (Cursos) resultado2;
                            System.out.println("      " + cursoTmp.getCod()
                                    + "-> Nombre: " + cursoTmp.getNombre()
                                    + ", fechaInicio:  " + cursoTmp.getFechainicio()
                                    + ", fechaFin: " + cursoTmp.getFechafin());
                        }
                    }
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
