package org.example.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity //Con esto se indica que se va a referir a una tabla, un mapeo
@Table(name = "detalles_cliente") //Nombre de la tabla
public class DetalleCliente {
    @Id //Primary key
    @Column(name = "id")//Case sensitive
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "web")
    private String web;
    @Column(name = "tfno")
    private String tfno;
    @Column(name = "comentarios")
    private String comentarios;

    /**
     * Para conseguir la bidireccionalidad debe figurar en los dos el OneToOne
     * -mappedBy debe ser el nombre que tiene el atributo (Clientes) desde el que se hace referencia
     * a esta clase. Cliente hace referencia a DetalleCliente por el atributo detalleCliente que es el que
     * podnremos en mappedBy
     */
    @OneToOne(mappedBy = "detalleCliente",cascade = CascadeType.ALL) //El nombre de OneToOne por el que se relacionan en clientes
    @JoinColumn(name="id")
    private Cliente cliente;

    public DetalleCliente(String web, String tfno, String comentarios) {
        this.web = web;
        this.tfno = tfno;
        this.comentarios = comentarios;
    }

    @Override
    public String toString() {
        return "*DetalleCliente*\n" +
                "id=" + id +
                ", web='" + web + '\'' +
                ", tfno='" + tfno + '\'' +
                ", comentarios='" + comentarios + '\'';

    }
}
