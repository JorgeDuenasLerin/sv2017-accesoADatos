/*
 *SERGIO GARCIA BALSAS
 */
package proyectorecetas;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;

/**
 *
 * @author sergio
 */
public class ProyectoRecetas
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        ObjectContainer db = null;
        try
        {
            db = Db4o.openFile("recetas.dat");
            Receta r1 = new Receta("Pollo al Horno", "pollo, patatas", 
                    5, 20,5, "coges el pollo y lo metes al horno");
            
             Receta r2 = new Receta("Lentejas a la riojana", "lentejas, chorizo",
                    2, 30, 1, "se hierve");
            
            db.set(r1);
            db.set(r2);
            db.commit();
        } finally
        {
            if (db != null)
            {
                db.close();
            }
        }

    }
}
