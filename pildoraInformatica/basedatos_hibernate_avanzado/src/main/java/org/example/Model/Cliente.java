package org.example.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity //Con esto se indica que se va a referir a una tabla, un mapeo
@Table(name = "cliente") //Nombre de la tabla
public class Cliente {
    @Id //Indica que es primary key
    @Column(name = "id")//Case sensitive
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Para la clave primaria, sino se pone a la hora de obteerlo o insertarlo puede dar problemas en el valor
    private int id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellidos;
    @Column(name = "direccion")
    private String direccion;
    /**
     * Para especificar la realacion uno a uno y que el ¿borrado no se haga en cascada?
     */
    @OneToOne(cascade = CascadeType.ALL)// 1 --> 1
    @JoinColumn(name = "id")
    private DetalleCliente detalleCliente;
    //Estoy haciendo un mapeo desde la clase pedido (atributo 'cliente')
    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "cliente", cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    private List<Pedido> pedidos;

    public Cliente(String nombre, String apellidos, String direccion) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "\n*Cliente: " + nombre + " " + apellidos + "\nDir: " + direccion +
                "\n" + detalleCliente+"\n";
    }

    public void agregarPedido(Pedido pedido) {
        if (pedidos == null)
            pedidos = new ArrayList<>();
        pedidos.add(pedido);
        pedido.setCliente(this);
    }
}
