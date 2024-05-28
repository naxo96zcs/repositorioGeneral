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

    public ServletCheckConexionDB() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);

        String urlDB="jdbc:mysql://localhost:3306/gestionPedidosCRUD?useSSL=false";
        String user="root";
        String pass="";
        //Esto es nuevo para indicar que se usa la version
        String driver = "com.mysql.cj.jdbc.Driver";
        try {
            //Para que escriba en el navegador (PrintWriter)
            PrintWriter out = resp.getWriter();
            out.println("Nombre de la BBDD: "+urlDB);
            Class.forName(driver);
            Connection connection= DriverManager.getConnection(urlDB,user,pass);
            out.println("Conectado!");
            System.out.println("Hibernate: Conectado!");
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
