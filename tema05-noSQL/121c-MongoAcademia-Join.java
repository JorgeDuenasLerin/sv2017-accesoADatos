/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mongoacademia;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.IndexOptions;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;

/**
 *
 * @author Azahara
 */
public class MongoAcademia {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        MongoClient cliente = new MongoClient();
        MongoDatabase db = cliente.getDatabase("academia2");

        MongoCollection<Document> coleccionCurso = db.getCollection("cursos");// se importa el bson
        MongoCollection<Document> coleccionAlumno = db.getCollection("alumno");
        MongoCollection<Document> coleccionMatricula = db.getCollection("matricula");

        //coleccionAlumno.createIndex(new BasicDBObject("codigo", 1), new IndexOptions().unique(true));  // create

        Scanner sc = new Scanner(System.in);
        String opcion = "";
        do {
            System.out.println("1.ver todos los cursos ,alumnos y matriculas");
            System.out.println("2. añadir curso");
            System.out.println("3.ver informacion de un curso");
            System.out.println("0. Salir");
            opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    System.out.println("CURSOS");
                    for (Document doc : coleccionCurso.find()) {
                        System.out.println(doc.toJson());
                    }
                    System.out.println("ALUMNOS");
                    for (Document doc : coleccionAlumno.find()) {
                        System.out.println(doc.toJson());
                    }
                    System.out.println("MATRICULAS");
                    for (Document doc : coleccionMatricula.find()) {
                        System.out.println(doc.toJson());
                    }
                    break;

                case "2":
                    Document doc = new Document();
                    System.out.println("¿codigo?");
                    String codigo = sc.nextLine();
                    System.out.println("¿nombre?");
                    String nombre = sc.nextLine();
                    System.out.println("¿fecha de inicio?");
                    String fecha = sc.nextLine();
                    doc.append("cod", codigo);
                    doc.append("nombre", nombre);
                    doc.append("fecha", fecha);
                    coleccionCurso.insertOne(doc);

                    System.out.println("¿cuantos alumnos?");
                    int nAlumnos = sc.nextInt();
                    sc.nextLine();

                    for (int i = 0; i < nAlumnos; i++) {
                        Document doc1 = new Document();
                        System.out.println("¿codigo?");
                        String codigoA = sc.nextLine();
                        System.out.println("¿nombre y apellidos?");
                        String nombreA = sc.nextLine();
                        //compruebo el alumno
                        boolean existe = false;
                        for (Document docAl : coleccionAlumno.find(new Document("codigo", codigoA))) {
                            existe = true;
                        }

                        if (!existe) {
                            doc1.append("nombreApellidos", nombreA);
                            doc1.append("codigo", codigo);
                            coleccionAlumno.insertOne(doc1);
                        }

                        //la Matricula
                        Document doc2 = new Document();
                        doc2.append("cod_alumno", codigoA);
                        doc2.append("cod_curso", codigo);
                        coleccionMatricula.insertOne(doc2);

                    }

                    break;

                case "3":
                    System.out.println("Nombre del curso");
                    nombre = sc.nextLine();
                    codigo = "";
                    String codMat = "";
                    String codAL = "";
                    for (Document docCur : coleccionCurso.find(Filters.regex("nombre", nombre))) {
                        codigo = docCur.getString("cod");
                        System.out.println("Curso: "+docCur.getString("nombre") );
                        System.out.println("Fecha de inicio: "+docCur.getString("fecha") );
                        System.out.println("Alumnos:");
                        for (Document docMat : coleccionMatricula.find(Filters.eq("cod_curso", codigo))) {
                            codMat = docMat.getString("cod_alumno");
                            for (Document docAl : coleccionAlumno.find(new Document("codigo", codMat))) {
                                System.out.println(docAl.getString("nombreApellidos"));
                            }
                        }
                        System.out.println("");
                    }

                    break;
                case "0":
                    System.out.println("Adios");
                    break;

            }
        } while (!opcion.equals("0"));

    }

}

