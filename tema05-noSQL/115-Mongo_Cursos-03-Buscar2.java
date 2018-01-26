//Antonio Sevila
package mongo_cursos;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCommandException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.util.JSON;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;

/**
 *
 * @author antonio
 */
public class Mongo_Cursos {
    public static Document creaDocumento(Scanner teclado){
        System.out.println("Introduce codigo de curso");
        int cod = teclado.nextInt();
        teclado.nextLine();
        System.out.println("Introduce nombre del curso");
        String nomCurso = teclado.nextLine();
        System.out.println("Introduce fecha de inicio del curso (yyyy/mm/dd)");
        String fechaInicio = teclado.nextLine();
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Date fecha = null;
        try {
            fecha = format.parse(fechaInicio);
        } catch (ParseException ex) {
            Logger.getLogger(Mongo_Cursos.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("¿Cuántos alumnos asisten a este curso?");
        int numAlumnos = teclado.nextInt();
        teclado.nextLine();
        int codAlumno;
        String apellidosNombre;
        List<Document> listaAlumnos = new ArrayList<>();
        for(int numAlumno = 0; numAlumno < numAlumnos; numAlumno++){
            System.out.println("Código de alumno: ");
            codAlumno = teclado.nextInt();
            teclado.nextLine();
            System.out.println("Apellidos y nombre del alumno:");
            apellidosNombre = teclado.nextLine();
            listaAlumnos.add(new Document().append("cod", codAlumno)
                    .append("apenom", apellidosNombre));
        }
        Document documento = new Document();
        documento.append("cod", cod);
        documento.append("nombre", nomCurso);
        documento.append("inicio", fecha);
        documento.append("alumnos", listaAlumnos);
        
        return documento;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);
        MongoClient mongoClient = null;
        Scanner teclado = new Scanner(System.in);
        try {
            mongoClient = new MongoClient("localhost", 27017);
            System.out.println("Seleccionando la colección.");
            MongoDatabase database = mongoClient.getDatabase("academia");
            MongoCollection<Document> collection
                    = database.getCollection("cursos");

            System.out.println("1. Añadir curso");
            System.out.println("2. Buscar por curso");
            System.out.println("3. Buscar por nombre alumno");
            String opcion = teclado.nextLine();
            switch (opcion) {
                case "1":
                    Document documento = creaDocumento(teclado);
                    collection.insertOne(documento);
                    break;
                    
                case "2":
                    System.out.println("Introduce nombre del curso (parcial)");
                    String nomCurso = teclado.nextLine();
                    System.out.println(collection.find(
                            Filters.regex("nombre", nomCurso)).first().toJson());
                    break;
                
                case "3":
                    System.out.println("Introduce nombre del alumno");
                    String apenom = teclado.nextLine();
                    /*BasicDBObject filtro = (BasicDBObject) JSON.parse(
                            "{alumnos.apenom : { $regex: /" + apenom  + "/}}");*/
                    int contador = 0;
                    for(Document doc : collection
                            .find(Filters.regex("alumnos.apenom", apenom))){
                        System.out.println(doc.toJson());
                        contador++;
                    }
                    if(contador == 0){
                        System.out.println("No hay resultados.");
                    }
                    break;
            }
        } catch (MongoCommandException e) {
            System.err.println(e.getErrorMessage());
        }

        System.out.println("Desconectando de MongoDB.");
        mongoClient.close();
    }

}
