package conexionHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;


public class OperacionesTabla {
    public static void main(String[] args) {
        //Insertar y borrar
        insertAndDelete();
        //Actualizar tabla
        updatedTable();
        //Informacion de la tabla con todos los datos
        select();
        //Insertar y consultar cliente
        saveAndGetClient();
    }

    private static void insertAndDelete() {
        String fichero = "hibernate.cfg.xml";
        //Obteniendo una instancia de hibernate
        SessionFactory factory = new Configuration().configure(fichero).addAnnotatedClass(Cliente.class).buildSessionFactory();
        //Abriendo sesion a partir de la instancia
        Session session = factory.openSession();
        try {
            Cliente cliente = new Cliente("Pepe", "Diaz", "Conquistadores");
            session.beginTransaction();
            session.save(cliente);
            session.getTransaction().commit();
            System.out.println(cliente + " guardado");

            //Inicializando transaccion
            session.beginTransaction();
            //Ejecucion QUERY
            session.createQuery("DELETE FROM Cliente WHERE nombre='Pepe'").executeUpdate();
            //Guardando el cambio de la QUERY
            session.getTransaction().commit();
            //Cerrando instancia
            factory.close();

        } finally {
            //Si t0do sale mal se cierra la instancia
            factory.close();
        }
    }

    /**
     * Aqui se obtiene un elemento con id 1 y se cambia el nombre dos veces, para demostrarlo se hace un select y se obtiene
     * los datos modificados dos veces
     *
     * @! La informacion de la tabla se modifica cuando se modifica el OBJETO, cuando se finalizan las transaccioens es importante
     * hacer commit y cerrar la sesio. O tambien puede utilizarse la setencia update
     */
    private static void updatedTable() {
        String fichero = "hibernate.cfg.xml";
        SessionFactory factory = new Configuration().configure(fichero).addAnnotatedClass(Cliente.class).buildSessionFactory();
        Session session = factory.openSession();
        try {
            int clienteId = 1;
            session.beginTransaction();
            Cliente cliente = session.get(Cliente.class, clienteId); //Obtencion
            session.getTransaction().commit();
            cliente.setNombre("Maria");
            //No es necesario modificar nada, ya que el objeto esta apuntado al dato dentro de la base de datos
            session.beginTransaction();
            List<Cliente> listaClientes = session.createQuery("from Cliente where id=1").getResultList();
            session.getTransaction().commit();
            soutData(listaClientes);
            cliente.setNombre("Juan");

            session.beginTransaction();
            List<Cliente> listaClientes2 = session.createQuery("from Cliente where id=1").getResultList();
            session.getTransaction().commit();

            soutData(listaClientes2);
            session.beginTransaction();
            session.createQuery("update Cliente set nombre='Nacho' where id=1").executeUpdate();
            session.getTransaction().commit();

            session.beginTransaction();
            List<Cliente> listaClientes3 = session.createQuery("from Cliente where id=1").getResultList();
            session.getTransaction().commit();
            System.out.println("¡¡La sentencia de update se ejecuta pero no se puede consultar al comento, no se por qué!!");
            soutData(listaClientes3);

            factory.close();

        } finally {
            factory.close();
        }
    }

    private static void select() {
        //SELECT
        String fichero = "hibernate.cfg.xml";
        SessionFactory factory = new Configuration().configure(fichero).addAnnotatedClass(Cliente.class).buildSessionFactory();

        Session session = factory.openSession();
        try {
            //Incio sesion
            session.beginTransaction();

            //Consulta SQL SELECT * FROM Clientes
            System.out.println("-Consulta 1");
            List<Cliente> listaClientes = session.createQuery("from Cliente").getResultList();
            soutData(listaClientes);
            //Consulta SQL SELECT * FROM Clientes WHERE Apellidos = Diaz
            System.out.println("-Consulta 2");
            listaClientes = session.createQuery("from Cliente cl where cl.apellidos='Diaz'").getResultList();
            soutData(listaClientes);
            //Consulta SQL SELECT * FROM Clientes WHERE Apellidos = Diaz OR
            System.out.println("-Consulta 3");
            listaClientes = session.createQuery("from Cliente cl where cl.apellidos='Diaz' and cl.direccion='Gran via'").getResultList();
            soutData(listaClientes);
            //Commit y sesion
            session.getTransaction().commit();
            session.close();


        } finally {
            factory.close();
        }
    }

    private static void soutData(List<Cliente> listaClientes) {
        for (Cliente c : listaClientes) {
            System.out.println(c);
        }
        System.out.println();
    }

    public static void saveAndGetClient() {
        String fichero = "hibernate.cfg.xml";
        SessionFactory factory = new Configuration().configure(fichero).addAnnotatedClass(Cliente.class).buildSessionFactory();

        Session session = factory.openSession();

        try {
            Cliente cliente = new Cliente("Raul", "Diaz", "Conquistadores");
            session.beginTransaction();
            session.save(cliente);
            session.getTransaction().commit();
            System.out.println(cliente + " guardado");

            System.out.println("Consultando registro con id " + cliente.getId());
            session.beginTransaction();
            Cliente clienteRecuperado = session.get(Cliente.class, cliente.getId());
            System.out.println("-Cliente\n" + clienteRecuperado + "\n-----------------");
            session.getTransaction().commit();


            session.close();
        } finally {
            factory.close();
        }

    }
}
