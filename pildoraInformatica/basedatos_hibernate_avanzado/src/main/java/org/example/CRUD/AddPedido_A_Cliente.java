package org.example.CRUD;

import org.example.Model.Cliente;
import org.example.Model.DetalleCliente;
import org.example.Model.Pedido;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Date;
import java.util.List;

public class AddPedido_A_Cliente {
    public static void main(String[] args) {
        String fichero = "hibernate.cfg.xml";
        SessionFactory factory = new Configuration().configure(fichero)
                .addAnnotatedClass(Cliente.class)
                .addAnnotatedClass(DetalleCliente.class)
                .addAnnotatedClass(Pedido.class)
                .buildSessionFactory();

        Session session = factory.openSession();

        try {
            Pedido pedido = new Pedido(new Date());
            pedido.setFormaPago("Tarjeta credito");

            session.beginTransaction();
            List<Cliente> listaClientes = session.createQuery("from Cliente", Cliente.class).getResultList();
            System.out.println("#Mostrando clientes#");
            for (Cliente c : listaClientes)
                System.out.println(c);

            Cliente cliente = listaClientes.getFirst();
            System.out.println("Añadiendo pedido a "+cliente.getNombre());
            cliente.agregarPedido(pedido);
            session.persist(cliente);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }
    }
}
