//Antonio Sevila Diaz
package empresamantenimiento_simulacro;

import com.mongodb.MongoClient;
import com.mongodb.MongoCommandException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;

/**
 *
 * @author antonio
 */
public class EmpresaMantenimiento_simulacro {

    public static void anyadirIncidencia(Scanner teclado,
            MongoCollection<Document> collection_incidencias) {
        System.out.println("Introduce codigo");
        String cod = teclado.nextLine();
        System.out.println("Nombre del cliente: ");
        String nomCliente = teclado.nextLine();
        System.out.println("Teléfono del cliente: ");
        String telefono = teclado.nextLine();
        System.out.println("Dirección del cliente: ");
        String direccion = teclado.nextLine();
        System.out.println("Descripción del problema: ");
        String descripcion = teclado.nextLine();
        String especialidad;
        ArrayList<String> listaEspecialidades = new ArrayList<>();
        do {
            System.out.println("Introduce especialidad necesaria "
                    + "para solucionar el problema. (enter para terminar)");
            especialidad = teclado.nextLine();
            if( ! especialidad.equals("")){
                listaEspecialidades.add(especialidad);
            }
        } while (!especialidad.equals(""));

        Document operario = new Document()
                .append("codigo", cod)
                .append("fechahora", new Date())
                .append("nomcliente", nomCliente)
                .append("telefono", telefono)
                .append("direccion", direccion)
                .append("descripcion", descripcion)
                .append("especialidades", listaEspecialidades);
        collection_incidencias.insertOne(operario);
        System.out.println("Incidencia guardada");
    }

    public static void indicarVisita(
            MongoCollection<Document> collection_visitas, Scanner teclado,
            String codOperario) {
        System.out.println("Introduce código de la incidencia");
        String codIncidencia = teclado.nextLine();
        System.out.println("Introduce descripción de las tareas");
        String descripcion = teclado.nextLine();

        Document document = new Document()
                .append("codigooperario", codOperario)
                .append("codigoIncidencia", codIncidencia)
                .append("fechaHoraVisita", new Date())
                .append("descripcion", descripcion);
        collection_visitas.insertOne(document);
        System.out.println("Visita agregada");
    }

    public static void anyadirOperario(Scanner teclado,
            MongoCollection<Document> collection_operarios) {
        System.out.println("Introduce codigo");
        String cod = teclado.nextLine();
        System.out.println("Introduce nombre");
        String nombre = teclado.nextLine();
        String especialidad;
        ArrayList<String> listaEspecialidades = new ArrayList<>();
        do {
            System.out.println("Introduce especialidad (enter para terminar)");
            especialidad = teclado.nextLine();
            if( ! especialidad.equals("")){
                listaEspecialidades.add(especialidad);
            }
            
        } while ( ! especialidad.equals(""));

        Document operario = new Document()
                .append("codigo", cod)
                .append("nombre", nombre)
                .append("especialidades", listaEspecialidades);
        collection_operarios.insertOne(operario);
        System.out.println("Operario insertado");
    }

    public static void verOperarios(MongoCollection<Document> collection_operarios) {
        System.out.println("Mostrando operarios...");
        for (Document doc : collection_operarios.find()) {
            System.out.println(doc.toJson());
            System.out.print("Codigo: " + doc.getString("codigo")
                    + ", nombre: " + doc.getString("nombre"));
            ArrayList<String> listaEspecialidades
                    = (ArrayList) doc.get("especialidades");
            System.out.print(", especialidades: ");
            for (String especialidad : listaEspecialidades) {
                System.out.print(especialidad + ", ");
            }
            System.out.println();
        }
    }

    public static void verIncidencias(MongoCollection<Document>
            collection_incidencias) {
        System.out.println("Mostrando incidencias...");
        for (Document doc : collection_incidencias.find()) {
            System.out.print("Codigo: " + doc.getString("codigo")
                    + ", fecha y hora: " + doc.getDate("fechahora")
                    + ", nombre cliente: " + doc.getString("nomcliente")
                    + ", telefono: " + doc.getString("telefono")
                    + ", direccion: " + doc.getString("direccion")
                    + ", descripción del problema: "
                    + doc.getString("descripcion"));
            ArrayList<String> listaEspecialidades
                    = (ArrayList) doc.get("especialidades");
            System.out.print(", especialidades: ");
            for (String especialidad : listaEspecialidades) {
                System.out.print(especialidad + ", ");
            }
            System.out.println();
        }
    }
    
    public static void marcarIncidenciaSolucionada(Scanner teclado,
            MongoCollection<Document> collection_incidencias){
        System.out.println("Introduce código de incidencia solucionada");
        String codIncidencia = teclado.nextLine();
        
        collection_incidencias.updateOne(Filters.and(
                    Filters.eq("codigo",codIncidencia)
                    , Filters.exists("fechaFinalizacion", false))
                , Updates.set("fechaFinalizacion", new Date()));
    }

    public static void menuAdmin(Scanner teclado,
            MongoCollection<Document> collection_operarios,
            MongoCollection<Document> collection_incidencias) {
        String opcion;
        do {
            System.out.println("1. Añadir operarios");
            System.out.println("2. Ver operarios");
            System.out.println("3. Añadir incidencias");
            System.out.println("4. Ver incidencias");
            System.out.println("5. Marcar incidencia como solucionada");
            System.out.println("S. Salir");
            opcion = teclado.nextLine();

            switch (opcion) {
                case "1":
                    anyadirOperario(teclado, collection_operarios);
                    break;
                case "2":
                    verOperarios(collection_operarios);
                    break;
                case "3":
                    anyadirIncidencia(teclado, collection_incidencias);
                    break;
                case "4":
                    verIncidencias(collection_incidencias);
                    break;
                case "5":
                    marcarIncidenciaSolucionada(teclado, collection_incidencias);
                    break;
                case "s":
                case "S":
                    System.out.println("Adios!");
                    opcion = opcion.toLowerCase();
                    break;
            }

        } while (!opcion.equals("s"));
    }

    public static void menuOperario(Document document, Scanner teclado,
            MongoCollection<Document> collection_incidencias,
            MongoCollection<Document> collection_visitas) {
        String opcion;
        do {
            System.out.println("1. Ver lista de incidencias no solucionadas");
            System.out.println("2. Indicar visita");
            System.out.println("S. Salir");
            opcion = teclado.nextLine();

            switch (opcion) {
                case "1":
                    System.out.println("Mostrando incidencias no"
                            + " solucionadas...");
                    for (Document doc : collection_incidencias.find(
                            Filters.exists("fechaFinalizacion", false))) {
                        System.out.print("Codigo" + doc.getString("codigo")
                                + ", fecha y hora: " + doc.getDate("fechahora")
                                + ", nombre cliente: " + doc.getString("nomcliente")
                                + ", telefono: " + doc.getString("telefono")
                                + ", direccion: " + doc.getString("direccion")
                                + ", descripción del problema: "
                                + doc.getString("descripcion"));
                        ArrayList<String> listaEspecialidades
                                = (ArrayList) doc.get("especialidades");
                        System.out.print(", especialidades: ");
                        for (String especialidad : listaEspecialidades) {
                            System.out.print(especialidad + ", ");
                        }
                        System.out.println();
                    }
                    break;
                case "2":
                    indicarVisita(collection_visitas, teclado,
                            document.getString("codigo"));
                    break;
                case "s":
                case "S":
                    System.out.println("Adios!");
                    opcion = opcion.toLowerCase();
                    break;
            }

        } while (!opcion.equals("s"));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);
        Scanner teclado = new Scanner(System.in);
        String opcion;
        String usuario;
        MongoClient cliente = new MongoClient();
        try {
            MongoDatabase db = cliente.getDatabase("empMantenimiento");
            MongoCollection<Document> collection_operarios = db.
                    getCollection("operarios");
            MongoCollection<Document> collection_incidencias = db.
                    getCollection("incidencias");
            MongoCollection<Document> collection_visitas = db.
                    getCollection("visitas");

            System.out.println("Introduce usuario");
            usuario = teclado.nextLine();

            if (usuario.equals("admin")) {
                System.out.println("Introduce contraseña");
                String contrasenya = teclado.nextLine();

                if (contrasenya.equals("1234")) {
                    menuAdmin(teclado, collection_operarios,
                            collection_incidencias);
                } else {
                    System.out.println("Contraseña incorrecta");
                }
            } else {

                MongoCursor<Document> cursor = collection_operarios.find(
                        Filters.eq("codigo", usuario)).iterator();
                if (cursor.hasNext()) {
                    menuOperario(cursor.next(), teclado, collection_incidencias,
                            collection_visitas);
                }

            }
        } catch (MongoCommandException e) {
            System.err.println(e.getErrorMessage());
        }
        System.out.println("Desconectando de MongoDB.");
        cliente.close();

    }

}
