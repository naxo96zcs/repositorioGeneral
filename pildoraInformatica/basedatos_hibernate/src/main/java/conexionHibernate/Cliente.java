package conexionHibernate;


import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity //Con esto se indica que se va a referir a una tabla, un mapeo
@Table(name = "clientes") //Nombre de la tabla
public class Cliente {
    @Id //Indica que es primary key
    @Column(name = "Id")//Case sensitive
    @GeneratedValue (strategy = GenerationType.IDENTITY) //Para la clave primaria, sino se pone a la hora de obteerlo o insertarlo puede dar problemas en el valor
    private int id;
    @Column(name = "Nombre")
    private String nombre;
    @Column(name = "Apellidos")
    private String apellidos;
    @Column(name = "Direccion")
    private String direccion;

    public Cliente(String nombre, String apellidos, String direccion) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
    }

}
