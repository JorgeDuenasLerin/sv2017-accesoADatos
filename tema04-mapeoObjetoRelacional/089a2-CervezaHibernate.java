/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cervezahibernate;

import java.util.List;
import java.util.Scanner;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Carla
 */
public class CervezaHibernate {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int opt =0;
        Scanner teclado = new Scanner(System.in);
        
        do {
            System.out.println("1. Añadir cervezas ");
            System.out.println("2. Ver todas las cervezas");
            System.out.println("3. Ver marcas de la cerveza");
            System.out.println("0. Salir");
            opt = Integer.parseInt(teclado.nextLine()); 
            System.out.println();
            switch(opt) {
                case 1:
                    anyadir();
                    break;
                case 2:
                    verTodos();
                    break;
                case 3:
                    verMarcas();
                    break;
            }
            
        }while(opt != 0);
        System.out.println("Adios!");
    }
    
    public static void anyadir() {
        Scanner teclado = new Scanner(System.in);
        System.out.print("Introduzca el código: ");
        short id = (short) Integer.parseInt(teclado.nextLine());
        System.out.print("Introduzca la marca: ");
        String marca = teclado.nextLine();
        System.out.print("Introduzca el comentario: ");
        String comentarios = teclado.nextLine();
        teclado.close();
        Session sesion = NewHibernateUtil.getSessionFactory().openSession();
        Transaction trans = sesion.beginTransaction();
        Cervezas cerveza = new Cervezas(id, marca, comentarios);
        sesion.save(cerveza);
        trans.commit();
        sesion.close();
    }
    
    public static void verTodos() {
    System.out.println ("Mostrando todos los datos:");
    Session sesion = NewHibernateUtil.getSessionFactory().openSession();
    Query consulta = sesion.createQuery("from Cervezas"); //llamada a la clase
    List resultados = consulta.list();
    for( Object resultado : resultados){
        Cervezas cerveza = (Cervezas) resultado;
            System.out.println ( cerveza.getId() + ": " +
                cerveza.getMarca()+ ", comentarios: " +
                cerveza.getComentarios());
            System.out.println();
        }
    sesion.close();
    }
    
    public static void verMarcas() {
    Session sesion = NewHibernateUtil.getSessionFactory().openSession();
    Query consulta = sesion.createQuery("select marca from Cervezas");
    List resultados = consulta.list();
    for( Object resultado : resultados){
        String marca = (String) resultado;
        System.out.println ( marca );
        }
        System.out.println("");
    sesion.close();
    }
}
