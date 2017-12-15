/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//Alexandra Sanchez Alonso
package bdretoshibernate;

import java.util.List;
import java.util.Scanner;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Sandra
 */
public class Bdretoshibernate {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        @SuppressWarnings("unused")
        org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger("org.hibernate");
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.SEVERE);
        Scanner teclado = new Scanner(System.in);

        boolean salir = true;
        String opcion = "";
        do {
            System.out.println("MENU");
            System.out.println("1.- Añadir reto ");
            System.out.println("2.- Buscar reto");
            System.out.println("3.- Modificar reto");

            System.out.println("4.- Eliminar");
            System.out.println("5.- Modificar titulo");
            System.out.println("6.- Cambiar dificultad");
            System.out.println("S.- Salir");
            opcion = teclado.nextLine();

            switch (opcion) {
                case "1":
                    anyadir(teclado);
                    break;
                case "2":
                    buscarPor(teclado);

                    break;
                case "3":
                    modificar(teclado);
                    break;
                case "4":
                    eliminar(teclado);

                    break;
                case "5":
                    modificarTitulo(teclado);
                    break;
                case "6":
                    cambiarDificultad(teclado);
                    break;
                case "s":
                case "S":
                    System.out.println("Saliendo...\n\n");
                    salir = false;
                    //para cerrar la sesion
                    NewHibernateUtil.getSessionFactory().close();
                    break;
                default:
                    System.out.println("Opcion incorrecta\n\n");
                    break;
            }
        } while (salir);

    }

    public static void anyadir(Scanner teclado) {
        System.out.print("Introduzca id: ");
        short id = Short.parseShort(teclado.nextLine());
        System.out.print("Introduzca titulo reto: ");
        String titulo = teclado.nextLine();
        System.out.print("Introduzca descripcion: ");
        String descripcion = teclado.nextLine();
        System.out.print("Introduzca dificultad: ");
        Byte dificultad = Byte.parseByte(teclado.nextLine());

        Session sesion = NewHibernateUtil.getSessionFactory().openSession();
        Transaction trans = sesion.beginTransaction();
        //short id, String titulo, String descripcion, Byte dificultad
        Reto reto = new Reto(id, titulo, descripcion, dificultad);
        sesion.save(reto);
        trans.commit();
        sesion.close();
    }

    public static void buscarPor(Scanner teclado) {//buscar por texto ordenar asc, ordenar por dificultad
        System.out.print("Dime que busca: ");
        String palabra = teclado.nextLine();
        Session sesion = NewHibernateUtil.getSessionFactory().openSession();
        Query consulta = sesion.createQuery(
                "select id,titulo,descripcion,dificultad FROM Reto"
                + " WHERE LOWER(titulo) LIKE '%" + palabra.toLowerCase() + "%'"
                + " OR"
                + " LOWER(descripcion) LIKE '%" + palabra.toLowerCase() + "%' "
                + "ORDER BY dificultad ASC");
        List<Object[]> resultados = consulta.list();

        for (Object[] resultado : resultados) {
            System.out.println("ID: " + (Short) resultado[0]);
            System.out.println("Titulo: " + (String) resultado[1]);
            System.out.println("Descripcion: " + (String) resultado[2]);
            System.out.println("Dificultad: " + (Byte) resultado[3]);
            System.out.println("---------------------------");
        }
    }

    public static void modificar(Scanner teclado) {//por id
        System.out.print("Que id busca: ");
        int id = Integer.parseInt(teclado.nextLine());
        Session sesion = NewHibernateUtil.getSessionFactory().openSession();
        Query consulta = sesion.createQuery("FROM Reto WHERE id=" + id);
        List resultados = consulta.list();
        Reto RetoAModificar = (Reto) resultados.get(0);
        Transaction trans = sesion.beginTransaction();
        System.out.println("Nuevo titulo: ");
        String tituloNuevo = teclado.nextLine();
        RetoAModificar.setTitulo(tituloNuevo);

        System.out.println("Nueva descripcion: ");
        String descripcionNueva = teclado.nextLine();
        RetoAModificar.setDescripcion(descripcionNueva);

        System.out.println("Nueva dificultad: ");
        String dificultadNueva = teclado.nextLine();
        RetoAModificar.setDificultad(Byte.parseByte(dificultadNueva));
        sesion.update(RetoAModificar);
        trans.commit();
        sesion.close();
    }

    public static void eliminar(Scanner teclado) {//por id
        Session sesion = NewHibernateUtil.getSessionFactory().openSession();
        System.out.print("Código del reto a borrar? ");
        String id = teclado.nextLine();
        try {
            Query consulta = sesion.createQuery("FROM Reto WHERE id = " + id);
            List<Reto> retos = consulta.list();
            if (retos.size() > 0) {
                System.out.println("¿Es este el reto (S/N)? " + retos.get(0).getTitulo());
                String opcion = teclado.nextLine().toUpperCase();
                if (opcion.equals("S")) {
                    Transaction trans = sesion.beginTransaction();
                    sesion.delete(retos.get(0));
                    trans.commit();
                    System.out.println("Reto borrado");
                }
            } else {
                System.out.println("No existe un reto con ese código");
            }
        } catch (Exception e) {
            System.out.println("Error al borrar el reto");

        }
    }

    public static void modificarTitulo(Scanner teclado) {
        Session sesion = NewHibernateUtil.getSessionFactory().openSession();
        Transaction trans = sesion.beginTransaction();
        int datosModificados = sesion.createQuery("UPDATE Reto SET "
                + "titulo = UPPER(titulo)").executeUpdate();

        trans.commit();

        System.out.println("Retos actualizados: "
                + datosModificados);

    }

    public static void cambiarDificultad(Scanner teclado) {
        Session sesion = NewHibernateUtil.getSessionFactory().openSession();
        System.out.println("dime id del reto: ");
        Short id = Short.parseShort(teclado.nextLine());
        System.out.println("dime nueva dificultad del reto: ");
        Byte dif = Byte.parseByte(teclado.nextLine());
        Transaction trans = sesion.beginTransaction();
        int datosModificados = sesion.createQuery(
                "update Reto "
                + "set dificultad = :nuevaDificultad "
                + "where id = :idDeReto")
                .setParameter("nuevaDificultad", dif)
                .setParameter("idDeReto", id)
                .executeUpdate();
        trans.commit();
    }

}
