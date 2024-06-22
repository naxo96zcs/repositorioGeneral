package org.example.DAO;

import org.springframework.stereotype.Component;

@Component
public class ClienteVipDAO {
    private String id;
    public void insertaCliente(){
        System.out.println("Insertando cliente VIP");
    }
    public void setId(String id){
        System.out.println("Id "+ id +" establecida");
    }
    public void metodoAspecto2(){
        System.out.println(this.getClass().getSimpleName());
    }
}
