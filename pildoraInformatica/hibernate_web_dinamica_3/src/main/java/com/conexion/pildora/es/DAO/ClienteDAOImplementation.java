package com.conexion.pildora.es.DAO;


import com.conexion.pildora.es.entity.Cliente;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository //Para registrarla en spring como un bean y se pueda inyectar al controlador
public class ClienteDAOImplementation implements ClienteDAO{
    @Autowired //Inyeccion de depdendencias (cogemos el elemento del contexto cuando lo necesitemos)
    private SessionFactory sessionFactory;

    @Override
    @Transactional //Con esto nos ahorramos el session begin,close,rollback...
    public List<Cliente> getClientes() {
        //Obtener sesion
        Session session = sessionFactory.getCurrentSession();
        //Crear query (hay una manera mas simple que devuelve la lista directamente)
        Query<Cliente> query = session.createQuery("from Cliente", Cliente.class);
        //Ejecutar la query devolver
        List<Cliente> clienteList = query.getResultList();
        return clienteList;
    }

    @Override
    @Transactional
    public void insertarCliente(Cliente cliente) {
        Session session=sessionFactory.getCurrentSession();
        session.persist(cliente);
    }
    @Override
    @Transactional
    public Cliente getCliente(int id) {
        System.out.println("[DAO]: get cliente wih id "+id);
        Session session=sessionFactory.getCurrentSession();
        Cliente cliente = session.get(Cliente.class,id);
        return cliente;
    }

    @Override
    @Transactional
    public void actualizarCliente(Cliente cliente) {
        System.out.println("[DAO]: Actualizando cliente "+cliente.getId());
        Session session = sessionFactory.getCurrentSession();
        //session.update(cliente);   FIXME DEPRECATED
        session.merge(cliente);
    }

    /**
     *
     * @param cliente si el parametro de entrada es cliente el borrado es mas sencillo pero
     *                añade complejidad si solo tenemos la id, incrementando el coste
     */
    @Override
    @Transactional
    public void eliminarCliente(Cliente cliente) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(cliente);
        System.out.println("[DAO]: Cliente borrado "+cliente.getId());
    }

    @Override
    @Transactional
    public void eliminarClienteById(int clienteId) {
        Session session = sessionFactory.getCurrentSession();

        //Creacion de la query con el parametro 'IdDelCliente' para el borrado del cliente por la id
        Query consulta = session.createQuery("DELETE FROM Cliente WHERE id=:IdDelCliente");
        //En la consulta se sustituye/establece el valor de 'IdDelCliente' por la del parametro de entrada clienteId
        consulta.setParameter("IdDelCliente",clienteId);
        //Se ejecuta la query
        consulta.executeUpdate();

        System.out.println("[DAO]: Cliente borrado con id "+clienteId);
    }
}
