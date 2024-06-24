package org.example.AOP;

import org.example.DAO.ClienteDAO;
import org.example.DAO.ClienteVipDAO;
import org.example.model.Cliente;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        //Leer la config de spring
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Configuracion.class);
        //Obtener el bean del contenedor de spring
        ClienteDAO clienteDAO = context.getBean("clienteDAO",ClienteDAO.class);
        //Llamar al metodo
        clienteDAO.insertaCliente();
        System.out.println("---------");

        ClienteVipDAO clienteVipDAO  = context.getBean("clienteVipDAO",ClienteVipDAO.class);
        clienteVipDAO.insertaCliente();
        clienteVipDAO.setId("ART15");
        System.out.println("--------- 1 aspect 2 diferents methods");

        clienteDAO.metodoAspecto1();
        clienteVipDAO.metodoAspecto2();

        System.out.println("--------- Diferent return of methods");
        System.out.println(clienteDAO.devolverDNI());
        System.out.println(clienteVipDAO.devolverDNI());

        System.out.println("--------- Arguments");
        Cliente clienteModelo=new Cliente();
        clienteModelo.setApellido("Apell");
        clienteModelo.setNombre("Nombrelo");
        clienteDAO.creacionCliente(clienteModelo);
        clienteVipDAO.creacionCliente(clienteModelo,"dehesillas",4);



        //Cerrar contexto
        context.close();

    }




}