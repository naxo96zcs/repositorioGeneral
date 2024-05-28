package org.example.inyeccionDependencia;

public class Informe implements CreacionInformes{


    @Override
    public String getInforme() {
        return "Presentacion del "+getClass().getSimpleName();
    }
}
