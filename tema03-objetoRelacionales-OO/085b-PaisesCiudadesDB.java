package paises.ciudadesdb;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.DatabaseClosedException;
import com.db4o.ext.DatabaseFileLockedException;
import com.db4o.ext.DatabaseReadOnlyException;
import com.db4o.ext.Db4oIOException;
import com.db4o.ext.IncompatibleFileFormatException;
import com.db4o.ext.OldFormatException;
import java.util.Scanner;

public class PaisesCiudadesDB {

    public static void main(String[] args) {
        ObjectContainer db = null;

        System.out.println("1.- Añadir pais");
        System.out.println("2.- Añadir ciudad");
        System.out.println("3.- Mostrar ciudades");
        System.out.println("4.- Mostrar todas las ciudades");
        Scanner sc = new Scanner(System.in);
        String opc = sc.nextLine();
        switch (opc) {
            case "1":
                try {
                    db = Db4o.openFile("paises-ciudades.dat");

                    System.out.println("Introduzca nombre y numero de habitantes"
                            + " del pais: ");
                    String nombre = sc.nextLine();
                    int nHabitantes = sc.nextInt();
                    sc.nextLine();
                    if (db.get(new Pais(nombre, nHabitantes)).hasNext()
                            == false) {
                        db.set(new Pais(nombre, nHabitantes));
                        db.commit();
                        System.out.println("Dato añadido.");
                    } else {
                        System.out.println("Dato repetido, no añadido.");
                    }
                } catch (DatabaseClosedException | DatabaseFileLockedException | DatabaseReadOnlyException | Db4oIOException | IncompatibleFileFormatException | OldFormatException e) {
                    System.out.println("Error");
                } finally {
                    if (db != null) {
                        db.close();
                    }
                }
                break;
            case "2":
                try {
                    db = Db4o.openFile("paises-ciudades.dat");
                    System.out.println("Introduzca nombre, numero de habitantes"
                            + " y pais que la contiene: ");
                    String nombre = sc.nextLine();
                    int nHabitantes = sc.nextInt();
                    sc.nextLine();
                    String pais = sc.nextLine();
                    if (db.get(new Ciudad(nombre, 0, null)).hasNext()
                            == false
                            && db.get(new Pais(pais, 0)).hasNext() == true) {
                        ObjectSet lista = db.get(new Pais(pais,0));
                        Pais p = (Pais)lista.next();
                        db.set(new Ciudad(nombre, nHabitantes, p));
                        db.commit();
                        System.out.println("Dato añadido.");
                    } else {
                        System.out.println("Dato repetido, no añadido.");
                    }
                } catch (DatabaseClosedException | DatabaseFileLockedException | DatabaseReadOnlyException | Db4oIOException | IncompatibleFileFormatException | OldFormatException e) {
                    System.out.println("Algun error con el trasto este del 2008.");
                } finally {
                    if (db != null) {
                        db.close();
                    }
                }
                break;
            case "3":
                try{
                System.out.println("Introduzca el nombre de la ciudad: ");
                    String nombre = sc.nextLine();
                    db = (ObjectContainer) Db4o.openFile("paises-ciudades.dat");
                    ObjectSet ciudades = db.get(new Ciudad(nombre, 0, null));
                    while (ciudades.hasNext()) {
                        System.out.println(ciudades.next());
                    }
                } catch (DatabaseClosedException | DatabaseFileLockedException | DatabaseReadOnlyException | Db4oIOException | IncompatibleFileFormatException | OldFormatException e) {
                    System.out.println("Algun error con el trasto este del 2008.");
                } finally {
                    if (db != null) {
                        db.close();
                    }
                }
                break;
            case "4":
                try{
                    db = (ObjectContainer) Db4o.openFile("paises-ciudades.dat");
                    ObjectSet ciudades = db.get(new Ciudad(null , 0, null));
                    while (ciudades.hasNext()) {
                        System.out.println(ciudades.next());
                    }
                } catch (DatabaseClosedException | DatabaseFileLockedException | DatabaseReadOnlyException | Db4oIOException | IncompatibleFileFormatException | OldFormatException e) {
                    System.out.println("Algun error con el trasto este del 2008.");
                } finally {
                    if (db != null) {
                        db.close();
                    }
                }

        }
    }
}
