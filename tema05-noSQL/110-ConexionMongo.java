//Mario Belso Ros
package conexionmongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;

public class ConexionMongo {

    public static void main(String[] args) {
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        MongoClient cliente = new MongoClient();
        MongoDatabase db = cliente.getDatabase("polos");
        MongoCollection<Document> coleccion = db.getCollection("hielo");
        System.out.println("Cantidad de polos en la bbdd polos: "
                + db.getName() + " "+ coleccion.count() );

        for(Document doc : coleccion.find(new Document("marca", "Frigo"))){
            System.out.println(doc.toJson());
        }
    }

}
