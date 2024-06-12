# Primeros 
## WebApp
Este directorio tengo que crearlo yo, a raíz de hacer un proyecto de tipo JakartaEE no se si se podrá de 
otra manera hacer que sea más automatico.<br>
Estructura: en src > main crear **webapp > WEB-INF >** vista ..., dentro de webapp se creará el fichero **web.xml** para configurar y mapear los servlet (fichero de configruacion casi general), en caso de tener que añadoir librerías tambien irían en WEB-INF > lib 

## Creacion de la base de datos
En este caso hemos utilziado el lanzador de comandos de SQL de phpAdmin
```sql
CREATE DATABASE  IF NOT EXISTS `gestionPedidosCRUD` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `gestionPedidosCRUD`;


DROP TABLE IF EXISTS `cliente`;

CREATE TABLE `cliente` (
                           `id` int(10) NOT NULL AUTO_INCREMENT,
                           `nombre` varchar(35) DEFAULT NULL,
                           `apellido` varchar(35) DEFAULT NULL,
                           `email` varchar(45) DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;



LOCK TABLES `cliente` WRITE;


INSERT INTO `cliente` VALUES
                          (1,'David','Maldonado','david@pildorasinformaticas.es'),
                          (2,'Sandra','López','sandra@pildorasinformaticas.es'),
                          (3,'María','Gómez','maria@pildorasinformaticas.es'),
                          (4,'Antonio','Fernández','antonio@pildorasinformaticas.es'),
                          (5,'Alicia','Martín','alicia@pildorasinformaticas.es');


UNLOCK TABLES;



```

## Servlet (java) de conexion a BD
Despues de la creacion de la BD y la integracion de las librerias correspondientes, 
pasamos a crear un servlet para ver si la conexion es buena con la BD, es necesario tener en cuenta<br>
* Para acceder al servlet es necesario que se acceda a la URL que esta anotada (@) con la clase
* Refactorizar el metodo doGet con la conexion a la base de datos

Codigo de clase [ServletCheckConexionDB.java](src%2Fmain%2Fjava%2FServletCheckConexionDB.java)
```java
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
            System.out.println("Conectado!");
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
```
### Servlet (xml) conexion BD
En el mismo nivel se encuentra la creacion de la conexion por servlet en el fichero [spring-crud-servlet.xml](src%2Fmain%2Fwebapp%2FWEB-INF%2Fspring-crud-servlet.xml),
en este bean se define las propieades para el pool de conexiones, indicando la conexion, usuario y contraseña. <br>
Tambien la configuracion de **c3po**, una libreria de java que provee una manera de manejar las conexiones de BD. 

```xml

<bean id="myDataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource"
      destroy-method="close">
    <property name="driverClass" value="com.mysql.cj.jdbc.Driver"/>
    <property name="jdbcUrl"
              value="jdbc:mysql://localhost:3306/gestionPedidosCRUD?useSSL=false&amp;serverTimezone=UTC"/>
    <property name="user" value="root"/>
    <property name="password" value=""/>

    <!-- Propiedades connection pool para C3P0 -->
    <property name="minPoolSize" value="5"/>
    <property name="maxPoolSize" value="20"/>
    <property name="maxIdleTime" value="30000"/>
</bean> 
```

# Programando proyecto
Una vez puesto en marcha pasos:
1) Creacion del fichero [web.xml](src%2Fmain%2Fwebapp%2FWEB-INF%2Fweb.xml) para la creacion del servle y su maepado, en este fichero se indica en donde debe buscar los recursos para configurarlo. Debe figurar en la ruta src/main/webapp/WEB-INF/web.xml
2) Configruacion del fichero [spring-crud-servlet.xml](src%2Fmain%2Fwebapp%2FWEB-INF%2Fspring-crud-servlet.xml) para indicar el servlet que queremos y que tiene que hacer 
    * Indicar en que ruta debe escanear los componentes
    * Indicar (bean) el directorio para que obtenga la vistas (JSP) 
    * Crear una configuracion (bean) para la conexion con la BD y un pool de conexiones  
    * Configurar (bean) la **session Factory** de Hibernate, sessionFactory representa una instancia de Hibernate que mantiene una conexion constante
    * Configurar (bean) el gestor de transacciones de Hibernate
3) Configurar el fichero BD  para que se hagan las conexiones por el mismo no utilziando el servlet de comprobador de conexion
4) Creacion del @Controler que mostrará el JSP para realizar una prueba inicialmente y luego una lista de clientes
5) Creacion del JSP para mostrar clientes
6) Creacion del modelo [Cliente.java](src%2Fmain%2Fjava%2Fcom%2Fexample%2Fhibernate_web_dinamica%2Fmodel%2FCliente.java)
```java
@Data //lombock (lb)
@AllArgsConstructor //(lb)
@NoArgsConstructor //(lb)
@Entity //Con esto se indica que se va a referir a una tabla
@Table(name = "cliente") //Nombre de la tabla en la BD
public class Cliente {
    @Id //Se usa para indicar que es la primarykey
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Valor generado automaticamente por la BD?
    private int id;
    @Column(name = "nombre") //hace referencia a la columna de la tabla (entity)
    private String nombre;
    ...
}
```
7) Creacion de la interfaz DAO ([ClienteDAO.java](src%2Fmain%2Fjava%2Fcom%2Fexample%2Fhibernate_web_dinamica%2Fdao%2FClienteDAO.java)) y su implementacion 
# Errores que surgen en la puesta en marcha
Cuando se estaba importando el codigo desde **maven** surge un error con la dependencia de *hibernate-commons-annotations*,
aparece un error con el jta que no es localizado en el repositorio de mave, por lo que es excluido.

```xml
   <!-- https://mvnrepository.com/artifact/org.hibernate/hibernate-commons-annotations -->
<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-commons-annotations</artifactId>
    <version>3.3.0.ga</version>
    <exclusions>
        <exclusion>
            <groupId>javax.transaction</groupId>
            <artifactId>jta</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```
