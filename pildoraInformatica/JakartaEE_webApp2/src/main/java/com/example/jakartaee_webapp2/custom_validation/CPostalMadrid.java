package com.example.jakartaee_webapp2.custom_validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = CPostalMadridValidacion.class)//La clase que va a tener la logica de la aplicacion
@Target({ElementType.METHOD,ElementType.FIELD})//A que aplicamos la validacion: método, campo...
@Retention(RetentionPolicy.RUNTIME)// Cuando se comprueba la validacion (si es en tiempo de ejecucion RUNTIME)
public @interface CPostalMadrid {

    //definir el cod postal por defecto, empieza por 28 en madrid
    public String value() default "28";

    //definir el mensaje de error
    public String message() default "El cod. postal debe empezar por 28";

    //definir los grupos, validar el formulario por pasos, que haga valoraciones por pasos de grupo de datos
    Class<?>[] groups() default {};

    //definir payloads, para metadatos
    Class<? extends Payload>[] payload() default {};

}
