package org.example.aspectos;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.example.model.Cliente;


@Aspect
@Component
public class LoginConAspecto {
    @Before("execution(public void org.example.DAO.ClienteVipDAO.insertaCliente())")
    public void antesInsertarCliente() {
        System.out.println("Aspect - El usuario esta logueado");
        System.out.println("Aspect - El perfil para insertar los clientes es correcto");
    }
    @Before("execution(public void setId(String)))")
    public void antesSetId() {
        System.out.println("Aspect - Comprobando id");

    }
    @Before("execution(public void metodoAspecto*())")
    public void aspectoEnMetodosDiferentes() {
        System.out.println("Aspect - DiferentMethods");
    }

    /**
     * Mismo nombre pero diferentes output
     */
    @Before("execution(public * devolverDNI())")
    public void metodoAspectoDiferenteOutput() {
        System.out.println("Aspect - DiferentOutputs");
    }

    @Before("execution(public void creacionCliente(org.example.model.Cliente))")
    public void metodoConUnArgumento(){
        System.out.println("Aspect - MethodAnArgument");
    }

    @Before("execution(public void creacionCliente(org.example.model.Cliente,..))")
    public void metodoConNArgumentos(){
        System.out.println("Aspect - MethodLotArguments");
    }

}
