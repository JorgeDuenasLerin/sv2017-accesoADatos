// package repasoexamen;

/**
 *
 * @author Miguel Moya Ortega
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.Serializable;

// -----------------------------------------

class Vehiculo implements Serializable {
    private int id;
    protected String marca;
    protected String modelo;
    
    public int getId() {
        return this.id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    
    Vehiculo(int id, String marca, String modelo) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
    }
    
    @Override
    public String toString() {
        return id + "-" + marca + "-" + modelo;
    }
} 

// -----------------------------------------

class Modelo {
    protected String nombre;
    protected String pais;
    protected int anyo;
    
    public Modelo(String nombre, String pais, int anyo) {
        this.nombre = nombre;
        this.pais = pais;
        this.anyo = anyo;
    }
    
    @Override
    public String toString() {
        return nombre + "-" + pais + "-" + anyo;
    }
} 

// -----------------------------------------

public class RepasoExamen {

    public static void main(String[] args) {
        List<Vehiculo> vehiculos = getVehicles();
        List<Modelo> modelos = getModelos();

        for (Vehiculo v : vehiculos) {
            System.out.println(v);
        }
        for (Modelo m : modelos) {
            System.out.println(m);
        }

        generateCSV(vehiculos, modelos);
        
        serialice(vehiculos);
    }

    public static List<Modelo> getModelos() {
        List<Modelo> modelos = new ArrayList<>();

        try {
            File inputFile = new File("marcas.xml");
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("marca");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    String nombre = eElement.getAttribute("nombre");
                    String pais = eElement.getAttribute("pais");
                    int anyo = Integer.parseInt(eElement.getAttribute("anyo"));
                    modelos.add(new Modelo(nombre, pais, anyo));
                }
            }
        } catch (Exception e) {
            System.out.println("Error desconocido.");
        }

        return modelos;
    }

    public static List<Vehiculo> getVehicles() {
        List<Vehiculo> vehiculos = new ArrayList<>();

        try {
            File inputFile = new File("vehiculos.xml");
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("vehiculo");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    int id = Integer.parseInt(eElement.getAttribute("id"));
                    String marca = eElement.getElementsByTagName("marca")
                            .item(0).getTextContent();
                    String modelo = eElement.getElementsByTagName("modelo")
                            .item(0).getTextContent();
                    vehiculos.add(new Vehiculo(id, marca, modelo));
                }
            }
        } catch (Exception e) {
            System.out.println("Error desconocido.");
        }
        return vehiculos;
    }

    public static void generateCSV(List<Vehiculo> vehiculos, List<Modelo> modelos) {

        try {
            PrintWriter print = new PrintWriter("vehiculos.csv");
            print.println("id,marca,modelo,paisFundacion,anyoFundacion");

            for (Vehiculo v : vehiculos) {
                print.print(
                        v.getId() + ",\"" + v.getMarca() + "\",\"" + v.getModelo()
                        + "\",\""
                );
                boolean found = false;
                for (Modelo m : modelos) {
                    if (m.nombre.toLowerCase().contains(v.marca.toLowerCase())) {
                        print.print(m.pais + "\",\"" + m.anyo + "\"");
                        found = true;
                    }
                }
                if (!found) {
                    print.print("Desconocido\",\"Desconocido\"");
                }
                print.println();
            }

            print.close();
        } catch (FileNotFoundException e) {
            System.out.println("Fichero no encontrado.");
        }
    }

    public static void serialice(List<Vehiculo> v) {
        try {
            File fichero = new File("vehiculos.dat");
            FileOutputStream ficheroSalida
                    = new FileOutputStream(fichero);
            ObjectOutputStream ficheroObjetos
                    = new ObjectOutputStream(ficheroSalida);
            ficheroObjetos.writeObject(v);
            ficheroObjetos.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Fichero no encontrado");
        } catch (IOException ex) {
            System.out.println("Error desconocido");
        }
    }
}
