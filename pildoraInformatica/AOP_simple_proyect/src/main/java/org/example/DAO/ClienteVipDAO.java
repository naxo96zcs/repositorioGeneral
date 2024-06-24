package org.example.DAO;

import org.example.model.Cliente;
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

    public long devolverDNI(){
        return 75633324;
    }

    public void creacionCliente(Cliente cliente,String residencia,int numero){
        System.out.println("Cargando cliente "+cliente.getNombre()+" con residencia "+ residencia+ " "+ numero);

    }
}
