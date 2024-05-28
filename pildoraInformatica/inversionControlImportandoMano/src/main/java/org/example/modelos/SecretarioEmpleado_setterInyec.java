package org.example.modelos;

import org.example.inyeccionDependencia.CreacionInformes;

/**
 * INYECCION POR SETTER
 */
public class SecretarioEmpleado_setterInyec implements Empleados{

    private String tarea;
    private String email;
    private String empresa;
    private CreacionInformes creacionInformes; //Inyeccion de dependencia mediante el setter

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

    /**
     * Mediante el setter se consigue la inyeccion Spring lo inyecta cuando lo necesita
     * @param creacionInformes
     */
    public void setCreacionInformes(CreacionInformes creacionInformes) {
        this.creacionInformes = creacionInformes;
    }

    public String getTareas(){
        tarea = "Gestiono la tareas de "+getClass().getSimpleName()+ " gestionando agenda";
        return tarea;
    }

    @Override
    public String getInforme() {
        String aux = getClass().getSimpleName()+ " creando informe "+creacionInformes.getInforme();
        return aux;
    }
}
