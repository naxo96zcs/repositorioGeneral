package org.example;

import org.example.Model.Cliente;
import org.example.Model.DetalleCliente;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * La eliminacion en cascada ya esta implementada en los modelos
 * @IMPORTANTE No hay informacion nueva relevante
 */
public class EliminarCascada {
    public static void main(String[] args) {
        String fichero = "hibernate.cfg.xml";
        SessionFactory factory = new Configuration().configure(fichero)
                .addAnnotatedClass(Cliente.class)
                .addAnnotatedClass(DetalleCliente.class)
                .buildSessionFactory();
        Session session = factory.openSession();

        try {
            session.beginTransaction();

            Cliente cliente = new Cliente("Nacho","Quintana","Loranca");
            DetalleCliente detalleCliente = new DetalleCliente("Google","650650650","Buena web");
            cliente.setDetalleCliente(detalleCliente);
            session.persist(cliente);
            System.out.println("Cliente " + cliente.getNombre() + " guardado");
            session.remove(cliente);
            System.out.println(cliente.getNombre()+" eliminado");

            session.getTransaction().commit();
        }catch (Exception e) {
            session.close();
            //Imprime la pila de llamadas, da mas informacion del error
            e.printStackTrace();
        }finally {
            session.close();
            factory.close();
        }
    }
}