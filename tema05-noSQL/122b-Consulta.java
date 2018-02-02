//Mario Belso Ros - Consulta
package consulta;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.IndexOptions;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;

public class Consulta {

    // use consulta  ..... en CMD.
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        MongoClient cliente = new MongoClient();
        MongoDatabase db = cliente.getDatabase("consulta");
        MongoCollection<Document> coleccion_consultas = db.getCollection("consulta");
        boolean salida = false;
        do {
            mostrarMenu();
            String opcion = sc.nextLine();
            switch (opcion) {
                case "1":
                    insertarConsulta(coleccion_consultas);
                    break;
                case "2":
                    buscarConsulta(coleccion_consultas);
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
        System.out.println("1.- Insertar consulta");
        System.out.println("2.- Buscar consulta.");
        System.out.println("0.   Salir");

    }

    private static void insertarConsulta(
            MongoCollection<Document> coleccion_consultas) {
        System.out.println("Introduce el DNI del paciente: ");
        String dni = sc.nextLine();
        System.out.println("Introduce el nombre y apellidos del paciente: ");
        String nombreApe = sc.nextLine();
        Date fecha = new Date(); //fecha actual.
        System.out.println("Introduzca el motivo de la consulta: ");
        String motivo = sc.nextLine();
        System.out.println("Introduzca el diagnostico: ");
        String diagnostico = sc.nextLine();
        System.out.println("Es una cita programada? (s/n)");
        String prog = sc.nextLine().toLowerCase();
        boolean programado = false;
        String fechaTexto = null;
        if (prog.equals("s")) {
            programado = true;
            System.out.println("Introduzca la fecha de la siguiente visita: "
                    + "(yyyy-MM-dd HH:mm:ss)");
            fechaTexto = sc.nextLine();
        } else if (prog.equals("n")) {
            programado = false;
        }

        System.out.println("Introduzca el nombre del medico: ");
        String nomMedico = sc.nextLine();
        System.out.println("Introduzca el Nº colegiado del medico: ");
        String colegiado = sc.nextLine();

        Document doc_consulta = new Document();
        doc_consulta.append("dni", dni);
        doc_consulta.append("nombreCompleto", nombreApe);
        doc_consulta.append("fechaConsulta", fecha);
        doc_consulta.append("motivo", motivo);
        doc_consulta.append("diagnostico", diagnostico);
        doc_consulta.append("programado", programado);
        doc_consulta.append("medico", new Document()
                .append("nombre", nomMedico).append("nColegiado", colegiado));
        if (programado) {
            doc_consulta.append("fechaConsulta", fechaTexto);
        }
        coleccion_consultas.insertOne(doc_consulta);
    }

    private static void buscarConsulta(
            MongoCollection<Document> coleccion_consultas) {
        System.out.println("Introduzca el nombre del paciente: ");
        String nombreApe = sc.nextLine();

        for (Document docConsulta : coleccion_consultas.find(Filters.regex(
                "nombreCompleto", nombreApe))) {
            System.out.println(docConsulta.toJson());
        }
        
    }

}
