package org.example.modelos;

import org.example.inyeccionDependencia.CreacionInformes;

/**
 * INYECCION POR CONSTRUCTOR
 */
public class DirectorEmpleado_cosctructInyec implements Empleados {

    //Dependencia que sera inyectada
    private CreacionInformes informesNuevos;

    //Constructor que inyecta la depenencia
    public DirectorEmpleado_cosctructInyec(CreacionInformes informes){
        this.informesNuevos = informes;
    }

    @Override
    public String getTareas() {
        String aux = getClass().getSimpleName()+" haciendo tareas de Director";
        return aux;
    }
    //Dependencia iyeccion
    @Override
    public String getInforme() {
        return "Informe creado por "+getClass().getSimpleName()+": "+ informesNuevos.getInforme();
    }
}
