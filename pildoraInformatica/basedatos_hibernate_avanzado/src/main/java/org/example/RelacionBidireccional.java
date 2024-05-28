package org.example;

import org.example.Model.Cliente;
import org.example.Model.DetalleCliente;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class RelacionBidireccional {
    public static void main(String[] args) {
        String fichero = "hibernate.cfg.xml";
        /**
         * Importante añadir las clases
         */
        SessionFactory factory = new Configuration().configure(fichero)
                .addAnnotatedClass(Cliente.class)
                .addAnnotatedClass(DetalleCliente.class)
                .buildSessionFactory();
        Session session = factory.openSession();

        try {
            //Creando un cliente y sus detalles
            Cliente cliente = new Cliente("Nacho","Quintana","Loranca");
            DetalleCliente detalleCliente = new DetalleCliente("Google","650650650","Buena web");
            cliente.setDetalleCliente(detalleCliente);
            session.beginTransaction();
            List<DetalleCliente> detalleCliente1 = session.createQuery("from DetalleCliente",DetalleCliente.class).getResultList();

            if(detalleCliente1.size()<1){ System.out.println("Insertar almenos un cliente");
                return;
            }
            System.out.println(detalleCliente1.getFirst().getCliente());


            session.getTransaction().commit();
            session.close();
            factory.close();
        } finally {
            factory.close();
        }




    }
}