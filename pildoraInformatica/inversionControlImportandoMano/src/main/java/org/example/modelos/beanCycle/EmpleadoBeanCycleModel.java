package org.example.modelos.beanCycle;

import org.example.inyeccionDependencia.CreacionInformes;
import org.example.inyeccionDependencia.Informe;
import org.example.modelos.Empleados;

public class EmpleadoBeanCycleModel implements Empleados {

    private CreacionInformes informes;
    private String empresa;
    private String email;

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /*public EmpleadoBeanCycleModel (CreacionInformes informes){
        this.informes = informes;
    }*/

    @Override
    public String getTareas() {
        return "Tarea de "+getClass().getSimpleName();
    }

    @Override
    public String getInforme() {
        return "Informe de "+getClass().getSimpleName()+ " informe ->: "+informes.getInforme();
    }

    public void setInformes(Informe informes) {
        this.informes=informes;
    }

    /**
     * Metodo init se ejecuta antes de que el bean este disponible, se llama en el XML
     * Ambos metodos ddeben instanciarse con una "propiedad" dentro de las caracterisitcas del bean
     * Dentro del método: init-method="nombreEsteMetodo", y cerrar el bean
     */
    public void antesCreacion(){
        System.out.println("# Bean creation");
    }

    /**
     * Metodo destroy se ejecuta despues del que el bean se destuya, se llama en el XML
     * Dentro del método: destroy-method="nombreEsteMetodo", y cerrar el bean
     */
    public void metodoFinal(){
        System.out.println("# Bean destroy");
    }

}
