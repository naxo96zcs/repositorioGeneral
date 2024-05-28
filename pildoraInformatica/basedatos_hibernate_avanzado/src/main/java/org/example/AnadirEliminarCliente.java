package org.example;

import org.example.Model.Cliente;
import org.example.Model.DetalleCliente;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class AnadirEliminarCliente {
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
            //Antes de inciar la transaccion es necesario asociar los objetos
            System.out.println(cliente);
            //Guardamos el cliente, ya mapeado anteriormente
            session.beginTransaction();
            session.persist(cliente);//Save is deprecated use persiste instead

            System.out.println("-Mostrando clientes con informacion asociada");
            List<Cliente> listaClientes = session.createQuery("from Cliente",Cliente.class).getResultList();
            for (Cliente c : listaClientes)
                System.out.println(c);
            //Eliminando el cliente insertado (el unico que hay en la lista)
           // session.remove(listaClientes.getFirst());

            Cliente cAux = session.get(Cliente.class,listaClientes.getFirst().getId());
            if(cAux==null) System.out.println("Cliente borrado");
            else System.out.println("Cliente no borrado");

            session.getTransaction().commit();
            session.close();
            factory.close();
        } finally {
            factory.close();
        }




    }
}