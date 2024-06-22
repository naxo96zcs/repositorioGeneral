package org.example.DAO;

import org.springframework.stereotype.Component;

@Component
public class ClienteDAO {
    public void insertaCliente(){
        System.out.println("Insertando cliente");
    }

    public void metodoAspecto1(){
        System.out.println(this.getClass().getSimpleName());
    }
}
