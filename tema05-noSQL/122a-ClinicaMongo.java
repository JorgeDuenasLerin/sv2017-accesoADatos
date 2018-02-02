package clinicamongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;

/**
 *
 * @author antonio Defez
 */
public class ClinicaMongo {

    public static void main(String[] args) {
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        MongoClient cliente = new MongoClient();
        MongoDatabase db = cliente.getDatabase("clinica");
        MongoCollection<Document> coleccion_citas = db.getCollection("citas");

        boolean salida = false;
        Scanner entrada = new Scanner(System.in);
        do {
            mostrarMenu();
            String opcion = entrada.nextLine();
            switch (opcion) {
                case "1":
                    insertarCita(coleccion_citas);
                    break;

                case "2":
                    buscarCita(coleccion_citas);
                    break;

                case "0":
                    salida = true;
                    break;
            }
        } while (!salida);

    }

    private static void mostrarMenu() {
        System.out.println("1.  Insertar cita");
        System.out.println("2.  Buscar cita");
        System.out.println("0.  salir");
    }

    private static void insertarCita(MongoCollection<Document> coleccion_citas) {

        Date fecha = new Date();
        //obteniendo elementos de la cita
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introuzca el dni del paciente");
        String dni = entrada.nextLine();
        System.out.println("Introuduzca nombre y apellidos del cliente");
        String nombre = entrada.nextLine();
        System.out.println("Introduzca el nombre del medico");
        String doctor_nombre = entrada.nextLine();
        System.out.println("Introduzca el numero de colegiado");
        String doctor_numero = entrada.nextLine();
        System.out.println("Introduzca el motivo de la consulta");
        String motivo = entrada.nextLine();
        System.out.println("Introduzca el diagnostico");
        String diagnostico = entrada.nextLine();
        System.out.println("Es una urgencia ?(Si/No)");
        boolean esUrgencia;
        String urgencia = entrada.nextLine();
        if (!urgencia.toLowerCase().equals("si")) {
            esUrgencia = false;
        } else {
            esUrgencia = true;
        }

        //guardando la doctor
        Document doc_doctor = new Document();
        doc_doctor.append("nombre", doctor_nombre);
        doc_doctor.append("num_colegiado", doctor_numero);

        //guardando la cita
        Document doc_cita = new Document();
        doc_cita.append("dni", dni);
        doc_cita.append("nombre", nombre);
        doc_cita.append("medico", doc_doctor);
        doc_cita.append("motivo", motivo);
        doc_cita.append("diagnostico", diagnostico);
        doc_cita.append("esUrgencia", esUrgencia);
        if (esUrgencia == true) {
            doc_cita.append("fecha", fecha);
        }

        coleccion_citas.insertOne(doc_cita);

    }

    //buscamos por el nombre del paciente
    private static void buscarCita(MongoCollection<Document> coleccion_citas) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduzca el nombre del paciente");
        String nombre= entrada.nextLine();
        int cont=0;
        for (Document doc : coleccion_citas.find(Filters.regex("nombre",
                nombre))) {
            System.out.println(doc.toJson());
            cont++;
        }
        if(cont==0)
        {
            System.out.println("No se encontro la cita del cliente");
        }
        
    }

}
