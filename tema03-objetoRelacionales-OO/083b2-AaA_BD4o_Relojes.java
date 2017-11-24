//Creacion base de datos para objetos -> Relojes
//Antonio Sevila
package aaa_bd4o_relojes;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import java.util.List;
import java.util.Scanner;
import com.db4o.query.Predicate;

/**
 *
 * @author antonio
 */
public class AaA_BD4o_Relojes {

    /**
     * @param args the command line arguments
     */
    public static Reloj cambiarDatos(Reloj reloj) {
        Scanner teclado = new Scanner(System.in);
        System.out.println(reloj);
        System.out.println("¿Quieres cambiar la marca?"
                + " (Yes/No)");
        String decision = teclado.nextLine().toLowerCase();
        if (decision.equals("yes")) {
            System.out.println("Introduce nueva marca: ");
            String nuevaMarca = teclado.nextLine();
            reloj.setMarca(nuevaMarca);
            System.out.println("Cambiado!");
        }

        System.out.println("¿Quieres cambiar el modelo?"
                + " (Yes/No)");
        decision = teclado.nextLine().toLowerCase();
        if (decision.equals("yes")) {
            System.out.println("Introduce nuevo modelo: ");
            String nuevoModelo = teclado.nextLine();
            reloj.setModelo(nuevoModelo);
            System.out.println("Cambiado!");
        }
        System.out.println("¿Quieres cambiar el precio?"
                + " (Yes/No)");
        decision = teclado.nextLine().toLowerCase();
        if (decision.equals("yes")) {
            System.out.println("Introduce nueva precio: ");
            double nuevoPrecio = teclado.nextDouble();
            teclado.nextLine();
            reloj.setPrecio(nuevoPrecio);
            System.out.println("Cambiado!");
        }
        return reloj;
    }
    public static void main(String[] args) {
        ObjectContainer container = null;
        String opcion = null;
        Scanner teclado = new Scanner(System.in);
        String marca, modelo;
        double precio;
        ObjectSet objectSet;
        try {
            container = Db4o.openFile("relojes.dat");
            do {
                System.out.println("1. Introducir reloj");
                System.out.println("2. Buscar por marca");
                System.out.println("3. Buscar por modelo");
                System.out.println("4. Buscar entre dos precios");
                System.out.println("5. Modificar");
                System.out.println("S. Salir");

                opcion = teclado.nextLine();
                switch (opcion) {
                    case "1":
                        System.out.println("Introduce marca: ");
                        marca = teclado.nextLine();

                        System.out.println("Introduce modelo");
                        modelo = teclado.nextLine();

                        System.out.println("Introduce precio:");
                        precio = teclado.nextDouble();
                        teclado.nextLine();

                        Reloj reloj1 = new Reloj(marca,
                                modelo, precio);
                        Reloj relojPrueba
                                = new Reloj(marca, modelo, 0);

                        if (container.get(relojPrueba).hasNext() == false) {
                            System.out.println("Guardando reloj...");

                            container.set(reloj1);

                            container.commit();
                            System.out.println("Reloj guardado!");
                        } else {
                            System.out.println("El reloj ya existe");
                        }
                        break;

                    case "2":
                        System.out.println("Introduce marca por la que buscar");
                        marca = teclado.nextLine();
                        Reloj objReloj = new Reloj(
                                marca, null, 0);
                        objectSet = container.get(objReloj);

                        while (objectSet.hasNext()) {
                            System.out.println(objectSet.next());
                        }
                        break;
                    case "3":
                        System.out.println("Introduce modelo por la que buscar");
                        final String modeloBuscar = teclado.nextLine();

                        List<Reloj> relojesPorModelo = container.query(new Predicate<Reloj>() {
                            public boolean match(Reloj reloj) {
                                return reloj.getModelo().contains(modeloBuscar);
                            }
                        });
                        for (Reloj relojTmp : relojesPorModelo) {
                            System.out.println(relojTmp);
                        }
                        break;
                    case "4":
                        System.out.println("Introduce el precio mínimo por la que buscar");
                        double precioMin = teclado.nextDouble();
                        teclado.nextLine();
                        System.out.println("Introduce el precio máximo por la que buscar");
                        double precioMax = teclado.nextDouble();
                        teclado.nextLine();

                        List<Reloj> relojesPorPrecio = container.query(new Predicate<Reloj>() {
                            public boolean match(Reloj reloj) {
                                return reloj.getPrecio() > precioMin
                                        && reloj.getPrecio() < precioMax;
                            }
                        });
                        for (Reloj relojTmp : relojesPorPrecio) {
                            System.out.println(relojTmp);
                        }
                        break;
                    case "5":
                        int contador = 1;
                        Reloj todosLosRelojes = new Reloj(
                                null, null,0);
                        objectSet = container.get(todosLosRelojes);

                        while (objectSet.hasNext()) {
                            System.out.println(contador + ". " + objectSet.next());
                            contador++;
                        }
                        contador = 1;
                        System.out.println("Elige el número del reloj"
                                + " que quiere cambiar: ");
                        int numReloj = teclado.nextInt();
                        teclado.nextLine();
                        objectSet = container.get(todosLosRelojes);

                        while (objectSet.hasNext()) {
                            if (contador == numReloj) {
                                Reloj relojModificar
                                        = (Reloj) objectSet.next();
                                relojModificar = cambiarDatos(relojModificar);
                                container.set(relojModificar);
                                container.commit();
                            } else {
                                objectSet.next();
                            }

                            contador++;
                        }
                        break;
                    case "S":
                    case "s":
                        System.out.println("Adios!");
                        break;
                }
            } while (!opcion.equals("S"));

        } finally {
            if (container != null) {
                container.close();
            }
        }
    }
}
