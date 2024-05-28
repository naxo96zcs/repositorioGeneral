import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

@WebServlet("/ServletCheckConexionDB")
@WebListener
public class ServletCheckConexionDB extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(req, response);
        //Con est
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();


        String urlDB="jdbc:mysql://localhost:3306/gestionPedidosCRUD?useSSL=false";
        String user="root";
        String pass="";
        //Esto es nuevo para indicar que se usa la version
        String driver = "com.mysql.cj.jdbc.Driver";
        try {
            //Para que escriba en el navegador (PrintWriter)

            out.print("Nombre de la BBDD: "+urlDB);
            Class.forName(driver);
            Connection connection= DriverManager.getConnection(urlDB,user,pass);
            out.println("Conectado!");
            System.out.print("\nHibernate: Conectado!");
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
