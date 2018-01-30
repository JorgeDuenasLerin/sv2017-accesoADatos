//Comprobar clave primaria.
package mongomatriculados;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import java.util.ArrayList;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoWriteException;
import com.mongodb.client.model.IndexOptions;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;

/**
 *
 * @author Mario Belso
 */
public class MongoMatriculados {

    public static void main(String[] args) {
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        MongoClient cliente = new MongoClient();
        MongoDatabase db = cliente.getDatabase("academia2");
        MongoCollection<Document> coleccion_cursos = db.getCollection("cursos2");
        MongoCollection<Document> coleccion_alumnos = db.getCollection("alumnos");
        MongoCollection<Document> coleccion_matricula = db.getCollection("matricula");
        coleccion_alumnos.createIndex(new BasicDBObject("codigo", 1),
                new IndexOptions().unique(true));
        
        boolean salida = false;
        Scanner entrada = new Scanner(System.in);
        do {
            mostrarMenu();
            String opcion = entrada.nextLine();
            switch (opcion) {
                case "1":
                    insertarCurso(coleccion_cursos, coleccion_alumnos, coleccion_matricula);
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "0":
                    salida = true;
                    break;
            }
        } while (!salida);
    }

    private static void mostrarMenu() {
        System.out.println("1.   Insertar curso");
        System.out.println("0.   Salir");
    }

    private static void insertarCurso(MongoCollection<Document> coleccion_cursos,
            MongoCollection<Document> coleccion_alumno,
            MongoCollection<Document> coleccion_matricula
    ) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduce nombre del curso");
        String nombre = entrada.nextLine();
        System.out.println("Introduce la fecha de inicio del curso (YYYY/MM/DD)");
        String fecha = entrada.nextLine();
        Date fecha_d = new Date(fecha);
        System.out.println("Introduce el codigo del curso");
        int codCurso = entrada.nextInt();
        entrada.nextLine();//limpiando buffer

        Document doc_curso = new Document();
        doc_curso.append("codigo", codCurso);
        doc_curso.append("nombre", nombre);
        doc_curso.append("inicio", fecha_d);
        coleccion_cursos.insertOne(doc_curso);

        boolean salida = false;
        do {
            System.out.print("Introduzca un nuevo usuario; para salir, ");
            System.out.println("deje el nombre en blanco");
            System.out.println("Introduzca el nombre");
            String nombre_alumn = entrada.nextLine();
            if ("".equals(nombre_alumn)) {
                salida = true;
            } else {
                System.out.println("Introduzca el codigo del alumno");
                int codAlumno = entrada.nextInt();
                entrada.nextLine();

                //insertando al alumno
                Document doc_alumno = new Document();
                doc_alumno.append("codigo", codAlumno);
                doc_alumno.append("nombre", nombre_alumn);
                try{
					coleccion_alumno.insertOne(doc_alumno);
                }
                catch(MongoWriteException e){
                    System.out.println("Clave duplicada, no se puede insertar.");
                }

                //insertando en matricula
                Document doc_matricula = new Document();
                doc_matricula.append("codigo_alumno", codAlumno);
                doc_matricula.append("codigo_curso", codCurso);
                coleccion_matricula.insertOne(doc_matricula);
            }
        } while (!salida);
    }
}
