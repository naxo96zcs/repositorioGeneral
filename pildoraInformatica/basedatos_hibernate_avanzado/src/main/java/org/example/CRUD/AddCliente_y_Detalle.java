package org.example.CRUD;

import org.example.Model.Cliente;
import org.example.Model.DetalleCliente;
import org.example.Model.Pedido;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class AddCliente_y_Detalle {
    public static void main(String[] args) {
        String fichero = "hibernate.cfg.xml";
        SessionFactory factory = new Configuration().configure(fichero)
                .addAnnotatedClass(Cliente.class)
                .addAnnotatedClass(DetalleCliente.class)
                .addAnnotatedClass(Pedido.class)
                .buildSessionFactory();

        Session session = factory.openSession();

        try {
            session.beginTransaction();
            Cliente cliente = new Cliente("Nacho","Quintana","Loranca");
            DetalleCliente detalleCliente = new DetalleCliente("Google","650650650","Buena web");
            cliente.setDetalleCliente(detalleCliente);
            System.out.println("+Cliente añadido");

            session.persist(cliente);//Save is deprecated use persiste instead
            System.out.println("#Mostrando clientes#");
            List<Cliente> listaClientes = session.createQuery("from Cliente",Cliente.class).getResultList();
            for (Cliente c : listaClientes)
                System.out.println(c);
            session.getTransaction().commit();
            session.close();
            factory.close();
        } finally {
            factory.close();
        }
    }
}