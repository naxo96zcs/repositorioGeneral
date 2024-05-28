package com.example.jakartaee_webapp2.custom_validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.annotation.Annotation;

/**
 * Clase que tiene la logica de la validacion
 *
 * El primer paramtro de "CPostalMadridValidacion" es la interfaz de la que esta siendo referido esta clase con la
 * etiqueta @Constraint
 */
public class CPostalMadridValidacion implements ConstraintValidator <CPostalMadrid, String> {

    private String prefijoCodPostalMadrid;

    //Redundate si es igual que el super
    @Override
    public void initialize(CPostalMadrid elCodigo) {
        prefijoCodPostalMadrid = elCodigo.value();
    }

    /**
     * Comprueba si el cod. postal introducido por el usuario empieza por lo definido en el initialize
     * @param inputUser object to validate
     * @param cxt context in which the constraint is evaluated
     *
     * @return
     */
    @Override
    public boolean isValid(String inputUser,ConstraintValidatorContext cxt) {
        if(inputUser!=null)
            return inputUser.startsWith(prefijoCodPostalMadrid); //Tiene que empezar por prefijo
        else
            return true; //Si es nulo tmb sirve para continuar con el form
    }

}
