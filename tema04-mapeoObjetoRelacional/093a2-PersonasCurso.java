/*
Antonio DEFEZ
 */
package personascurso;

import java.util.List;
import java.util.Scanner;
import org.hibernate.Query;
import org.hibernate.Session;

public class PersonasCurso {


    public static void main(String[] args) {
        //para quitar los mensajes de info 
        @SuppressWarnings("unused")
        org.jboss.logging.Logger logger
                = org.jboss.logging.Logger.getLogger("org.hibernate");
        java.util.logging.Logger.getLogger("org.hibernate")
                .setLevel(java.util.logging.Level.SEVERE);
        boolean salida = false;
        Scanner entrada = new Scanner(System.in);
        String opcion;
        Session ses = NewHibernateUtil.getSessionFactory().openSession();
        do {
            mostrarMenu();
            opcion = entrada.nextLine();
            switch (opcion) {
                case "1"://mostrar persona
                    verPersona(ses);
                    break;
                case "2"://mostrar Curso
                    verCurso(ses);
                    break;
                case "3"://Busqueda por nombre
                    buscarPorNombre(ses);
                    break;
                case "4"://salir
                    salida = true;
                    break;

            }

        } while (!salida);
        ses.close();
        System.out.println("--------------------");

        NewHibernateUtil.getSessionFactory().close();
    }

    private static void mostrarMenu() {
        System.out.println("1.Ver Detalles Persona");
        System.out.println("2.Ver Detalles Curso");
        System.out.println("3.Buscar Usuario por Nombre");
        System.out.println("4.Salir");
        
    }

    private static void verPersona(Session ses) {
        System.out.println("Mostrando los Persona");
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introudce codigo de Persona");
        int codigo = entrada.nextInt();
        Query consulta2 = ses.createQuery("from Personas where identificador ="
                +codigo);
        //hace refencia a la clase de java
        List resultados2 = consulta2.list();

        for (Object resultado : resultados2) {
            Personas con = (Personas) (resultado);
            System.out.println(con);
            System.out.println("Lista de Cursos ");
            con.mostrarLista();
        }
     }

    private static void verCurso(Session ses) {
     System.out.println("Mostrando los Cursos");
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introudce codigo de Curso");
        int codigo = entrada.nextInt();
        Query consulta2 = ses.createQuery("from Cursos where identificador ="
                +codigo);
        //hace refencia a la clase de java
        List resultados2 = consulta2.list();

        for (Object resultado : resultados2) {
            Cursos con = (Cursos) (resultado);
            System.out.println(con);
            System.out.println("Lista de Personas  ");
            con.mostrarLista();
        }
    }

    private static void buscarPorNombre(Session ses) {
         System.out.println("Mostrando los Persona");
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introudce nombre de la Persona");
        String nombre = entrada.nextLine();
        Query consulta2 = ses.createQuery("from Personas where nombre like '%"
                +nombre+"%' "
                );
        //hace refencia a la clase de java
        List resultados2 = consulta2.list();

        for (Object resultado : resultados2) {
            Personas con = (Personas) (resultado);
            System.out.println(con);
            System.out.println("Lista de Cursos ");
            con.mostrarLista();
        }
     }
    
}
