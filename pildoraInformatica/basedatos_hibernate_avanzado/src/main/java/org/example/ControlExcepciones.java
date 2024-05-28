package org.example;

import org.example.Model.Cliente;
import org.example.Model.DetalleCliente;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Codigo leak es cuando se pide un dato pero no esta  y la conexion no se puede cerrar, creando fugas de memoria
 * utilizando mas memoria de la necesaria
 *
 */
public class ControlExcepciones {
    public static void main(String[] args) {
        String fichero = "hibernate.cfg.xml";
        SessionFactory factory = new Configuration().configure(fichero)
                .addAnnotatedClass(Cliente.class)
                .addAnnotatedClass(DetalleCliente.class)
                .buildSessionFactory();
        Session session = factory.openSession();

        try {
            session.beginTransaction();

            Cliente cliente =  session.get(Cliente.class,99);
            /**
             * En este caso al intentar obtener el detalle se produciria una excepcion y al no tener una forma de cerrar la sesion
             * se produciria un escape (leak) de memoria, por lo que es importante cerrar la sesion en el finally o en el catch
             */
            DetalleCliente detalleCliente =cliente.getDetalleCliente();
            session.getTransaction().commit();

        }catch (Exception e) {
            session.close();
            //Imprime la pila de llamadas, da mas informacion del error
            e.printStackTrace();
        }finally {
            session.close();
            factory.close();
        }
    }
}