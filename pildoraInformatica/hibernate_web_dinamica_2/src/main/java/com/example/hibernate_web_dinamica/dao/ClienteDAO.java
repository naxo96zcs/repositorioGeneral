package com.example.hibernate_web_dinamica.dao;

import com.example.hibernate_web_dinamica.model.Cliente;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface ClienteDAO {

    public List<Cliente> getClientes();
}
