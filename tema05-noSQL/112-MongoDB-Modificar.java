// Sergio García Balsas
package mongo3;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.util.JSON;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;

/**
 *
 * @author Sergio
 */
public class Mongo3
{

    public static void Menu()
    {
        System.out.println("1. Mostrar");
        System.out.println("2. Buscar por marca");
        System.out.println("3. Añadir");
        System.out.println("4. Modificar precio");
        System.out.println("0. Salir");
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;

        //Para quitar los warning:
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        //Conectamos a Mongo:
        MongoClient cliente = new MongoClient();
        //Seleccionamos la base de datos a la que conectar:      
        MongoDatabase db = cliente.getDatabase("polos");

        //Seleccionamos la tabla (coleccion)
        //Importar org.bson (bson es el json de mongo)
        MongoCollection<Document> collection = db.getCollection("hielo");
        do
        {
            Menu();
            String opcion = sc.nextLine();
            switch (opcion)
            {
                case "1": //Mostrar
                    for (Document doc : collection.find())
                    {
                        System.out.println(doc.toJson());
                    }
                    break;

                case "2":
                    System.out.println("Marca a buscar?");
                    String m = sc.nextLine();

                    //Sacar solo frigo
                    // Creamos un filtro:
                    BasicDBObject filtros = (BasicDBObject) JSON.parse(
                            "{marca:\""+   m +          "\"}"
                    );
                    
                    for (Document doc : collection.find(filtros))
                    {
                        System.out.println(doc.toJson());
                    }
                    break;

                case "3": //Añadir
                    System.out.println("Nombre?");
                    String nombre = sc.nextLine();
                    System.out.println("Marca?");
                    String marca = sc.nextLine();
                    System.out.println("precio?");
                    float precio = sc.nextFloat();
                    Document doc = new Document();
                    doc.append("nombre", nombre);
                    doc.append("marca", marca);
                    doc.append("precio", precio);
                    collection.insertOne(doc); //TAMBIÉN HAY UN MANY (PARA GUARDAR UNA LISTA DE OBJETOS) (INSERTMANY)
                    break;

                case "4":
                    System.out.println("Precio nuevo?");
                    float p = sc.nextFloat();
                    sc.nextLine();
                    System.out.println("Nombre del polo?");
                    String nombrePolo = sc.nextLine();
                    collection.updateOne(
							Filters.eq("nombre", nombrePolo),
							Updates.set("precio", p ));
                    break;

                case "0":
                    salir = true;
                    break;
            }

        } while (!salir);
        System.out.println("Adios");
    }

}
