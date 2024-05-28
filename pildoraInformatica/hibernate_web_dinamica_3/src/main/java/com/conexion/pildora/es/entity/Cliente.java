package com.conexion.pildora.es.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity //Con esto se indica que se va a referir a una tabla, un mapeo
@Table(name = "cliente") //Nombre de la tabla
public class Cliente {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "email")
    private String email;

    @Override
    public String toString() {
        return nombre + " " + apellido + " - " + email;
    }
}
