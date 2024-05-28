package org.example.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pedido")
public class Pedido {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="fecha")
    private Date fecha;
    @Column(name="forma_pago")
    private String formaPago;
    //Con casitodos los tipos de cascadas indicamos que se pueda realziar de tod0 menos el borrado
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name="cliente_id") //Parecido a @Column
    private Cliente cliente;//Muchos (class) pedidos a un cliente (attribute)

    public Pedido(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "*Pedido*\n" +
                "id=" + id +
                ", fecha=" + fecha +
                ", formaPago='" + formaPago;
    }

}
