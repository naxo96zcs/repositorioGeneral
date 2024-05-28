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
        System.out.println("[DAO]:Actualizando cliente:"+cliente.getId());
        Session session = sessionFactory.getCurrentSession();
        //session.update(cliente);   FIXME DEPRECATED
        session.merge(cliente);
    }
}
