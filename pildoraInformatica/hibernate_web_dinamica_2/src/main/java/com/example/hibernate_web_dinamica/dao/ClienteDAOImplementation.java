package com.example.hibernate_web_dinamica.dao;

import com.example.hibernate_web_dinamica.model.Cliente;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@Repository //Para registrarla en spring como un bean
public class ClienteDAOImplementation implements ClienteDAO{
/*  @Autowired
    private SessionFactory sessionFactory;*/


    @Override
    //@Transactional //Con esto nos ahorramos el session begin,close,rollback...
    public List<Cliente> getClientes() {
        //Obtener sesion
/*        Session session = sessionFactory.getCurrentSession();
        //Crear query (hay una manera mas simple que devuelve la lista directamente)
        Query<Cliente> query = session.createQuery("from Cliente", Cliente.class);
        //Ejecutar la query devolver
        List<Cliente> clienteList = query.getResultList();
        return clienteList;*/
        return null;
    }
}
