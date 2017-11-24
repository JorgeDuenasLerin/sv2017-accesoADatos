package relojesbd;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import java.util.List;
import java.util.Scanner;
import com.db4o.query.Predicate;

public class RelojesBD {

    public static void main(String[] args) {
        ObjectContainer db = null;

        System.out.println("1.- Añadir reloj");
        System.out.println("2.- Mostrar reloj por marca");
        System.out.println("3.- Modificar reloj");
        System.out.println("4.- Buscar reloj por modelo");
        System.out.println("5.- Buscar reloj por precio");
        Scanner sc = new Scanner(System.in);
        String opc = sc.nextLine();
        switch (opc) {
            case "1":
                try {
                    db = Db4o.openFile("relojes.dat");

                    System.out.println("Introduzca marca, modelo y precio: ");
                    String marca = sc.nextLine();
                    String modelo = sc.nextLine();
                    double precio = sc.nextDouble();
                    sc.nextLine();
                    if (db.get(new Reloj(marca, modelo, 0)).hasNext()
                            == false) {
                        db.set(new Reloj(marca, modelo, precio));
                        db.commit();
                        System.out.println("Dato añadido.");
                    } else {
                        System.out.println("Dato repetido, no añadido.");
                    }
                } catch (Exception e) {
                    System.out.println("Error");
                } finally {
                    if (db != null) {
                        db.close();
                    }
                }
                break;
            case "2":
                try {
                    System.out.println("Introduzca la marca del reloj: ");
                    String marca = sc.nextLine();
                    db = (ObjectContainer) Db4o.openFile("relojes.dat");
                    ObjectSet relojes = db.get(new Reloj(marca, null, 0));
                    while (relojes.hasNext()) {
                        System.out.println(relojes.next());
                    }

                } catch (Exception e) {
                    System.out.println("Algun error con el trasto este del 2008.");
                } finally {
                    if (db != null) {
                        db.close();
                    }
                }
                break;
            case "3":
                System.out.println("Introduce la marca y el modelo del "
                        + "reloj a modificar: ");
                String marca = sc.nextLine();
                String modelo = sc.nextLine();
                System.out.println("¿Que dato quieres cambiar?");
                System.out.println("1.- Marca // 2.- Modelo// 3.- Precio");
                String cambio = sc.nextLine();
                System.out.println("Introduzca el nuevo valor: ");
                String nuevoDato = sc.nextLine();
                try {

                    db = (ObjectContainer) Db4o.openFile("relojes.dat");
                    ObjectSet lista = db.get(new Reloj(marca, modelo, 0));
                    if (lista.hasNext()) {
                        Reloj reloj = (Reloj) lista.next();
                        switch (cambio) {
                            case "1":
                                reloj.setMarca(nuevoDato);
                                break;
                            case "2":
                                reloj.setModelo(nuevoDato);
                                break;
                            case "3":
                                reloj.setPrecio(Double.parseDouble(nuevoDato));
                                break;
                        }
                        db.set(reloj);
                        db.commit();
                    }
                } catch (Exception e) {
                    System.out.println("Algun error con el trasto este del 2008.");
                } finally {
                    if (db != null) {
                        db.close();
                    }
                }
                break;
            case "4":
                try {
                    db = (ObjectContainer) Db4o.openFile("relojes.dat");

                    System.out.println("Introduzca el modelo del reloj: ");
                    String modeloAprox = sc.nextLine();
                    List<Reloj> relojes = db.query(new Predicate<Reloj>() {
                        @Override
                        public boolean match(Reloj rel) {
                            return rel.getModelo().toLowerCase()
                                    .contains(modeloAprox.toLowerCase());
                        }
                    });
                    relojes.forEach((r) -> {
                        System.out.println(r.toString());
                    });

                } catch (Exception e) {
                    System.out.println("Algun error con el trasto este del 2008.");
                } finally {
                    if (db != null) {
                        db.close();
                    }
                }

                break;

            case "5":
                try {
                    db = (ObjectContainer) Db4o.openFile("relojes.dat");

                    System.out.println("Introduzca el precio maximo "
                            + "y minimo del reloj: ");
                    double max = sc.nextDouble();
                    double min = sc.nextDouble();
                    List<Reloj> relojes = db.query(new Predicate<Reloj>() {
                        @Override
                        public boolean match(Reloj rel) {
                            return rel.getPrecio() >= min
                                    && rel.getPrecio() <= max;
                        }
                    });
                    relojes.forEach((r) -> {
                        System.out.println(r.toString());
                    });

                } catch (Exception e) {
                    System.out.println("Algun error con el trasto este del 2008.");
                } finally {
                    if (db != null) {
                        db.close();
                    }
                }
                break;
        }
    }
}
