/* Mario Belso Ros tabla hibernate

tabla:

CREATE TABLE concursos (
	id varchar(15) PRIMARY KEY,
    nombre varchar(35),
    fecha date

INSERT INTO concursos (id, nombre, fecha)
VALUES ('001', 'Comer tartas', '2007-05-01'),
('002', 'Backwards Speedrun Half-Life', '1999-07-05');

);

 */
package concursoshibernate;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class ConcursosHibernate {

    public static void main(String[] args) {
        @SuppressWarnings("unused")
        org.jboss.logging.Logger logger
                = org.jboss.logging.Logger.getLogger("org.hibernate");
        java.util.logging.Logger.getLogger("org.hibernate")
                .setLevel(java.util.logging.Level.SEVERE);
        verTodos();
        NewHibernateUtil.getSessionFactory().close();

    }

    public static void verTodos() {
        System.out.println("Mostrando todos los concursos:");
        Session sesion = NewHibernateUtil.getSessionFactory().openSession();
        Query consulta = sesion.createQuery("from Concursos");
        List resultados = consulta.list();
        for (Object resultado : resultados) {
            Concursos concurso = (Concursos) resultado;
            System.out.println("Id: " + String.format("%-5s", concurso.getId())
                    + "  Nombre: " + String.format("%-5s", concurso.getNombre())
                    + "  Fecha: " + String.format("%-5s", concurso.getFecha()
                            .toString()));
        }
        sesion.close();
    }

}
