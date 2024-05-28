# Descripcion

CRUD, Hibernate, JSP, servlet

# Crear BD
Crear BD en phpAdmin

````sql
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
````

# Crear servlet check BD

https://youtu.be/iHUuk_VeH7U?list=PLU8oAlHdN5Blq85GIxtKjIXdfHPksV_Hm

# Etiquetas de JavaEE

https://www.baeldung.com/javaee-web-annotations

# Importante los facets

Los facets implementan librerias, dependencias y proveen de elementos adicionales a la interfaz de usuario para
configurar especificamente el entorno de trabajo
o framework

Se utiliza para "apuntar" / localizar **webapp**/web y asi tener localizado el
recurso [web.xml](src%2Fmain%2Fwebapp%2FWEB-INF%2Fweb.xml) que sera el archivo de referencia
para configurar el proyecto, desde el se crea un servlet con la configuracion que figura
en [spring-crud-servlet.xml](src%2Fmain%2Fwebapp%2FWEB-INF%2Fspring-crud-servlet.xml)

# Librerias / Maven

Importada las librerias necesarias, lo que he hecho es buscar parte de lo que no reconoce y buscarlo en internet,
luego en la segunda buscada (en mi caso) te encuentras con **jar-download** que te indica la version de lo que necesitas
y
si ha sido actualziada.<br>
Es conveniente que se **reduzca el numero de dependencias** y que figure el plugin de maven
que se encarga de compilar dependencias, clases y recursos de la aplicacion web y crear el **_WAR_**

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-war-plugin</artifactId>
    <version>3.3.2</version>
</plugin>
```

Hago un run y no debe dar problemas

# Crear controlador y pagina JSP

Desde el
controlador ([Controlador.java](src%2Fmain%2Fjava%2Fcom%2Fconexion%2Fpildora%2Fes%2Fcontrolador%2FControlador.java)) se
llamara al JSP ([tablaCliente.jsp](src%2Fmain%2Fwebapp%2FWEB-INF%2Fview%2FtablaCliente.jsp)), este controlador sera
invocado una vez lanzado el proyecto a traves del la url + su **RequestMapping**,
con la etiqueta controller es tiene el mismo valor que

# Creacion de Entidad

[Cliente.java](src%2Fmain%2Fjava%2Fcom%2Fconexion%2Fpildora%2Fes%2Fentity%2FCliente.java), importante referenciar el
paquete en donde figura esta entidad y que este todo correcto en el
fichero [spring-crud-servlet.xml](src%2Fmain%2Fwebapp%2FWEB-INF%2Fspring-crud-servlet.xml) en el bean **sessionFactory
**,
en la propiedad _packagesToScan_ es donde se hace referencia a la entidad.

```xml
<bean id="sessionFactory"
      class="org.springframework.orm.hibernate5.LocalSessionFactoryBean">
    <property name="dataSource" ref="myDataSource"/>
    <property name="packagesToScan" value="com.conexion.pildora.es.entity"/>
    <property name="hibernateProperties">
        <props>
            <prop key="hibernate.dialect">org.hibernate.dialect.MySQLDialect</prop>
            <prop key="hibernate.show_sql">true</prop>
        </props>
    </property>
</bean>     
```

# DAO

Data Transfert Object

Creacion del DAO, implementacion y modificacion de la pagina JSP para que muestre una lista clientes

## Interfaz e implementacion DAO

Con el dao establecemos una conexion con la persistencia de datos, primero creamos su interfaz y luego lo implementamos.
Con este metodo conseguimos extraer los clientes de la base de datos.

La implementacion de la interfaz del
DAO [ClienteDAOImplementation.java](src%2Fmain%2Fjava%2Fcom%2Fconexion%2Fpildora%2Fes%2FDAO%2FClienteDAOImplementation.java)
para poder conectarse con la base de datos lo hace mediante SessionFactory (definido
en [spring-crud-servlet.xml](src%2Fmain%2Fwebapp%2FWEB-INF%2Fspring-crud-servlet.xml))
el cual contiene una instancia de la BD, por la cual se pueden ejecutar Querys.

### @Repository

Es la etiqueta que utiliza la
clase [ClienteDAOImplementation.java](src%2Fmain%2Fjava%2Fcom%2Fconexion%2Fpildora%2Fes%2FDAO%2FClienteDAOImplementation.java)
que implementa los metodos de la
interfaz [ClienteDAO.java](src%2Fmain%2Fjava%2Fcom%2Fconexion%2Fpildora%2Fes%2FDAO%2FClienteDAO.java)
, de esta manera Spring podrá encontrar este recursos cuando lo necesite con el autowired. <br>
La interfaz no es necesaria etiquetarla (**_a dia de hoy_**) con esta etiqueta pero si en la implementacion.

### @Transactional

Etiqueta que sirve para evitarnos el uso de tener que abrir y cerrar sesiones

```java
 @Autowired
private SessionFactory sessionFactory;

        Session session=sessionFactory.getCurrentSession();
        Query<Cliente> query=session.createQuery("from Cliente",Cliente.class);
```

En vez de

```java
 @Autowired
private SessionFactory factory;

        Session session=factory.openSession();
        session.beginTransaction();
        session.createQuery("from Cliente").getResultList();
        session.getTransaction().commit();
        session.close();
```

## Create/Insertar cliente
1. Sin mas se añade un **boton** para añadir un cliente que redirige a una pagina nueva
2. Crear **controlador** [Controlador.java](src%2Fmain%2Fjava%2Fcom%2Fconexion%2Fpildora%2Fes%2Fcontrolador%2FControlador.java) al que se le invoca desde el boton y crear el **formulario** [formularioAgregarCliente.jsp](src%2Fmain%2Fwebapp%2FWEB-INF%2Fview%2FformularioAgregarCliente.jsp) para introducir los datos del cliente
   * En el controlador antes de llegar al fomulario  se **crea el cliente** (con sus datos vacios) y se agregan al modelo
   * En nuevo formulario hay que añadir (**taglibrary forms**) una libreria de formulario
```xhtml
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
</head>
<body>
<form:form action="insertarCliente" modelAttribute="agregarCliente" method="post">
   <table>
      <tr>
         <td>Nombre</td>
         <td><form:input path="nombre"/></td>
      </tr>
   </table>
</form:form>
```
   * En el action del formulario se indica cual es el siguiente controlador
3. Crear el metodo de guardar en el ClienteDAO e implementarlo
4. Recuperar del JSP el cliente con los datos introducidos y llamar al DAO para insertar el cliente<br>
Es imporante señalar aqui la etiqueta utilizada **@PostMapping** que es la url que aparecera en el action del form (2.) y la etiqueta en el parametro de
entrada **@ModelAttribute** para obtener el cliente 
```java
 @PostMapping("/insertarCliente")
public String insertarCliente(@ModelAttribute("agregarCliente")Cliente cliente){
    clienteDAO.insertarCliente(cliente);
    return "redirect:/cliente/lista";
}
```


## Update/Actualizacion de un cliente (modificacion en la creacion)
1. Crear el boton en la tabla para que elija el cliente que se quiere modificar
2. Modificar el jsp [tablaCliente.jsp](src%2Fmain%2Fwebapp%2FWEB-INF%2Fview%2FtablaCliente.jsp) para que pase el id del cliente y la url del controlador que
   hara la carga del cliente en el formulario
3. Modificar el formulario para que incluya el campo id del cliente oculto
4. Modificar [Controlador.java](src%2Fmain%2Fjava%2Fcom%2Fconexion%2Fpildora%2Fes%2Fcontrolador%2FControlador.java) el metodo que se encarga de insertar, consultara la BD para ver si existe el cliente, sino existe lo inserta, sino lo modifica

# JSP

En el archivo JSP para iterar sobre la lista que se le pasa como parametro es necesario implementar la taglib (con el prefijo **c** en este caso) de
jsp/jstl y añadir sus librerias correspondientes.

**_IMPORTANTE_**: si a la hora de añadir la uri y autocompletar no sale la uri que queremos probablementen no este implementada la dependencia que deseamos

```jsp
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:forEach var="clienteTenp" items="${clienteList}">
    <tr>
        <td>${clienteTenp.nombre}</td>
        <td>${clienteTenp.apellido}</td>
        <td>${clienteTenp.email}</td>
    </tr>
    </c:forEach>
```

Asi se podra utilizar el metodo _"forEach"_ y mostrar los elementos que se necesiten de la lista

## Errores encontrados en la creacion del JSP

Al incluir las librearias para jstl ha habido un problema con las dependicas de maven, ya que necesitaba las
dependencias de glass fish y
jakarta  ([Error en stackoverflow](https://stackoverflow.com/questions/4928271/how-to-install-jstl-it-fails-with-the-absolute-uri-cannot-be-resolved-or-una))
<br> Sino aparecia un error 500 en donde los errores iban cambiando entre dos diferentes

```xml
<!--Librerias necesarias-->
<dependency>
    <groupId>jakarta.servlet.jsp.jstl</groupId>
    <artifactId>jakarta.servlet.jsp.jstl-api</artifactId>
    <version>3.0.0</version>
</dependency>

<dependency>
<groupId>org.glassfish.web</groupId>
<artifactId>jakarta.servlet.jsp.jstl</artifactId>
<version>3.0.1</version>
</dependency>
```
# Pagina inicio y CSS
## index.jsp
Scrip redireccion, unicamente se ha creado y añadido una linea en [index.jsp](src%2Fmain%2Fwebapp%2Findex.jsp) para que redireccione a [tablaCliente.jsp](src%2Fmain%2Fwebapp%2FWEB-INF%2Fview%2FtablaCliente.jsp)
```html
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index</title>
</head>
<body>
<% response.sendRedirect("cliente/lista");%>
</body>
</html>
```
## CSS
1. Crear la **carpeta resources**: puede venir una creada por defecto, nosotros crearemos una en webapp al mismo nivel que WEB-INF 
2. Crear **css** en resources: [estilo.css](src%2Fmain%2Fwebapp%2Fresources%2Fcss%2Festilo.css)
3. Configurar spring para que **busque los recursos** en resources, esto se hara añadiendo una linea al servlet [spring-crud-servlet.xml](src%2Fmain%2Fwebapp%2FWEB-INF%2Fspring-crud-servlet.xml) <br>
```xml
<!--Especificacion del directorio de recursos, "mapping" es una url donde se encontraran los recursos, donde accede spring
	para obtener los recursos-->
<mvc:resources mapping="/resources/**" location="/resources/"></mvc:resources>  
```
4. **Referencia al css desde el jsp**: añadir una linea en el jsp que queremos aplicar el css.
```html
<head>
    <meta charset="ISO-8859-1">
    <title>Lista clientes</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/estilo.css"/>
</head>
```
Linea importante añadida en head del fichero [tablaCliente.jsp](src%2Fmain%2Fwebapp%2FWEB-INF%2Fview%2FtablaCliente.jsp)
que buscara el css en la referencia indicada en "**ref**", este recurso es puesto a su disponibilidad en el apartado "3.", lo
esta buscando en esa url

