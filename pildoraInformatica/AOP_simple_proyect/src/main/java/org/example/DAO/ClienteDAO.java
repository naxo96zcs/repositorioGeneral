package org.example.DAO;

import org.example.model.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteDAO {


    public void insertaCliente(){
        System.out.println("Insertando cliente");
    }

    public void metodoAspecto1(){
        System.out.println(this.getClass().getSimpleName());
    }

    public String devolverDNI(){
        return "75633324-T";
    }

    public void creacionCliente(Cliente cliente){
        System.out.println("Cargando cliente "+cliente.getNombre());

    }
}
