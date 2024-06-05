package com.conexion.pildora.es.DAO;
import com.conexion.pildora.es.entity.Cliente;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteDAO {

    public List<Cliente> getClientes();
    public void insertarCliente(Cliente cliente);
    public Cliente getCliente(int id);
    void actualizarCliente(Cliente cliente);
    void eliminarCliente(Cliente cliente);
    void eliminarClienteById(int clienteId);
}
