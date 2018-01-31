//Alejandro Gascón Martí
package academia2;

import com.mongodb.MongoClient;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;

public class Academia2 {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        String opcion = "";
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        MongoClient cliente = new MongoClient();

        MongoDatabase db = cliente.getDatabase("academia2");

        do {
            System.out.println("1.-Añadir curso");
            System.out.println("1.-Buscar curso");
            opcion = entrada.nextLine();
            switch (opcion) {
                case "1":
                    insertarCurso(db);
                    break;
                case "2":
                    buscarCursoNombre(db);
                    break;
            }
        } while (!opcion.equals("s"));
    }

    public static void insertarCurso(MongoDatabase db) {
        Scanner entrada = new Scanner(System.in);
        MongoCollection<Document> coleccionCursos = db.getCollection("cursos");
        MongoCollection<Document> coleccionAlumnos = db.getCollection("alumnos");
        MongoCollection<Document> coleccionMatriculas
                = db.getCollection("matriculas");

        coleccionAlumnos.createIndex(Indexes.descending("codigo"),
                new IndexOptions().unique(true));

        System.out.println("Código: ");
        String codigoCurso = entrada.nextLine();
        System.out.println("Nombre: ");
        String nombre = entrada.nextLine();

        System.out.println("Introduce fecha de inicio del curso (yyyy/mm/dd)");
        String fechaInicio = entrada.nextLine();
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Date inicio = null;
        try {
            inicio = format.parse(fechaInicio);
        } catch (ParseException ex) {
        }

        Document docCurso = new Document();
        docCurso.append("codigo", codigoCurso)
                .append("nombre", nombre)
                .append("inicio", inicio);
        coleccionCursos.insertOne(docCurso);

        System.out.println("Número de alumnos; ");
        int numeroAlumnos = Integer.parseInt(entrada.nextLine());

        for (int i = 0; i < numeroAlumnos; i++) {
            System.out.println("Código del alumno");
            String codigoAlumno = entrada.nextLine();
            System.out.println("Nombre del alumno");
            String nombreAlumno = entrada.nextLine();

            Document docAlumno = new Document();
            docAlumno.append("codigo", codigoAlumno)
                    .append("nombre", nombreAlumno);
            try {
                coleccionAlumnos.insertOne(docAlumno);
            } catch (MongoWriteException e) {
                System.out.println("Ya existe el alumno");
            }

            Document docMatricula = new Document();
            docMatricula.append("cod_alumno", codigoAlumno);
            docMatricula.append("cod_curso", codigoCurso);
            coleccionMatriculas.insertOne(docMatricula);

        }

    }

    public static void buscarCursoNombre(MongoDatabase db) {
        Scanner entrada = new Scanner(System.in);

        System.out.println("Nombre del curso: ");
        String nombre = entrada.nextLine();
        MongoCollection<Document> coleccionCursos = db.getCollection("cursos");
        MongoCollection<Document> coleccionAlumnos = db.getCollection("alumnos");
        MongoCollection<Document> coleccionMatriculas
                = db.getCollection("matriculas");

        for (Document docCurso : coleccionCursos.
                find(Filters.regex("nombre", nombre))) {

            String codigoCurso = docCurso.getString("codigo");
            System.out.println("Curso: ");
            System.out.println(docCurso.toJson());
            System.out.println("Alumnos: ");

            for (Document docMatricula : coleccionMatriculas
                    .find(Filters.eq("cod_curso", codigoCurso))) {
                String codigoAlumno = docMatricula.getString("cod_alumno");

                for (Document docAlumno : coleccionAlumnos
                        .find(Filters.eq("codigo", codigoAlumno))) {
                    System.out.println(docAlumno.toJson());
                }

            }
        }

    }
}
