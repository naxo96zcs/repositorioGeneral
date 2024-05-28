package org.example;

import org.example.modelos.Empleados;
import org.example.modelos.JefeEmpleado;
import org.example.modelos.SecretarioEmpleado_setterInyec;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {


        System.out.println("Start!");
        //methodCreadorEmpleadosBasico();
        contextCreationXMLFileDependencyInyection();


    }

    public static void contextCreationXMLFileDependencyInyection() {
        //Se crea el contexto mediante un fichero xml del cual se extrae toda la configuracion del mismo
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        //Id que tendra este bin y su interfaz
        Empleados juan = context.getBean("miEmpleado",Empleados.class);
        System.out.println("\n"+juan.getTareas());
        System.out.println(juan.getInforme()); //Inyeccion en el constructor por la etiqueta constructor-arg

        Empleados secretario = context.getBean("secretarioInyeccionSetter",Empleados.class);
        System.out.println("\n"+secretario.getTareas());
        System.out.println(secretario.getInforme());//Inyeccion en el setter por la etiqueta property

        SecretarioEmpleado_setterInyec secretarioCasteado = (SecretarioEmpleado_setterInyec) secretario;
        System.out.println("Email y empresa: "+secretarioCasteado.getEmail()+" - "+secretarioCasteado.getEmpresa());

        Empleados jefeEmpleado = context.getBean("jefeEmpleadoInyecSetter",Empleados.class);
        System.out.println("\n"+jefeEmpleado.getTareas());
        System.out.println(jefeEmpleado.getInforme());
        JefeEmpleado jefeEmpleadoCast = (JefeEmpleado) jefeEmpleado;
        System.out.println("Email y empresa: "+jefeEmpleadoCast.getEmail()+" - "+jefeEmpleadoCast.getEmpresa());

        //Cerrarlo como su fuera un metodo de lectura del xml
        context.close();
    }

    /**
     * Sin Spring ni nada, un resumen de lo que queremos que ocurra pero sin Spring
     */
    public static void methodCreadorEmpleadosBasico(){
        //Creacion basica con una clase
       /* Empleados jefeEmpleado = new DirectorEmpleado();
        System.out.println(jefeEmpleado.getTareas());*/
    }
}