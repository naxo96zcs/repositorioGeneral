package org.example.AOP;

import org.example.DAO.ClienteDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        //Leer la config de spring
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Configuracion.class);
        //Obtener el bean del contenedor de spring
        ClienteDAO cliente = context.getBean("clienteDAO",ClienteDAO.class);
        //Llamar al metodo
        cliente.insertaCliente();
        //Cerrar contexto
        context.close();

    }




}