package org.example.aspectos;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

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

}
