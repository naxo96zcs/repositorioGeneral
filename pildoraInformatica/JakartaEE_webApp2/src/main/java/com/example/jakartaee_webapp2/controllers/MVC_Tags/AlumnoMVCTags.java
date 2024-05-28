package com.example.jakartaee_webapp2.controllers.MVC_Tags;

import com.example.jakartaee_webapp2.custom_validation.CPostalMadrid;
import jakarta.validation.constraints.*;

public class AlumnoMVCTags {
    /**
     * -Bean Annotations-
     * 1.Anotaciones para utilizar los bean validations, por ejemplo para que el nombre no quede en blanco
     * 2.Estas anotaciones son necesarias especificarlas en los metodos
     * - El resutlado de las validaciones se guardan en "BindingResult", (mirar el metodo que lo invoca "ControladorAlumno.resgistroFormulario")
     */

    @NotNull        //Valor no nulo
    @Size(min = 2, message = "El nombre tiene que tener al menos 2 letras")
    //Long minima, mensaje que se muestra si esta mal
    private String nombre;
    @NotNull
    @Size(min = 2, message = "El apellido tiene que tener al menos 2 letras")
    private String apellido;
    @NotNull @Min(value = 18, message = "Tiene que ser mayor de edad") //Valor minimo
    @Max(value = 200, message = "Edad inferior a 200 años")// Vakir naxuni
    private Integer edad;
    @Email
    @NotNull
    private String email;
    //El primero comentado es mas basico, el otro es mas avanzado para comprobar prefijos
    //@Pattern(regexp = "[0-9]{5}", message = "Solo 5 valores numericos")
    @CPostalMadrid //Esta etiqueta ha sido creada con la interfaz en com/example/jakartaee_webapp2/custom_validation/CPostalMadrid.java
    private String codigoPostal;
    private String optativa;
    private String evalucion;
    private String idiomas;

    //Getters & Setters

    public String getCodigoPostal() {
        return codigoPostal;
    }
    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public String getEvalucion() {
        return evalucion;
    }

    public void setEvalucion(String evalucion) {
        this.evalucion = evalucion;
    }

    public String getOptativa() {
        return optativa;
    }

    public void setOptativa(String optativa) {
        this.optativa = optativa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
