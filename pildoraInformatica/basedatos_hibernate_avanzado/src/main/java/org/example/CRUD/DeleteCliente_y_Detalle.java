package org.example.CRUD;

import org.example.Model.Cliente;
import org.example.Model.DetalleCliente;
import org.example.Model.Pedido;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class DeleteCliente_y_Detalle {
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
            System.out.println("#Mostrando clientes#");
            List<Cliente> listaClientes = session.createQuery("from Cliente", Cliente.class).getResultList();
            for (Cliente c : listaClientes) {
                session.remove(c);
            }
            //Eliminados todos los clientes

            listaClientes = session.createQuery("from Cliente", Cliente.class).getResultList();
            if (listaClientes.size() == 0) System.out.println("-Clientes borrados");
            else System.out.println("#Clientes no borrados");

            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }




    }
}