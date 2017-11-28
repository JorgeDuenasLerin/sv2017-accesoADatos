/*
Antonio Defez
 */
package estudiosestudiantes;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import java.util.List;
import java.util.Scanner;

public class EstudiosEstudiantes {

    public static void main(String[] args) {
        ObjectContainer db = null;
        Scanner entrada = new Scanner(System.in);

        try {
            db = Db4o.openFile("EstudiantesEstudios.dat");
            boolean salida = false;
            do {
                MostrarMenu();
                System.out.println("Introduce la opcion");
                String opcion = entrada.nextLine();

                switch (opcion) {
                    case "1":
                        insertarEstudiante(db);
                        break;
                    case "2":
                        insertarEstudios(db);
                        break;
                    case "3":
                        matricularEstudianteEnEstudio(db);
                        break;
                    case "4":
                        verDetalleEstudio(db);
                        break;
                    case "5":
                        verDetalleEstudiante(db);
                        break;
                    case "6":
                        salida = true;
                        break;
                }

            } while (!salida);

            System.out.println("Fin de programa");
        } finally {
            if (db != null) {
                System.out.println("Todo ok1!!");
                db.close();
            }
        }
    }

    private static void MostrarMenu() {
        System.out.println("1.Añadir estudiante");
        System.out.println("2.Añadir estudio");
        System.out.println("3.Matricular alumno en estudio");
        System.out.println("4.Ver detalles de un estudio");
        System.out.println("5.Ver detalles de un alumno");
        System.out.println("6.Salir");

    }

    private static void mostrarAlumnos(ObjectContainer db) {
        ObjectSet estudiantes = db.get(new Estudiante(null, 0));
        Estudiante tmp = null;
        if (!estudiantes.isEmpty()) {
            System.out.println("---------------------------------------------");
            System.out.println("Alumnos registrados en el centro");

            while (estudiantes.hasNext()) {
                tmp = (Estudiante) estudiantes.next();
                System.out.println(tmp);
            }
        }
    }

    private static void mostrarEstudios(ObjectContainer db) {
        ObjectSet estudios = db.get(new Estudios(null, 0));
        Estudios tmp = null;
        if (!estudios.isEmpty()) {
            System.out.println("---------------------------------------------");
            System.out.println("Estudios registrados en el centro");

            while (estudios.hasNext()) {
                tmp = (Estudios) estudios.next();
                System.out.println(tmp);
            }
        }
    }

    private static void insertarEstudiante(ObjectContainer db) {
        Scanner sc = new Scanner(System.in);

        mostrarAlumnos(db);
        System.out.println("---------------------------------------------");
        System.out.println("Introduciendo nuevo Estudiante");

        System.out.println("Introduce el nombre del estudiante");
        String nombre = sc.nextLine();
        System.out.println("Introduce el codigo del estudiante");
        int codigo = sc.nextInt();
        Estudiante tmp = new Estudiante(nombre, codigo);
        if (db.get(new Estudiante(null, codigo)).hasNext() == false) {
            db.set(tmp);
            db.commit();
            System.out.println("Se ha añadido el estudiante " + tmp.getNombre());
        } else {
            System.out.println("Estudiante ya existente");
        }
    }

    private static void insertarEstudios(ObjectContainer db) {
        Scanner sc = new Scanner(System.in);
        mostrarEstudios(db);
        System.out.println("---------------------------------------------");
        System.out.println("Introduciendo nuevo Estudios");

        System.out.println("Introduce el nombre del Estudios");
        String nombre = sc.nextLine();
        System.out.println("Introduce el codigo del Estudios");
        int codigo = sc.nextInt();
        Estudios tmp = new Estudios(nombre, codigo);
        if (db.get(new Estudios(null, codigo)).hasNext() == false) {
            db.set(tmp);
            db.commit();
            System.out.println("Se ha añadido el Estudio " + tmp.getNombre());
        } else {
            System.out.println("Estudios ya existente");
        }
    }

    private static Estudiante ObtenerEstudiante(ObjectContainer db) {
        Scanner entrada = new Scanner(System.in);

        System.out.println("Matriculando a estudiante en un nuevo estudio");
        System.out.println("---------------------------------------------");
        System.out.println("Introduciendo los datos del alumno");
        System.out.println("---------------------------------------------");
        System.out.println("Introduce el codigo del estudiante");
        int codigo = entrada.nextInt();
        entrada.nextLine();//limpio buffer
        ObjectSet estudiantes = db.get(new Estudiante(null, codigo));
        Estudiante tmp_estudiante = null;
        while (estudiantes.hasNext()) {
            tmp_estudiante = (Estudiante) estudiantes.next();
        }
        return tmp_estudiante;
    }

    public static Estudios ObtenerEstudios(ObjectContainer db) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("---------------------------------------------");
        System.out.println("Introuduciendo los datos del estudios");
        System.out.println("---------------------------------------------");
        System.out.println("Introudce el codigo del estudio");
        int codigo_estudio = entrada.nextInt();
        ObjectSet estudios = db.get(new Estudios(null, codigo_estudio));
        Estudios tmp_estudio = null;
        while (estudios.hasNext()) {
            tmp_estudio = (Estudios) estudios.next();
        }
        return tmp_estudio;
    }

    private static void matricularEstudianteEnEstudio(ObjectContainer db) {
        Scanner entrada = new Scanner(System.in);
        mostrarAlumnos(db);
        Estudiante tmp_estudiante = ObtenerEstudiante(db);
        if (tmp_estudiante == null) {
            System.out.println("El alumno no existe");
        } else {
            System.out.println("Datos del alumno");
            System.out.println(tmp_estudiante);
            mostrarEstudios(db);
            Estudios tmp_estudio = ObtenerEstudios(db);
            if (tmp_estudio == null) {
                System.out.println("La operacion no ha sido posible de realizar");
            } else {
                if (tmp_estudio.estaMatriculado(tmp_estudiante.getCodigo())
                        || tmp_estudiante.estaMatriculado(tmp_estudio.getCodigo())) {
                    System.out.println("No se puede volver a matricular a un "
                            + "alumno ya matriculado");
                } else {
                    tmp_estudio.AddEstudiante(tmp_estudiante);
                    tmp_estudiante.AddEstudio(tmp_estudio);
                    db.set(tmp_estudio);
                    db.set(tmp_estudiante);
                    db.commit();
                    System.out.println("Se ha matriculado correctamente a "
                            + tmp_estudiante.getNombre() + " en el estudio "
                            + tmp_estudio.getNombre());
                }
            }
        }

    }

    private static void verDetalleEstudio(ObjectContainer db) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduce el codigo del estudio");
        int codigo = entrada.nextInt();
        ObjectSet estudios = db.get(new Estudios(null, codigo));
        Estudios estudioElegido = null;
        while (estudios.hasNext()) {
            estudioElegido = (Estudios) estudios.next();
        }
        if (estudioElegido != null) {
            System.out.println("---------------------------------------------");
            System.out.println("Detalles del estudio " + estudioElegido.getNombre());
            List<Estudiante> matriculados = estudioElegido.getEstudiantes_matriculados();
            System.out.println(estudioElegido);
            System.out.println("Alumnos matriculados ");
            for (int to = 0; to < matriculados.size(); to++) {
                System.out.println(matriculados.get(to).getNombre());
            }
            System.out.println("---------------------------------------------");
        } else {
            System.out.println("El estudio no existe");
        }
    }

    private static void verDetalleEstudiante(ObjectContainer db) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduce el codigo del estudiante");
        int codigo = entrada.nextInt();
        ObjectSet alumnos = db.get(new Estudiante(null, codigo));
        Estudiante alumnotemp = null;
        while (alumnos.hasNext()) {
            alumnotemp = (Estudiante) alumnos.next();
        }
        if (alumnotemp != null) {
            System.out.println("---------------------------------------------");
            System.out.println("Detalles del estudiante " + alumnotemp.getNombre());
            List<Estudios> estudios = alumnotemp.getEstudios_realizados();
            System.out.println(alumnotemp);
            System.out.println("Estudios realizados");
            for (int to = 0; to < estudios.size(); to++) {
                System.out.println(estudios.get(to).getNombre());
            }
            System.out.println("---------------------------------------------");
        } else {
            System.out.println("El Estudiante no existe");
        }

    }

}
