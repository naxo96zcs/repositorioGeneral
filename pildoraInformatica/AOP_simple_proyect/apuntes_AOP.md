# AOP
AOP o programacion orientada a aspectos. La programacion orientada a aspectos sirve para:
* **Ahorrar tiempo** programando, menos repeticion de codigo
* Hacer un **codigo mas seguro**. Control de sesiones,permisos, carritos...

Un *aspecto*: es un _conjunto de asustos transversales de modularización díficil_, consiste en encapsular un código para utilziarlo en otras clases.

La encapsulacion y reutilizacion se puede conseguir con:
1. ~~**Herencia**~~: dificil de implementar porque no existe la herencia multiple en java
2. ~~**Delegacion de tareas**~~: actualizacion de clases es muy costoso
3. **Creacion de un aspecto**: reutilizacion de codigo fácil mediante la **encapsulacion de código**



## Ejemplo
Imaginemos que un usuario necesita iniciar sesion o recurperar su sesion antes de insertar un cliente (sobre todo para ver si dispone de permiosos).

````java
void insertarCliente(){
    Session session;
    session.save(miSesion); 
}
````
Cada vez que se programase un metodo en el que se necesitase recuperar la sesion, habría que recuperar la sesion perderíamos mucho tiempo a la hora del desarrollo, ya que si se 
modificase una funcionalidad habría que hacerlo en cada uno de los metodos.

## Aspectos
La ejecución de los aspectos puede hacerse antes o despues del codigo que se quiere ejecutar (antes o despues de la insercción).

Esto variará con las etiquetas utilizadas @Before, @After...

# Codigo
Para la puesta en marcha de este proyecto:
1. Exportar las librerías necesarias
   1. Importar **aspectj** (el runtime, el weaver y tool, para las anotaciones) 
2. Clase _Configuracion_: donde tiene que ir a buscar los aspecto y la configuracion de los mismos
````java
@Configuration //indica que es un archivo de configuracion
@EnableAspectJAutoProxy //Habilita los aspectos
@ComponentScan("org.example.AOP") //donde debe buscar los aspectos
public class Configuracion {
}
````
3. Clase principal
````java
    public static void main(String[] args) {
        //Leer la config de spring
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Configuracion.class);
        //Obtener el bean del contenedor de spring
        ClienteDAO cliente = context.getBean("clienteDAO",ClienteDAO.class);
        //Llamar al metodo
        cliente.insertaCliente();
        //Cerrar contexto
        context.close();
    }
````
4. Creacion del aspecto, este se ejecutara antes debido a:
   * _Before_, que hace que se ejecute el codigo antes del metodo indicado
```java
@Aspect
@Component
public class LoginConAspecto {
    @Before("execution(public void insertaCliente())")//se ejecute antes del metodo indicado
    public void antesInsertarCliente() {
        System.out.println("El usuario esta logueado");
        System.out.println("El perfil para insertar los clientes es correcto");
    }
}
```
## Aplicar el aspecto a una clase en concreto
Si tenemos dos clases A y B con el metodo insertarCliente en ambas, el aspecto siempre se ejecuta cuando se ejecuten entos metodos independientemente de que clase se este utilizando

Por defecto el aspecto se aplica para todos los metodos que coincidan para hacer que solo se le aplique a una clase hay que especificar una ruta (**org.example.DAO.ClienteVipDAO**) delante del metodo,
de esta manera lo que hara es que se le aplique solo al metodo de esa clase.
````java
    @Before("execution(public void org.example.DAO.ClienteVipDAO.insertaCliente())")
    public void antesInsertarCliente() {
        System.out.println("El usuario esta logueado");
        System.out.println("El perfil para insertar los clientes es correcto");
    }
````
## Aplicar el aspecto a metodos diferentes en distintas clases
Es importante que tengan un **lexico parecido** tipo getX, insertX, deleteX...

En este caso todos los metodos que se llamen **_metodoAspecto..._** se ejecutará el aspecto antes de ejecutarse el método, Hay que tener en cuenta que siempre deben de empezar igual. (En este ejemplo, los metodos que empiecen de la misma manera 'metodoAspecto...' se ejecutara el aspecto antes de estos) 
````java
@Before("execution(public void metodoAspecto*())")
    public void aspectoEnMetodosDiferentes() {
        //codigo
    }
````


# Refs
[Teoria AOP](https://www.youtube.com/watch?v=AjXPs9nVHow&list=PLU8oAlHdN5Blq85GIxtKjIXdfHPksV_Hm&index=76&pp=iAQB)

[Baeldung aspectj](https://www.baeldung.com/aspectj)


