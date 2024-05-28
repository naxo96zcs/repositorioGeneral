package org.example.modelos;

import org.example.inyeccionDependencia.CreacionInformes;

public class JefeEmpleado implements Empleados{
    private String tarea;
    private String email;
    private String empresa;
    private CreacionInformes creacionInformesEmpleado;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public void setCreacionInformesEmpleado(CreacionInformes creacionInformes) {
        this.creacionInformesEmpleado = creacionInformes;
    }


    public String getTareas(){
        tarea = "Gestiono las tareas de "+getClass().getSimpleName();
        return tarea;
    }

    @Override
    public String getInforme() {
        return getClass().getSimpleName()+" creando informe "+ creacionInformesEmpleado.getInforme();
    }


}
