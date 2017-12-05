//Alejandro Gascón Martí
package cervezashibernate;

import java.util.List;
import java.util.Scanner;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CervezasHibernate {

    public static void main(String[] args) {
        //anyadir();
        verTodos();
    }

    public static void verTodos() {
        System.out.println("Mostrando todos los datos:");
        Session sesion = NewHibernateUtil.getSessionFactory().openSession();
        Query consulta = sesion.createQuery("from Cervezas");
        List resultados = consulta.list();
        for (Object resultado : resultados) {
            Cervezas cerveza = (Cervezas) resultado;
            System.out.println(cerveza.getId() + ": "
                    + cerveza.getMarca()+ ", "
                    + cerveza.getComentarios());
        }
        sesion.close();
    }

    public static void anyadir() {
        Scanner teclado = new Scanner(System.in);
        System.out.print("Introduzca la id: ");
        short id = Short.parseShort(teclado.nextLine());
        System.out.print("Introduzca la marca: ");
        String marca = teclado.nextLine();
        System.out.print("Introduzca el comentario: ");
        String comentario = teclado.nextLine();
        teclado.close();
        Session sesion = NewHibernateUtil.getSessionFactory().openSession();
        Transaction trans = sesion.beginTransaction();
        Cervezas c = new Cervezas(id, marca, comentario);
        sesion.save(c);
        trans.commit();
        sesion.close();
    }
}
