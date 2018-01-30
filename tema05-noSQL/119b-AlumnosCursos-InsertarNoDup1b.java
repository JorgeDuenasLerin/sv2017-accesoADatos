/*Crea una versión mejorada de la academia2, que compruebe si existe un 
dato antes de añadir, para evitar duplicados.*/

//Alexandra Sanchez
package alumnoscursos;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.util.JSON;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;

public class AlumnosCursos {

    static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        //para quitar los mensajitos de informacion.. que molestan
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        MongoClient cliente = new MongoClient();
        MongoDatabase database = cliente.getDatabase("academia2");
        MongoCollection<Document> curso = database.getCollection("cursos");
        MongoCollection<Document> alumno = database.getCollection("alumnos");
        MongoCollection<Document> matricula = database.getCollection("matricula");
        boolean salir = true;
        String opcion = "";
        do {
            System.out.println("MENU");
            System.out.println("1.- Añadir curso");
            System.out.println("2.- Buscar curso por nombre");
            //System.out.println("3.- Buscar curso por alumno");
            //System.out.println("4.- Cambiar nombre curso (buscando por su codigo)");
            //System.out.println("5.- Cambiar nombre alumno (buscando por codigo alumno)");
            System.out.println("S.- Salir");
            opcion = teclado.nextLine();

            switch (opcion) {
                case "1":
                    System.out.print("Dime codigo curso: ");
                    String codCurso = teclado.nextLine();
                    System.out.println("Dime nombre del curso: ");
                    String nombreCurso = teclado.nextLine();
                    System.out.print("Dime fecha de inicio curso: (yyyy/mm/dd)");
                    String fecha = teclado.nextLine();
                    Date fechaIni = new Date(fecha);
                    //curso
                    Document docCurs = new Document();
                    docCurs.append("codigo", codCurso);
                    docCurs.append("nombre", nombreCurso);
                    docCurs.append("fecha", fechaIni);
                    curso.insertOne(docCurs);

                    System.out.print("Cantidad de alumnos: ");
                    String cantidad = teclado.nextLine();

                    int i = Integer.parseInt(cantidad);
                    for (int alu = 0; alu < i; alu++) {
                        System.out.println("Nombre y apellidos del alumno: ");
                        String nomAlu = teclado.nextLine();
                        System.out.println("Codigo del alumno");
                        String codAlu = teclado.nextLine();

                        //alumno
                        //comprobamos que no exista ya
                        // Con booleano de control
                        boolean existe = false;
                        for (Document docFind : curso.find(
							new Document("alumno.codigo", codAlu)))
                        {
							existe = true;
						}
						if ( ! existe )
						{
                            Document docAlu = new Document();
                            docAlu.append("codigo", codAlu);
                            docAlu.append("nombre", nomAlu);
                            alumno.insertOne(docAlu);
                        }

                        //matricula
                        Document docMatri = new Document();
                        docMatri.append("cod_alumno", codAlu);
                        docMatri.append("cod_curso", codCurso);
                        matricula.insertOne(docMatri);
                    }

                    break;

                case "2":
                    System.out.print("Nombre Curso: ");
                    String cursoFind = teclado.nextLine();

                    // Creamos un filtro.
                    BasicDBObject filtro = (BasicDBObject) JSON.parse(
                            "{nombre: '" + cursoFind + "'}"
                    );
                    for (Document cur : curso.find(filtro)) {
                        System.out.println(cur.toJson());
                    }
                    break;

                case "3":
                    System.out.print("Nombre alumno: ");
                    String nomBusco = teclado.nextLine();

                    for (Document cur : curso.find(Filters.regex("alumno.nombre", nomBusco))) {
                        System.out.println(cur.toJson());
                    }
                    break;
                case "4":
                    System.out.print("codigo de curso a modificar: ");
                    String cod = teclado.nextLine();
                    System.out.println("nombre nuevo del curso: ");

                    String nombreNuevo = teclado.nextLine();

                    curso.updateOne(
                            Filters.eq("codigo", cod),
                            Updates.set("nombre", nombreNuevo));

                    System.out.println("");
                    break;
                case "5":
                    System.out.println("Codigo del alumno: ");
                    String codAlu = teclado.nextLine();
                    System.out.println("Nombre nuevo del alumno: ");
                    String nomNuevo = teclado.nextLine();

                    curso.updateMany(
                            Filters.eq("alumno.codigo", codAlu),
                            Updates.set("alumno.nombre", nomNuevo));
                    break;

                case "S":
                case "s":
                    System.out.println("Saliendo...\n\n");
                    salir = false;
                    break;

                default:
                    System.out.println("Opcion incorrecta\n\n");
                    break;
            }
        } while (salir);
    }
}
