package pruebasHibernate;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
    public static void main(String[] args) {
        System.out.println("Prueba conexion a Hibernate!");

        String jdbcUrl="jdbc:mysql://localhost:3306/pruebasHibernate?useSSL=false";
        String user="root";
        String pass=""; // en blanco por que es a false el acceso


        try {
            Connection connection = DriverManager.getConnection(jdbcUrl,user,pass);
            if(connection!=null)
                System.out.println("Conectado");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}